package application.base.infrastructure.bootstrapers.demo;

import application.base.application.classManagement.ScheduleClassController;
import application.base.application.meetingManagement.ScheduleMeetingController;
import application.base.domain.meetingManagement.MeetingInvitation;
import eapli.framework.actions.Action;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MeetingBootstrapper implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(MeetingBootstrapper.class);

    private static final ScheduleMeetingController scheduleMeetingController = new ScheduleMeetingController();

    List<MeetingInvitation> emptyMeetingInvitations = new ArrayList<>();

    @Override
    public boolean execute() {
        try {
            scheduleMeetingController.createMeeting("desc test", convertStringToDate("2023-10-29"), convertStringToLocalTime("10:00", "HH:mm"),
                    convertStringToLocalTime("12:30","HH:mm"), scheduleMeetingController.getUsers().iterator().next());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public static LocalTime convertStringToLocalTime(String timeString, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalTime.parse(timeString, formatter);
    }

    public static Date convertStringToDate(String dateString) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(dateString);
    }


}
