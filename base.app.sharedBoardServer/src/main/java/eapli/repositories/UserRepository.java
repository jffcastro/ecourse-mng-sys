package eapli.repositories;

import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class UserRepository extends JpaRepository<SystemUser, Username> {
    @Override
    protected String persistenceUnitName() {
        return "eapli.base";
    }

    public boolean findByIdPass(String id, String pass) {
        final TypedQuery<SystemUser> query = entityManager().createQuery(
                "SELECT e FROM SystemUser e where e.username.value = :id and e.password.value = :pass",
                SystemUser.class);
        query.setParameter("id", id);
        query.setParameter("pass", pass);
        try {
            query.getSingleResult();
            return true;
        }catch (NoResultException e){
            return false;
        }
    }
}
