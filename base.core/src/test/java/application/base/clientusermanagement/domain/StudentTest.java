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
package application.base.clientusermanagement.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import application.base.usermanagement.domain.Student;
import application.base.usermanagement.domain.StudentBuilder;
import application.base.usermanagement.domain.UserBuilderHelper;
import application.base.usermanagement.domain.valueobjects.MecanographicNumber;
import org.junit.Test;

import application.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.domain.model.NilPasswordPolicy;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;

/**
 * Created by Nuno Bettencourt [NMB] on 03/04/16.
 */
public class StudentTest {

    private final String aMecanographicNumber = "abc";
    private final String anotherMecanographicNumber = "xyz";

    public static SystemUser dummyUser(final String username, final Role... roles) {
        // should we load from spring context?
        final SystemUserBuilder userBuilder = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder());
        return userBuilder.with(username, "duMMy1", "dummy", "dummy", "a@b.ro").withRoles(roles).build();
    }

    private SystemUser getNewDummyUser() {
        return dummyUser("dummy", BaseRoles.MANAGER);
    }

    private SystemUser getNewDummyUserTwo() {
        return dummyUser("dummy-two", BaseRoles.MANAGER);
    }

    @Test
    public void ensureClientUserEqualsPassesForTheSameMecanographicNumber() throws Exception {

        final Student aStudent = new StudentBuilder().withMecanographicNumber("DUMMY")
                .withSystemUser(getNewDummyUser()).build();

        final Student anotherStudent = new StudentBuilder().withMecanographicNumber("DUMMY")
                .withSystemUser(getNewDummyUser()).build();

        final boolean expected = aStudent.equals(anotherStudent);

        assertTrue(expected);
    }

    @Test
    public void ensureClientUserEqualsFailsForDifferenteMecanographicNumber() throws Exception {
        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.MANAGER);

        final Student aStudent = new StudentBuilder().withMecanographicNumber(aMecanographicNumber)
                .withSystemUser(getNewDummyUser()).build();

        final Student anotherStudent = new StudentBuilder()
                .withMecanographicNumber(anotherMecanographicNumber).withSystemUser(getNewDummyUser()).build();

        final boolean expected = aStudent.equals(anotherStudent);

        assertFalse(expected);
    }

    @Test
    public void ensureClientUserEqualsAreTheSameForTheSameInstance() throws Exception {
        final SystemUserBuilder userBuilder1 = UserBuilderHelper.builder();
        final SystemUser student = userBuilder1
                .withUsername("student")
                .withPassword("Password1")
                .withName("studentFstName", "studentlSTname")
                .withEmail("student@gmail.com")
                .withRoles(BaseRoles.STUDENT)
                .build();

        final Student aStudent = new Student(student, new MecanographicNumber("1170264"));

        final boolean expected = aStudent.equals(aStudent);

        assertTrue(expected);
    }

    @Test
    public void ensureClientUserEqualsFailsForDifferenteObjectTypes() throws Exception {
        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.MANAGER);

        final Student aStudent = new StudentBuilder().withMecanographicNumber("DUMMY")
                .withSystemUser(getNewDummyUser()).build();

        final boolean expected = aStudent.equals(getNewDummyUser());

        assertFalse(expected);
    }

    @Test
    public void ensureClientUserIsTheSameAsItsInstance() throws Exception {
        final Student aStudent = new StudentBuilder().withMecanographicNumber("DUMMY")
                .withSystemUser(getNewDummyUser()).build();

        final boolean expected = aStudent.sameAs(aStudent);

        assertTrue(expected);
    }

    @Test
    public void ensureTwoClientUserWithDifferentMecanographicNumbersAreNotTheSame() throws Exception {
        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.MANAGER);
        final Student aStudent = new StudentBuilder().withMecanographicNumber(aMecanographicNumber)
                .withSystemUser(getNewDummyUser()).build();

        final Student anotherStudent = new StudentBuilder()
                .withMecanographicNumber(anotherMecanographicNumber).withSystemUser(getNewDummyUser()).build();

        final boolean expected = aStudent.sameAs(anotherStudent);

        assertFalse(expected);
    }
}
