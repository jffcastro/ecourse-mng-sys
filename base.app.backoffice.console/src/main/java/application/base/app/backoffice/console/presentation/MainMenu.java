/*
 * Copyright (c) 2013-2022 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package application.base.app.backoffice.console.presentation;

import application.base.UI.authz.AddUserUI;
import application.base.UI.courseManagement.*;
import application.base.UI.authz.MyUserMenu;
import application.base.Application;
import application.base.UI.authz.DeactivateUserAction;
import application.base.UI.authz.ListUsersAction;
import application.base.UI.clientuser.AcceptRefuseSignupRequestAction;
import application.base.UI.enrollmentManagement.BulkEnrollStudentsUI;
import application.base.UI.meetingManagement.CancelMeetingUI;
import application.base.UI.meetingManagement.ScheduleMeetingUI;
import application.base.UI.meetingManagement.UserMeetingInvitationUI;
import application.base.UI.meetingManagement.ViewMeetingInvitesUI;
import application.base.UI.sharedBoardManagement.SharedBoardMenu;
import application.base.usermanagement.domain.BaseRoles;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.menu.HorizontalMenuRenderer;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

/**
 * TODO split this class in more specialized classes for each menu
 *
 * @author Paulo Gandra Sousa
 */
public class MainMenu extends AbstractUI {

    private static final String RETURN_LABEL = "Return ";

    private static final int EXIT_OPTION = 0;

    // USERS
    private static final int ADD_USER_OPTION = 1;
    private static final int LIST_USERS_OPTION = 2;
    private static final int DEACTIVATE_USER_OPTION = 3;
    private static final int ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION = 4;


    // MAIN MENU
    private static final int MY_USER_OPTION = 1;
    private static final int USERS_OPTION = 2;
    private static final int COURSES_OPTION = 3;

    private static final int ENROLLMENTS_OPTION = 4;
    private static final int SHARED_BOARD_OPTION = 5;
    private static final int SCHEDULE_MEETING_OPTION = 7;
    private static final int SETTINGS_OPTION = 6;

    // COURSE
    private static final int CREATE_COURSE = 1;
    private static final int LIST_COURSES = 2;
    private static final int OPEN_CLOSE_COURSE = 3;
    private static final int OPEN_CLOSE_ENROLLMENTS = 4;

    private static final int SET_TEACHER_IN_COURSE = 5;

    // ENROLLMENT
    private static final int BULK_ENROLL_STUDENTS = 1;
    private static final int ACCEPT_REJECT_ENROLLMENT_REQUESTS = 2;

    // MEETING
    private static final int SCHEDULE_MEETING = 1;
    private static final int CHECK_MEETINGS = 2;

    private static final int VIEW_MEETING_PARTICIPANTS = 3;

    private static final int CANCEL_MEETING = 4;

    private static final String SEPARATOR_LABEL = "--------------";

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    @Override
    public boolean show() {
        drawFormTitle();
        return doShow();
    }

    /**
     * @return true if the user selected the exit option
     */
    @Override
    public boolean doShow() {
        final Menu menu = buildMainMenu();
        final MenuRenderer renderer;
        if (Application.settings().isMenuLayoutHorizontal()) {
            renderer = new HorizontalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        } else {
            renderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        }
        return renderer.render();
    }

    @Override
    public String headline() {

        return authz.session().map(s -> "Base [ @" + s.authenticatedUser().identity() + " ]")
                .orElse("Base [ ==Anonymous== ]");
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu();
        mainMenu.addSubMenu(MY_USER_OPTION, myUserMenu);

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.POWER_USER, BaseRoles.MANAGER)) {
            final Menu usersMenu = buildUsersMenu();
            mainMenu.addSubMenu(USERS_OPTION, usersMenu);

            final Menu coursesMenu = buildCoursesMenu();
            mainMenu.addSubMenu(COURSES_OPTION, coursesMenu);

            final Menu enrollmentsMenu = buildEnrollmentsMenu();
            mainMenu.addSubMenu(ENROLLMENTS_OPTION, enrollmentsMenu);

            final Menu sharedBoardMenu = new SharedBoardMenu(BaseRoles.MANAGER);
            mainMenu.addSubMenu(SHARED_BOARD_OPTION, sharedBoardMenu);

            final Menu meetingsMenu = buildMeetingsMenu();
            mainMenu.addSubMenu(SCHEDULE_MEETING_OPTION, meetingsMenu);

            final Menu settingsMenu = buildAdminSettingsMenu();
            mainMenu.addSubMenu(SETTINGS_OPTION, settingsMenu);

        }

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

        return mainMenu;
    }

    private Menu buildAdminSettingsMenu() {
        final Menu menu = new Menu("Settings >");

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildUsersMenu() {
        final Menu menu = new Menu("Users >");
        menu.addItem(ADD_USER_OPTION, "Add User", new AddUserUI()::show);
        menu.addItem(LIST_USERS_OPTION, "List all Users", new ListUsersAction());
        menu.addItem(DEACTIVATE_USER_OPTION, "Deactivate User", new DeactivateUserAction());
        menu.addItem(ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION, "Accept/Refuse Signup Request",
                new AcceptRefuseSignupRequestAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }



    private Menu buildCoursesMenu() {
        final Menu menu = new Menu("Courses >");
        menu.addItem(CREATE_COURSE, "Create Course", new CreateCourseUI()::show);
        menu.addItem(LIST_COURSES, "List Courses", new ListAvailableCoursesUI()::show);
        menu.addItem(OPEN_CLOSE_COURSE, "Open/Close Course", new OpenCloseCourseUI()::show);
        menu.addItem(OPEN_CLOSE_ENROLLMENTS, "Open/Close Enrollments", new ChangeEnrollmentsStatusUI()::show);
        menu.addItem(SET_TEACHER_IN_COURSE, "Set Teachers in Course", new SetTeachersCourseUI()::show);

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);
        return menu;
    }

    private Menu buildEnrollmentsMenu() {
        final Menu menu = new Menu("Enrollments >");
        menu.addItem(BULK_ENROLL_STUDENTS, "Bulk Enroll Students", new BulkEnrollStudentsUI()::show);
        menu.addItem(ACCEPT_REJECT_ENROLLMENT_REQUESTS, "Accept/Reject Enrollment Requests", new AcceptRejectEnrollmentRequestUI()::show);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);
        return menu;
    }

    private Menu buildMeetingsMenu(){
        final Menu menu = new Menu("Meetings >");
        menu.addItem(SCHEDULE_MEETING, "Schedule Meeting", new ScheduleMeetingUI()::show);
        menu.addItem(CHECK_MEETINGS, "Check for meeting invitations", new UserMeetingInvitationUI()::show);
        menu.addItem(VIEW_MEETING_PARTICIPANTS, "View my meeting participants", new ViewMeetingInvitesUI()::show);
        menu.addItem(CANCEL_MEETING, "Cancel a meeting", new CancelMeetingUI()::show);
        return menu;
    }

}