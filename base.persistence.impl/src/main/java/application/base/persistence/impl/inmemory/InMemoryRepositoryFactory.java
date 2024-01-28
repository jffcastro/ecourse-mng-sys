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
package application.base.persistence.impl.inmemory;

import application.base.repositories.examEnrollmentManagement.ExamEnrollmentRepository;
import application.base.repositories.examManagement.ExamRepository;
import application.base.repositories.meetingManagement.MeetingInvitationRepository;
import application.base.repositories.meetingManagement.MeetingRepository;
import application.base.repositories.questionManagement.QuestionRepository;
import application.base.repositories.sharedBoardManagement.*;
import application.base.usermanagement.repositories.StudentRepository;
import application.base.usermanagement.repositories.SignupRequestRepository;
import application.base.usermanagement.repositories.TeacherRepository;
import application.base.infrastructure.bootstrapers.BaseBootstrapper;
import application.base.infrastructure.persistence.RepositoryFactory;
import application.base.repositories.classManagement.CourseClassRepository;
import application.base.repositories.courseManagement.CourseRepository;
import application.base.repositories.enrollmentManagement.EnrollmentRepository;
import application.base.repositories.teachingManagment.TeachingRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.inmemory.InMemoryUserRepository;

public class InMemoryRepositoryFactory implements RepositoryFactory {

	static {
		// only needed because of the in memory persistence
		new BaseBootstrapper().execute();
	}

	@Override
	public UserRepository userRepository(final TransactionalContext tx) {
		return new InMemoryUserRepository();
	}

	@Override
	public UserRepository userRepository() {
		return userRepository(null);
	}

	@Override
	public StudentRepository studentRepository(final TransactionalContext tx) {
		return new InMemoryStudentRepository();
	}

	@Override
	public StudentRepository studentRepository() {
		return studentRepository(null);
	}

	@Override
	public TeacherRepository teacherRepository(final TransactionalContext tx) {
		return new InMemoryTeacherRepository();
	}

	@Override
	public TeacherRepository teacherRepository() {
		return teacherRepository(null);
	}


	@Override
	public SignupRequestRepository signupRequests() {
		return signupRequests(null);
	}

	@Override
	public SignupRequestRepository signupRequests(final TransactionalContext tx) {
		return new InMemorySignupRequestRepository();
	}

	@Override
	public CourseRepository courseRepository(TransactionalContext autoTx) {
		return new InMemoryCourseRepository();
	}

	@Override
	public CourseRepository courseRepository() {
		return courseRepository(null);
	}

	@Override
	public CourseClassRepository courseClassRepository(TransactionalContext autoTx) {
		return new InMemoryCourseClassRepository();
	}

	@Override
	public CourseClassRepository courseClassRepository() {
		return courseClassRepository(null);
	}

	@Override
	public MeetingRepository meetingRepository(TransactionalContext autoTx) {
		return new InMemoryMeetingRepository();
	}

	@Override
	public MeetingRepository meetingRepository() {
		return meetingRepository(null);
	}

	@Override
	public MeetingInvitationRepository meetingInvitationRepository(TransactionalContext autoTx) {
		return new InMemoryMeetingInvitationRepository();
	}

	@Override
	public MeetingInvitationRepository meetingInvitationRepository() {
		return meetingInvitationRepository(null);
	}

	@Override
	public SharedBoardRepository sharedBoardRepository(TransactionalContext autoTx) {return new InMemorySharedBoardRepository();}

	@Override
	public SharedBoardRepository sharedBoardRepository() {
		return sharedBoardRepository(null);
	}

	@Override
	public TeachingRepository teachingRepository(TransactionalContext autoTx) {
		return new InMemoryTeachingRepository();
	}

	@Override
	public TeachingRepository teachingRepository() {
		return teachingRepository(null);
	}

	@Override
	public EnrollmentRepository enrollmentRepository(TransactionalContext autoTx) {
		return new InMemoryEnrollmentRepository();
	}

	@Override
	public EnrollmentRepository enrollmentRepository() {
		return enrollmentRepository(null);
	}

	@Override
	public ExamRepository examRepository(TransactionalContext autoTx) {
		return new InMemoryExamRepository();
	}

	@Override
	public ExamRepository examRepository() {
		return examRepository(null);
	}

	@Override
	public ExamEnrollmentRepository examEnrollmentRepository(TransactionalContext autoTx) {
		return new InMemoryExamEnrollmentRepository();
	}

	@Override
	public ExamEnrollmentRepository examEnrollmentRepository() {
		return examEnrollmentRepository(null);
	}

	@Override
	public QuestionRepository questionRepository(TransactionalContext autoTx) {
		return new InMemoryQuestionRepository();
	}

	@Override
	public QuestionRepository questionRepository() {
		return questionRepository(null);
	}

	@Override
	public SharedBoardInvitationRepository sharedBoardInvitationRepository(TransactionalContext autoTx){
		return new InMemorySharedBoardInvitationRepository();
	}

	@Override
	public SharedBoardInvitationRepository sharedBoardInvitationRepository() {
		return sharedBoardInvitationRepository(null);
	}

	@Override
	public RowRepository rowRepository (TransactionalContext autoTx){return new InMemoryRowRepository();}

	@Override
	public RowRepository rowRepository() {
		return rowRepository(null);
	}

	@Override
	public ColumnRepository columnRepository (TransactionalContext autoTx){return new InMemoryColumnRepository();}

	@Override
	public ColumnRepository columnRepository() {
		return columnRepository(null);
	}

	@Override
	public CellRepository cellRepository (TransactionalContext autoTx){return new InMemoryCellRepository();}

	@Override
	public CellRepository cellRepository() {
		return cellRepository(null);
	}

	@Override
	public PostItRepository postItRepository(TransactionalContext autoTx) {return new InMemoryPostItRepository();}

	@Override
	public PostItRepository postItRepository() {return postItRepository(null);}

	@Override
	public PostItHistoryRepository postItHistoryRepository(TransactionalContext autoTx) {
		return new InMemoryPostItHistoryRepository();
	}

	@Override
	public PostItHistoryRepository postItHistoryRepository() {
		return postItHistoryRepository(null);
	}

	@Override
	public TransactionalContext newTransactionalContext() {
		// in memory does not support transactions...
		return null;
	}

}
