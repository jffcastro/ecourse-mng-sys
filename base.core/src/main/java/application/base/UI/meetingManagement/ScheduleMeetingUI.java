package application.base.UI.meetingManagement;

import application.base.application.meetingManagement.ScheduleMeetingController;
import application.base.domain.meetingManagement.Meeting;
import application.base.domain.meetingManagement.MeetingInvitation;
import application.base.domain.meetingManagement.MeetingInvitationStatus;
import application.base.usermanagement.domain.BaseRoles;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLOutput;
import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * The type Schedule meeting ui.
 */
public class ScheduleMeetingUI extends AbstractUI {

    private AuthorizationService authorizationService = AuthzRegistry.authorizationService();


    /**
     * The The controller.
     */
    ScheduleMeetingController theController = new ScheduleMeetingController();
    private static final Logger LOGGER = LogManager.getLogger(ScheduleMeetingUI.class);

    @Override
    protected boolean doShow() {
        try {
            Scanner scanner = new Scanner(System.in);

            List<SystemUser> invitees = new ArrayList<>();

            List<MeetingInvitation> meetingInvitations = new ArrayList<>();

            String description = null;
            String dateString = null;
            LocalTime startTime = null;
            LocalTime endTime = null;

            if (authorizationService.isAuthenticatedUserAuthorizedTo(BaseRoles.TEACHER,BaseRoles.POWER_USER,
                    BaseRoles.STUDENT,BaseRoles.MANAGER)) {

                System.out.println("Let's Schedule a Meeting");

                while (true) {
                    description = Console.readLine("Type the description, it must be between 10 and 50 characters:");
                    if (theController.isDescriptionValid(description)) {
                        break;
                    } else {
                        System.out.println("Invalid description, try again! (Must be between 10 and 50 characters)");
                    }
                }

                while (true) {
                    dateString = Console.readLine("\nType the meeting date (FORMAT: YYYY-MM-DD), it must be after the current date:");
                    if (theController.checkAndGetDate(dateString) != null) {
                        break;
                    } else {
                        System.out.println("Invalid start date! Start date must be after the current date.");
                    }
                }

                while (true) {
                    startTime = readTime("\nType the start time (FORMAT: HH:mm): ");
                    endTime = readTime("\nType the end time (FORMAT: HH:mm), it must be after the start time and at max 4 hours after the start time:");
                    if (theController.isTimeRangeValid(startTime, endTime)) {
                        break;
                    } else {
                        System.out.println("Invalid time range! End time must be after the start time and the time range must be at max 4 hours, try again.");
                    }
                }

                Date date = theController.checkAndGetDate(dateString);
                SystemUser organizer = theController.getUser();

                if (organizer == null) {
                    System.out.println("You are not authorized to perform this action.");
                    return false;
                }

                System.out.println("The organizer is going to be: " + organizer.name().toString() + "with username: " + organizer.username().toString());

                if (theController.checkMeeting(organizer,date,startTime,endTime)) {
                    theController.createMeeting(description, date, startTime, endTime, organizer);
                    System.out.println("\nMEETING CREATED WITH SUCCESS at date: " + date + " and time: " + startTime + " to " + endTime + " with organizer: " + organizer.name().toString() + "\n");

                } else{
                    System.out.println("\nMEETING NOT CREATED");
                    System.out.println("The user " + organizer.name().toString() + " is already occupied at this time.");
                    return false;
                }

                Meeting meeting = theController.getMeetingWithOrganizerAndDate(organizer, date,description);

                if (meeting == null) {
                    System.out.println("Tried to query meeting from database but it returned null.");
                    return false;
                }

                List<SystemUser> users = (List<SystemUser>) theController.getUsers();

                if (users.isEmpty()) {
                    System.out.println("There are no users to invite.");
                    return false;
                }

                users.remove(organizer);

                boolean keepAddingUsers = true;

                while (keepAddingUsers) {
                    System.out.println("Select an option:");
                    System.out.println("1. Add an user");
                    System.out.println("0. Close the loop");

                    int option = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    switch (option) {
                        case 1:
                            SystemUser user = chooseUser(users);
                            if (user != null) {
                                invitees.add(user);
                                users.remove(user);
                            }
                            break;
                        case 0:
                            keepAddingUsers = false;
                            break;
                        default:
                            System.out.println("Invalid option");
                    }
                }

                if (invitees.isEmpty()) {
                    System.out.println("You must invite at least one user.");
                    return false;
                }


                for (SystemUser invitee : invitees) {
                    if (theController.checkMeeting(invitee,date,startTime,endTime)) {
                        theController.createMeetingInvitation(meeting, MeetingInvitationStatus.PENDING, invitee);
                        MeetingInvitation meetingInvitation = theController.getMeetingInvitationWithMeetingAndInvitee(meeting, invitee);
                        meetingInvitations.add(meetingInvitation);

                        System.out.println("\nMEETING INVITATION CREATED WITH SUCCESS at date: " + date + " and time: " + startTime + " to " + endTime + " to the user: " + invitee.name().toString() + "\n");

                    } else{
                        System.out.println("\nIGNORED MEETING INVITATION for the user: " + invitee.name().toString() + "\n at date: " + date);
                        System.out.println("The user " + invitee.name().toString() + " is already occupied at this time.");
                    }
                }

                theController.changeMeetingInvitations(meeting, meetingInvitations);

            } else {
                System.out.println("You are not authorized to perform this action.");
                return false;
            }
        } catch (IntegrityViolationException | ConcurrencyException ex) {
            LOGGER.error("Error performing the operation", ex);
            System.out.println(
                    "Unfortunately there was an unexpected error in the application. Please try again and if the problem persists, contact your system administrator.");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    private LocalTime readTime(String prompt) {
        while (true) {
            String timeString = Console.readLine(prompt);
            try {
                return LocalTime.parse(timeString);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time format. Please try again.");
            }
        }
    }

    @Override
    public String headline() {
        return "Schedule a meeting";
    }

    private SystemUser chooseUser(List<SystemUser> usersList) {
        List<SystemUser> availableUsers = new ArrayList<>(usersList);

        while (!availableUsers.isEmpty()) {
            System.out.println("Select a user to add to the meeting:");
            for (int i = 0; i < availableUsers.size(); i++) {
                SystemUser user = availableUsers.get(i);
                System.out.printf("%d. User ID: %s, Name: %s, Email: %s, Role: %s\n",
                        (i + 1), user.identity(), user.name(), user.email(), user.roleTypes());
            }
            int option = Console.readOption(1, availableUsers.size() + 1, 0); // Allow cancel option
            if (option == 0) {
                return null;
            } else if (option <= availableUsers.size()) {
                return availableUsers.get(option - 1);
            }
        }
        return null;
    }




}
