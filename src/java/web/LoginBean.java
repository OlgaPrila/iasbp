/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.UserFacade;
import entity.User;


import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import javax.inject.Inject;

/**
 *
 * @author olga
 */
@ManagedBean
@RequestScoped
public class LoginBean {

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public UserFacade getUf() {
        return uf;
    }

    public void setUf(UserFacade uf) {
        this.uf = uf;
    }
    @EJB
    private UserFacade uf;
    @ManagedProperty("#{userBean}")
    private web.UserBean userBean;
    @ManagedProperty("#{sipFileUploadBean}")
    private web.SipFileUploadBean sipFileUploadBean;

    public SipFileUploadBean getSipFileUploadBean() {
        return this.sipFileUploadBean;
    }

    public void setSipFileUploadBean(SipFileUploadBean sipFileUploadBean) {
        this.sipFileUploadBean = sipFileUploadBean;
    }
    private String login;

    public String getLogin() {
        return login;
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
    private String passwd;

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }

    public String doLogin() {

        User user = uf.getUserByLoginAndPassword(login, passwd);
        if (user == null) {
            FacesMessage facesMessage = new FacesMessage("Login or Password Incorrect");
            facesMessage.setSeverity(facesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage("loginForm", facesMessage); //$NON-NLS-1$
            return "welcome";
        } else {

            userBean.setUserInfo(user);

            return "welcome"; //$NON-NLS-1$
        }
    }

    public String doLogout() {
        userBean.setUserInfo(null);
        sipFileUploadBean.getFiles().clear();
        //   sipFileUploadBean.setFiles(get);
        return "home"; //$NON-NLS-1$
    }
}
