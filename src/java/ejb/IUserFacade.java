/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.User;
import javax.ejb.Local;

/**
 *
 * @author olga
 */
@Local
public interface IUserFacade extends IAbstractFacade<User> {
    public User getUserByLoginAndPassword(String login, String passwd)
            throws IllegalArgumentException;
    
     public User getUserByLogin(String login)
            throws IllegalArgumentException;
    
}
