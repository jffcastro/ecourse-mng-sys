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
package application.base.infrastructure.bootstrapers.demo;

import application.base.usermanagement.application.student.AcceptRefuseSignupFactory;
import application.base.usermanagement.application.student.AcceptRefuseSignupRequestController;
import application.base.usermanagement.domain.SignupRequest;
import application.base.usermanagement.application.student.SignupController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import application.base.infrastructure.bootstrapers.TestDataConstants;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;


public class StudentBootsrapper implements Action {
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentBootsrapper.class);

	private final SignupController signupController = new SignupController();
	private final AcceptRefuseSignupRequestController acceptController = AcceptRefuseSignupFactory.build();

	@Override
	public boolean execute() {
		// some users that signup and are approved
		signupAndApprove(TestDataConstants.USER_NAME_TEST1, "Password1", "John", "Smith", "john@smith.com",
				TestDataConstants.USER_NUMBER_TEST1);
		signupAndApprove("isep111", "Password1", "Ines", "Regina", "ines@regina.com", "isep111");
		signupAndApprove("isep222", "Password1", "Maria", "Ines", "maria@ines.com", "isep222");
		signupAndApprove("isep333", "Password1", "Pedro", "Miguel", "pedro@miguel.com", "isep333");
		signupAndApprove("isep444", "Password1", "Nuno", "Franco", "nuno@franco.com", "isep444");
		signupAndApprove("isep555", "Password1", "Joao", "Castro", "joao@castro.com", "isep555");

		// some users that signup but the approval is pending. use the backoffice
		// application to approve these
		signup("isep666", "Password1", "Mary", "Smith One", "mary1@smith.com", "isep666");
		signup("isep777", "Password1", "Mary", "Smith Two", "mary2@smith.com", "isep777");
		signup("isep888", "Password1", "Mary", "Smith Three", "mary3@smith.com", "isep888");
		signup("isep999", "Password1", "Mary", "Smith Four", "mary4@smith.com", "isep999");

		return true;
	}

	private SignupRequest signupAndApprove(final String username, final String password, final String firstName,
                                           final String lastName, final String email, final String mecanographicNumber) {
		SignupRequest request = null;
		try {
			request = signupController.signup(username, password, firstName, lastName, email, mecanographicNumber);
			acceptController.acceptSignupRequest(request);
		} catch (final ConcurrencyException | IntegrityViolationException e) {
			// ignoring exception. assuming it is just a primary key violation
			// due to the tentative of inserting a duplicated user
			LOGGER.warn("Assuming {} already exists (activate trace log for details)", username);
			LOGGER.trace("Assuming existing record", e);
		}
		return request;
	}

	private SignupRequest signup(final String username, final String password, final String firstName,
			final String lastName, final String email, final String mecanographicNumber) {
		SignupRequest request = null;
		try {
			request = signupController.signup(username, password, firstName, lastName, email, mecanographicNumber);
		} catch (final ConcurrencyException | IntegrityViolationException e) {
			// ignoring exception. assuming it is just a primary key violation
			// due to the tentative of inserting a duplicated user
			LOGGER.warn("Assuming {} already exists (activate trace log for details)", username);
			LOGGER.trace("Assuming existing record", e);
		}
		return request;
	}
}
