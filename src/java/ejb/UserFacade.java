/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.QueryNames;
import entity.User;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author olga
 */
@Stateless
@Local
public class UserFacade extends AbstractFacade<User> implements IUserFacade {

    @PersistenceContext(name = "IASBP-test_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

    /*
     * public User getUserByLoginAndPasswd (String login, String passwd){ Long
     * id = new Long (1); return new User (id, "55", "55");
    }
     */
    public User getUserByLoginAndPassword(String login, String passwd)
            throws IllegalArgumentException {

        if (login == null) {
            throw new IllegalArgumentException("UserJpa.ErrorLoginNull"); //$NON-NLS-1$
        }
        if (passwd == null) {
            throw new IllegalArgumentException("UserJpa.ErrorPasswordNull"); //$NON-NLS-1$
        }
        try {
            List<User> ls = em.createNamedQuery(QueryNames.USER_GET_BY_LOGIN_PASSWD).setParameter(1, login).setParameter(2, passwd).getResultList();

            if (ls == null || ls.isEmpty() == true) {
                return null;
            }
            return ls.get(0);

        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public User getUserByLogin(String login)
            throws IllegalArgumentException {

        if (login == null) {
            throw new IllegalArgumentException("UserJpa.ErrorLoginNull"); //$NON-NLS-1$
        }

        try {
            List<User> ls = em.createNamedQuery(QueryNames.USER_GET_BY_LOGIN).setParameter(1, login).getResultList();

            if (ls == null || ls.isEmpty() == true) {
                return null;
            }
            return ls.get(0);

        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
}
