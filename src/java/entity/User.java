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
@Table(name = "USR")
@NamedQueries({
    @NamedQuery(name = QueryNames.USER_GET_BY_LOGIN_PASSWD, query = "SELECT x FROM User x WHERE x.login = ?1 AND x.passwd = ?2"),
    @NamedQuery(name = QueryNames.USER_GET_BY_LOGIN, query = "SELECT x FROM User x WHERE x.login = ?1")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String login;
    @Enumerated
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
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.USR[ id=" + id + " ]";
    }
}
