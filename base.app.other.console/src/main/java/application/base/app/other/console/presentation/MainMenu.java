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
package application.base.app.other.console.presentation;

import application.base.UI.courseManagement.ListAvailableCoursesUI;
import application.base.UI.authz.MyUserMenu;
import application.base.UI.courseClassManagement.ChangeClassSchedule;
import application.base.UI.courseClassManagement.ScheduleClassUI;
import application.base.UI.courseClassManagement.ScheduleExtraordinaryClassUI;
import application.base.UI.courseManagement.ListExamsInCourseUI;
import application.base.UI.examManagement.ListExamGradesUI;
import application.base.UI.meetingManagement.CancelMeetingUI;
import application.base.UI.meetingManagement.ScheduleMeetingUI;
import application.base.UI.examManagement.CreateUpdateExamUI;
import application.base.UI.meetingManagement.UserMeetingInvitationUI;
import application.base.UI.meetingManagement.ViewMeetingInvitesUI;
import application.base.UI.questionManagement.CreateUpdateQuestionsUI;
import application.base.UI.sharedBoardManagement.SharedBoardMenu;
import application.base.Application;
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

    // SCHEDULE COURSE CLASS
    private static final int SCHEDULE_CLASS = 1;
    private static final int SCHEDULE_EXTRAORDINARY_CLASS = 2;
    private static final int CHANGE_SCHEDULE = 3;

    private static final String SEPARATOR_LABEL = "--------------";
    private static final String RETURN_LABEL = "Return ";
    private static final int EXIT_OPTION = 0;

    // MAIN MENU
    private static final int MY_USER_OPTION = 1;

    private static final int COURSES_OPTION = 2;

    private static final int EXAM_OPTION = 3;

    private static final int COURSE_CLASS_OPTION = 4;

    private static final int SCHEDULE_MEETING_OPTION = 5;

    private static final int SHARED_BOARD_OPTION = 6;

    // COURSE
    private static final int LIST_COURSES = 1;

    private static final int EXAMS_COURSE_OPTION = 2;

    // EXAM
    private static final int CREATE_UPDATE_EXAM = 1;

    private static final int CREATE_UPDATE_QUESTION = 2;

    private static final int LIST_EXAM_GRADES = 3;

    // MEETINGS

    private static final int SCHEDULE_MEETING = 1;
    private static final int CHECK_MEETINGS = 2;

    private static final int VIEW_MEETING_PARTICIPANTS = 3;

    private static final int CANCEL_MEETING = 4;

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private final Menu menu;
    private final MenuRenderer renderer;

    public MainMenu() {
        menu = buildMainMenu();
        renderer = getRenderer(menu);
    }

    private MenuRenderer getRenderer(final Menu menu) {
        final MenuRenderer theRenderer;
        if (Application.settings().isMenuLayoutHorizontal()) {
            theRenderer = new HorizontalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        } else {
            theRenderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        }
        return theRenderer;
    }

    @Override
    public boolean doShow() {
        return renderer.render();
    }

    @Override
    public boolean show() {
        drawFormTitle();
        return doShow();
    }

    @Override
    public String headline() {

        return authz.session().map(s -> "Base [ @" + s.authenticatedUser().identity() + " ]")
                .orElse("Base [ ==Anonymous== ]");
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu(BaseRoles.TEACHER);
        mainMenu.addSubMenu(MY_USER_OPTION, myUserMenu);

        final Menu coursesMenu = buildCoursesMenu();
        mainMenu.addSubMenu(COURSES_OPTION, coursesMenu);

        final Menu examsMenu = buildExamsMenu();
        mainMenu.addSubMenu(EXAM_OPTION, examsMenu);

        final Menu CourseClassMenu = buildCourseClassMenu();
        mainMenu.addSubMenu(COURSE_CLASS_OPTION, CourseClassMenu);

        final Menu meetingsMenu = buildMeetingsMenu();
        mainMenu.addSubMenu(SCHEDULE_MEETING_OPTION, meetingsMenu);

        final Menu SharedBoardMenu = new SharedBoardMenu(BaseRoles.TEACHER);
        mainMenu.addSubMenu(SHARED_BOARD_OPTION, SharedBoardMenu);

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

        return mainMenu;
    }

    private Menu buildCoursesMenu() {
        final Menu menu = new Menu("Courses >");
        menu.addItem(LIST_COURSES, "List courses: ", new ListAvailableCoursesUI()::show);
        menu.addItem(EXAMS_COURSE_OPTION, "List all exams of a course", new ListExamsInCourseUI()::show);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);
        return menu;
    }


    private Menu buildExamsMenu() {
        final Menu menu = new Menu("Exams >");
        menu.addItem(CREATE_UPDATE_EXAM, "Create/Update Exam", new CreateUpdateExamUI()::show);
        menu.addItem(CREATE_UPDATE_QUESTION, "Create/Update Question", new CreateUpdateQuestionsUI()::show);
        menu.addItem(LIST_EXAM_GRADES, "List grades of exam", new ListExamGradesUI()::show);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);
        return menu;
    }


    private Menu buildCourseClassMenu(){
        final Menu menu = new Menu("Course Class >");
        menu.addItem(SCHEDULE_CLASS, "Schedule Course Class", new ScheduleClassUI()::show);
        menu.addItem(SCHEDULE_EXTRAORDINARY_CLASS, "Schedule Extraordinary Course Class", new ScheduleExtraordinaryClassUI()::show);
        menu.addItem(CHANGE_SCHEDULE, "Change Schedule", new ChangeClassSchedule()::show);
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
