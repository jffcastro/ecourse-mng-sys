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
package application.base.persistence.impl.jpa;

import application.base.Application;
import application.base.infrastructure.persistence.RepositoryFactory;
import application.base.repositories.classManagement.CourseClassRepository;
import application.base.repositories.meetingManagement.MeetingInvitationRepository;
import application.base.repositories.meetingManagement.MeetingRepository;
import application.base.repositories.questionManagement.QuestionRepository;
import application.base.repositories.sharedBoardManagement.*;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.jpa.JpaAutoTxUserRepository;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaRepositoryFactory implements RepositoryFactory {

	@Override
	public UserRepository userRepository(final TransactionalContext autoTx) {
		return new JpaAutoTxUserRepository(autoTx);
	}

	@Override
	public UserRepository userRepository() {
		return new JpaAutoTxUserRepository(Application.settings().getPersistenceUnitName(),
				Application.settings().getExtendedPersistenceProperties());
	}

	@Override
	public JpaStudentRepository studentRepository(final TransactionalContext autoTx) {
		return new JpaStudentRepository(autoTx);
	}

	@Override
	public JpaStudentRepository studentRepository() {
		return new JpaStudentRepository(Application.settings().getPersistenceUnitName());
	}

	@Override
	public JpaTeacherRepository teacherRepository(final TransactionalContext autoTx) {
		return new JpaTeacherRepository(autoTx);
	}

	@Override
	public JpaTeacherRepository teacherRepository() {
		return new JpaTeacherRepository(Application.settings().getPersistenceUnitName());
	}

	@Override
	public JpaSignupRequestRepository signupRequests(final TransactionalContext autoTx) {
		return new JpaSignupRequestRepository(autoTx);
	}

	@Override
	public JpaSignupRequestRepository signupRequests() {
		return new JpaSignupRequestRepository(Application.settings().getPersistenceUnitName());
	}

	@Override
	public JpaCourseRepository courseRepository(final TransactionalContext autoTx) {
		return new JpaCourseRepository(autoTx);
	}

	@Override
	public JpaCourseRepository courseRepository (){
		return new JpaCourseRepository(Application.settings().getPersistenceUnitName());
	}

	@Override
	public CourseClassRepository courseClassRepository(TransactionalContext autoTx) {
		return new JpaCourseClassRepository(autoTx);
	}

	@Override
	public CourseClassRepository courseClassRepository() {
		return new JpaCourseClassRepository(Application.settings().getPersistenceUnitName());
	}

	@Override
	public MeetingRepository meetingRepository(final TransactionalContext autoTx) {
		return new JpaMeetingRepository(autoTx);
	}

	@Override
	public MeetingRepository meetingRepository() {
		return new JpaMeetingRepository(Application.settings().getPersistenceUnitName());
	}

	@Override
	public MeetingInvitationRepository meetingInvitationRepository(final TransactionalContext autoTx) {
		return new JpaMeetingInvitationRepository(autoTx);
	}

	@Override
	public MeetingInvitationRepository meetingInvitationRepository() {
		return new JpaMeetingInvitationRepository(Application.settings().getPersistenceUnitName());
	}

	@Override
	public JpaSharedBoardRepository sharedBoardRepository(final TransactionalContext autoTx) {
		return new JpaSharedBoardRepository(autoTx);
	}

	@Override
	public JpaSharedBoardRepository sharedBoardRepository(){
		return new JpaSharedBoardRepository(Application.settings().getPersistenceUnitName());
	}

	@Override
	public JpaTeachingRepository teachingRepository(final TransactionalContext autoTx) {
		return new JpaTeachingRepository(autoTx);
	}

	@Override
	public JpaTeachingRepository teachingRepository(){
		return new JpaTeachingRepository(Application.settings().getPersistenceUnitName());
	}

	@Override
	public JpaEnrollmentRepository enrollmentRepository(final TransactionalContext autoTx) {
		return new JpaEnrollmentRepository(autoTx);
	}

	@Override
	public JpaEnrollmentRepository enrollmentRepository(){
		return new JpaEnrollmentRepository(Application.settings().getPersistenceUnitName());
	}

	@Override
	public JpaExamRepository examRepository(final TransactionalContext autoTx) {
		return new JpaExamRepository(autoTx);
	}

	@Override
	public JpaExamRepository examRepository(){
		return new JpaExamRepository(Application.settings().getPersistenceUnitName());
	}

	@Override
	public JpaExamEnrollmentRepository examEnrollmentRepository(final TransactionalContext autoTx) {
		return new JpaExamEnrollmentRepository(autoTx);
	}

	@Override
	public JpaExamEnrollmentRepository examEnrollmentRepository(){
		return new JpaExamEnrollmentRepository(Application.settings().getPersistenceUnitName());
	}

	@Override
	public QuestionRepository questionRepository(TransactionalContext autoTx) {
		return new JpaQuestionRepository(autoTx);
	}

	@Override
	public QuestionRepository questionRepository() {
		return new JpaQuestionRepository(Application.settings().getPersistenceUnitName());
	}

	@Override
	public SharedBoardInvitationRepository sharedBoardInvitationRepository(TransactionalContext autoTx) {
		return new JpaSharedBoardInvitationRepository(autoTx);
	}

	@Override
	public SharedBoardInvitationRepository sharedBoardInvitationRepository() {
		return new JpaSharedBoardInvitationRepository(Application.settings().getPersistenceUnitName());
	}

	@Override
	public RowRepository rowRepository(TransactionalContext autoTx) {
		return new JpaRowRepository(autoTx);
	}

	@Override
	public RowRepository rowRepository() {
		return new JpaRowRepository(Application.settings().getPersistenceUnitName());
	}

	@Override
	public ColumnRepository columnRepository(TransactionalContext autoTx) {
		return new JpaColumnRepository(autoTx);
	}

	@Override
	public ColumnRepository columnRepository() {
		return new JpaColumnRepository(Application.settings().getPersistenceUnitName());
	}

	@Override
	public CellRepository cellRepository(TransactionalContext autoTx) {
		return new JpaCellRepository(autoTx);
	}

	@Override
	public CellRepository cellRepository() {
		return new JpaCellRepository(Application.settings().getPersistenceUnitName());
	}

	@Override
	public PostItRepository postItRepository(TransactionalContext autoTx) {
		return new JpaPostItRepository(autoTx);
	}

	@Override
	public PostItRepository postItRepository() {
		return new JpaPostItRepository(Application.settings().getPersistenceUnitName());
	}

	@Override
	public PostItHistoryRepository postItHistoryRepository(TransactionalContext autoTx) {return new JpaPostItHistoryRepository(autoTx);}

	@Override
	public PostItHistoryRepository postItHistoryRepository() {
		return new JpaPostItHistoryRepository(Application.settings().getPersistenceUnitName());
	}

	@Override
	public TransactionalContext newTransactionalContext() {
		return JpaAutoTxRepository.buildTransactionalContext(Application.settings().getPersistenceUnitName(),
				Application.settings().getExtendedPersistenceProperties());
	}

}
