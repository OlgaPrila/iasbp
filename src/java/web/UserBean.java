/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.IUserFacade;
import ejb.UserFacade;
import entity.User;
import entity.UserRole;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author olga
 */
@ManagedBean
@SessionScoped
public class UserBean {
    
    private boolean adminAccess;

    public boolean isAdminAccess() {
        if (this.getCurrentUser()==null) return false;
        if (getCurrentUser().getIasbpRole()==UserRole.ADMIN) return true;
        else return false;
    }

    public void setAdminAccess(boolean adminAccess) {
        this.adminAccess = adminAccess;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
    @EJB
    private IUserFacade userFacade;

    public UserBean(String login, String passwd) {
        this.login = login;
        this.passwd = passwd;
    }
    /*
     * public UserFacade getUf() { return userFacade;
    }
     */
    private Long id = null;
    private String login = null;

    public Long getId() {
        return id;
    }
    private String passwd = null;

    public String getLogin() {
        return this.login;
    }

    public String getPasswd() {
        return passwd;
    }

    /**
     * Creates a new instance of UserBean
     */
    public String addUser() {
        User usr = new User(this.login, this.passwd);
      
        User usrExists = userFacade.getUserByLogin(login);
        if (usrExists != null) {
            FacesMessage facesMessage = new FacesMessage("User with such name already exists");
            facesMessage.setSeverity(facesMessage.SEVERITY_ERROR);            
            FacesContext.getCurrentInstance().addMessage("registerForm", facesMessage); //$NON-NLS-1$
            return "addUser";
        }
        userFacade.create(usr);
        User user = userFacade.getUserByLoginAndPassword(login, passwd);
        this.id = user.getId();

        return "welcome";

    }

    public UserBean() {
    }

    public void setUserInfo(User user) {
        if (user != null) {
            this.login = user.getLogin();
            this.passwd = user.getPasswd();
            this.id = user.getId();
            //id = user.getId();
        } else {
            this.login = null;
            this.passwd = null;
            this.id = null;
            //userRole = null;
            //id = null;
        }
    }

    public User getCurrentUser() {
        if (this.getLogin()==null) return null;
        return userFacade.getUserByLoginAndPassword(login, passwd);
    }
    

}
