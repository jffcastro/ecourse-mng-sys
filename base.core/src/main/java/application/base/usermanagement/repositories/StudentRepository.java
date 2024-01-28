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
package application.base.usermanagement.repositories;

import java.util.Optional;

import application.base.usermanagement.domain.Student;
import application.base.usermanagement.domain.valueobjects.MecanographicNumber;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;

public interface StudentRepository extends DomainRepository<MecanographicNumber, Student> {

    /**
     * returns the student whose username is given
     *
     * @param username - the username to search for
     * @return optional of student with username
     */
    Optional<Student> findByUsername(Username username);

    /**
     * returns the student with the given mecanographic number
     *
     * @param mecanographicNumber - the mecanographic number to search for
     * @return student with mecanographic number
     */
    Student findByMecanographicNumber(MecanographicNumber mecanographicNumber);

    /**
     * This method is used in US 1007 and returns the student related to
     * a given system user
     *
     * @param systemuser - system user to search for
     * @return student related to system user passed as parameter
     */
    Student findBySystemUser(SystemUser systemuser);

    Iterable<Student> findAllActive();
}
