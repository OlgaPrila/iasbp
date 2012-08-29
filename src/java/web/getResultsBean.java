/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.UploadedFileFacade;
import entity.SipUploadedFile;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author olga
 */
@ManagedBean
@SessionScoped
public class getResultsBean {

    private List<SipUploadedFile> loadedFiles;
    
    @EJB
    private UploadedFileFacade uf;

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
    
    @ManagedProperty("#{userBean}")
    private web.UserBean userBean;

    public UploadedFileFacade getUf() {
        return uf;
    }

    public void setUf(UploadedFileFacade uf) {
        this.uf = uf;
    }

    public List<SipUploadedFile> getLoadedFiles() {
        return uf.getUserLoadedFiles(userBean.getCurrentUser());
    }

    public void setLoadedFiles(List<SipUploadedFile> loadedFiles) {
        this.loadedFiles = loadedFiles;
    }

    /**
     * Creates a new instance of getResultsBean
     */
    public getResultsBean() {
    }
}
