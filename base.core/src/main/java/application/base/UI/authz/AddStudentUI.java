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
package application.base.UI.authz;

import application.base.usermanagement.application.student.AcceptRefuseSignupFactory;
import application.base.usermanagement.application.student.AcceptRefuseSignupRequestController;
import application.base.usermanagement.application.student.SignupController;
import application.base.usermanagement.application.teacher.AddTeacherController;
import application.base.usermanagement.domain.BaseRoles;
import application.base.usermanagement.domain.SignupRequest;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.HashSet;
import java.util.Set;

public class AddStudentUI extends AbstractUI {

    private final SignupController signupController = new SignupController();
    private final AcceptRefuseSignupRequestController acceptController = AcceptRefuseSignupFactory.build();

    @Override
    protected boolean doShow() {
        final String username = Console.readLine("Username: ");
        final String password = Console.readLine("Password: ");
        final String firstName = Console.readLine("First Name: ");
        final String lastName = Console.readLine("Last Name: ");
        final String email = Console.readLine("E-Mail: ");
        final String mecanographicNumber = Console.readLine("Mecanographic Number: ");
        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.STUDENT);

        SignupRequest request = null;
        try {
            request = signupController.signup(username, password, firstName, lastName, email, mecanographicNumber);
            acceptController.acceptSignupRequest(request);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println("That username is already in use.");
        }

        return false;
    }


    @Override
    public String headline() {
        return "Add Student";
    }
}
