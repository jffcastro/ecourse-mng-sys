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
package application.base.app.user.console.presentation;

import application.base.UI.courseManagement.ListAvailableCoursesUI;
import application.base.UI.authz.MyUserMenu;
import application.base.UI.enrollmentManagement.RequestEnrollmentUI;
import application.base.UI.examManagement.*;
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
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

/**
 * @author Paulo Gandra Sousa
 */
class MainMenu extends ClientUserBaseUI {

    private static final String SEPARATOR_LABEL = "--------------";

    private static final String RETURN_LABEL = "Return ";

    private static final String NOT_IMPLEMENTED_YET = "Not implemented yet";

    private static final int EXIT_OPTION = 0;


    // MAIN MENU
    private static final int MY_USER_OPTION = 1;

    private static final int COURSES_OPTION = 2;

    private static final int SHARED_BOARD_OPTION = 3;

    private static final int ENROLLMENTS_OPTION = 4;

    private static final int MEETINGS_OPTION = 5;

    private static final int EXAMS_OPTION = 6;

    // COURSES
    private static final int LIST_COURSES = 1;

    // ENROLLMENT
    private static final int REQUEST_ENROLLMENT = 1;

    // MEETINGS
    private static final int SCHEDULE_MEETING = 1;

    private static final int CHECK_MEETINGS = 2;

    private static final int VIEW_MEETING_PARTICIPANTS = 3;

    private static final int CANCEL_MEETING = 4;

    //EXAMS
    private static final int LIST_FUTURE_EXAMS = 1;

    private static final int VIEW_GRADES = 2;

    private static final int TAKE_EXAM = 3;

    private static final int GEN_AUTO_EXAM = 4;

    private static final int TAKE_AUTO_EXAM = 5;

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
        final MenuRenderer renderer =
                new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        return renderer.render();
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu();
        mainMenu.addSubMenu(MY_USER_OPTION, myUserMenu);

        final Menu coursesMenu = buildCoursesMenu();
        mainMenu.addSubMenu(COURSES_OPTION, coursesMenu);

        final Menu sharedBoardMenu = new SharedBoardMenu(BaseRoles.MANAGER);
        mainMenu.addSubMenu(SHARED_BOARD_OPTION, sharedBoardMenu);

        final Menu enrollmentsMenu = buildEnrollmentsMenu();
        mainMenu.addSubMenu(ENROLLMENTS_OPTION, enrollmentsMenu);

        final Menu meetingsMenu = buildMeetingsMenu();
        mainMenu.addSubMenu(MEETINGS_OPTION, meetingsMenu);

        final Menu examsMenu = buildExamsMenu();
        mainMenu.addSubMenu(EXAMS_OPTION, examsMenu);

        mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));


        return mainMenu;
    }

    private Menu buildCoursesMenu() {
        final Menu menu = new Menu("Courses >");
        menu.addItem(LIST_COURSES, "List courses: ", new ListAvailableCoursesUI()::show);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);
        return menu;
    }

    private Menu buildEnrollmentsMenu() {
        final Menu menu = new Menu("Enrollments >");
        menu.addItem(REQUEST_ENROLLMENT, "Request Enrollment", new RequestEnrollmentUI()::show);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);
        return menu;
    }

    private Menu buildMeetingsMenu() {
        final Menu menu = new Menu("Meetings >");
        menu.addItem(SCHEDULE_MEETING, "Schedule Meeting", new ScheduleMeetingUI()::show);
        menu.addItem(CHECK_MEETINGS, "Check for meeting invitations", new UserMeetingInvitationUI()::show);
        menu.addItem(VIEW_MEETING_PARTICIPANTS, "View my meeting participants", new ViewMeetingInvitesUI()::show);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);
        menu.addItem(CANCEL_MEETING, "Cancel a meeting", new CancelMeetingUI()::show);
        return menu;
    }

    private Menu buildExamsMenu() {
        final Menu menu = new Menu("Exams >");
        menu.addItem(LIST_FUTURE_EXAMS, "List future exams", new ListFutureExamsUI()::show);
        menu.addItem(VIEW_GRADES, "View my grades", new ListOfMyGradesUI()::show);
        menu.addItem(TAKE_EXAM, "Take Exam", new TakeExamUI()::show);
        menu.addItem(GEN_AUTO_EXAM, "Generate automatic exam", new CreateUpdateAutoExamUI()::show);
        menu.addItem(TAKE_AUTO_EXAM, "Take automatic exam", new TakeAutoExamUI()::show);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);
        return menu;
    }
}
