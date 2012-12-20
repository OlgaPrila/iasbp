/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.*;

/**
 *
 * @author olga
 */
@Entity
@Table(name = "USRTEST")
@NamedQueries({
    @NamedQuery(name = QueryNames.USER_GET_BY_LOGIN_PASSWD, query = "SELECT x FROM User x WHERE x.login = ?1 AND x.passwd = ?2"),
    @NamedQuery(name = QueryNames.USER_GET_BY_LOGIN, query = "SELECT x FROM User x WHERE x.login = ?1")})
public class User extends DomainObject {    
    @Column
    private String login;
    @Enumerated(EnumType.STRING)
    private UserRole iasbpRole = UserRole.USER;

    public UserRole getIasbpRole() {
        return iasbpRole;
    }

    public void setIasbpRole(UserRole iasbpRole) {
        this.iasbpRole = iasbpRole;
    }

    public User() {
        super();
    }
    private String passwd;

    public String getLogin() {
        return login;
    }

    public User(String login, String passwd) {
        this.login = login;
        this.passwd = passwd;
        this.iasbpRole = UserRole.USER;
    }

    public User(Long id, String login, String passwd) {
        this.setId(id);
        this.login = login;
        this.passwd = passwd;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}