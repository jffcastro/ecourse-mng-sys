/*
 * Copyright (c) 2013-2022 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package application.base.infrastructure.persistence;

import application.base.repositories.examEnrollmentManagement.ExamEnrollmentRepository;
import application.base.repositories.examManagement.ExamRepository;
import application.base.repositories.meetingManagement.MeetingInvitationRepository;
import application.base.repositories.meetingManagement.MeetingRepository;
import application.base.repositories.questionManagement.QuestionRepository;
import application.base.repositories.sharedBoardManagement.*;
import application.base.usermanagement.repositories.StudentRepository;
import application.base.usermanagement.repositories.SignupRequestRepository;
import application.base.usermanagement.repositories.TeacherRepository;
import application.base.repositories.classManagement.CourseClassRepository;
import application.base.repositories.courseManagement.CourseRepository;
import application.base.repositories.enrollmentManagement.EnrollmentRepository;
import application.base.repositories.teachingManagment.TeachingRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;

/**
 * @author Paulo Gandra Sousa
 *
 */
public interface RepositoryFactory {

    /**
     * factory method to create a transactional context to use in the repositories
     *
     * @return
     */
    TransactionalContext newTransactionalContext();

    /**
     *
     * @param autoTx
     *            the transactional context to enrol
     * @return
     */
    UserRepository userRepository(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    UserRepository userRepository();

    /**
     *
     * @param autoTx
     *            the transactional context to enroll
     * @return
     */
    StudentRepository studentRepository(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    StudentRepository studentRepository();

    /**
     *
     * @param autoTx
     *            the transactional context to enroll
     * @return
     */
    TeacherRepository teacherRepository(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    TeacherRepository teacherRepository();

    /**
     *
     * @param autoTx
     *            the transactional context to enroll
     * @return
     */
    SignupRequestRepository signupRequests(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    SignupRequestRepository signupRequests();

    CourseRepository courseRepository(TransactionalContext autoTx);

    CourseRepository courseRepository ();

    /**
     * Course class repository course class repository.
     *
     * @param autoTx the auto tx
     * @return the course class repository
     */
    CourseClassRepository courseClassRepository(TransactionalContext autoTx);

    /**
     * Course class repository course class repository.
     *
     * @return the course class repository
     */
    CourseClassRepository courseClassRepository();

    MeetingRepository meetingRepository(TransactionalContext autoTx);

    MeetingRepository meetingRepository();

    MeetingInvitationRepository meetingInvitationRepository(TransactionalContext autoTx);

    MeetingInvitationRepository meetingInvitationRepository();

    SharedBoardRepository sharedBoardRepository(TransactionalContext autoTx);

    SharedBoardRepository sharedBoardRepository();

    TeachingRepository teachingRepository(TransactionalContext autoTx);

    TeachingRepository teachingRepository();

    EnrollmentRepository enrollmentRepository(TransactionalContext autoTx);

    EnrollmentRepository enrollmentRepository();

    ExamRepository examRepository(TransactionalContext autoTx);

    ExamRepository examRepository();

    ExamEnrollmentRepository examEnrollmentRepository(TransactionalContext autoTx);

    ExamEnrollmentRepository examEnrollmentRepository();

    QuestionRepository questionRepository(TransactionalContext autoTx);

    QuestionRepository questionRepository();

    SharedBoardInvitationRepository sharedBoardInvitationRepository(TransactionalContext autoTx);

    SharedBoardInvitationRepository sharedBoardInvitationRepository();

    RowRepository rowRepository(TransactionalContext autoTx);

    RowRepository rowRepository();

    ColumnRepository columnRepository(TransactionalContext autoTx);

    ColumnRepository columnRepository();

    CellRepository cellRepository(TransactionalContext autoTx);

    CellRepository cellRepository();

    PostItRepository postItRepository(TransactionalContext autoTx);

    PostItRepository postItRepository();

    PostItHistoryRepository postItHistoryRepository(TransactionalContext autoTx);

    PostItHistoryRepository postItHistoryRepository();
}
