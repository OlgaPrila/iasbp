/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.UploadedFileFacade;
import entity.SipUploadedFile;
import java.io.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;

@ManagedBean(name = "sipFileUploadBean")
@SessionScoped
public class SipFileUploadBean implements Serializable {

    private static final long serialVersionUID = 8301770521196265438L;
    private ArrayList<SipUploadedFile> files = new ArrayList<SipUploadedFile>();
    private String pathToUploadedFiles = null;
    private String pathToResultFiles = null;
    @ManagedProperty("#{userBean}")
    private web.UserBean userBean;

    public UploadedFileFacade getUploadedFileFacade() {
        return uploadedFileFacade;
    }

    public void setUploadedFileFacade(UploadedFileFacade uploadedFileFacade) {
        this.uploadedFileFacade = uploadedFileFacade;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public String getPathToResultFiles() {
        return pathToResultFiles;
    }

    public void setPathToResultFiles(String pathToResultFiles) {
        this.pathToResultFiles = pathToResultFiles;
    }

    public String getPathToUploadedFiles() {
        return pathToUploadedFiles;
    }

    public void setPathToUploadedFiles(String pathToUploadedFiles) {
        this.pathToUploadedFiles = pathToUploadedFiles;
    }

    /**
     * Creates a new instance of SipFileUploadBean
     */
    public SipFileUploadBean() {
    }
    @EJB
    private UploadedFileFacade uploadedFileFacade;

    public void paint(OutputStream stream, Object object) throws IOException {
        stream.write(getFiles().get((Integer) object).getData_());
        stream.close();
    }

    public void listener(FileUploadEvent event) throws Exception {

        System.out.println("gone");
        UploadedFile item = event.getUploadedFile();
        System.out.println(item.getName());
        SipUploadedFile file = new SipUploadedFile();
        file.setLength_(item.getData().length);
        file.setName(item.getName());
        file.setData_(item.getData());
        files.add(file);

        this.definePaths();
        File f = new File(getPathToUploadedFiles() + item.getName());

        if (f.exists()) {
            System.out.println("FILE EXISTS");
            file.setLoaded(false);

            return;
        }

        f.createNewFile();
        FileOutputStream outFile = new FileOutputStream(getPathToUploadedFiles() + item.getName());
        // записать массив
        outFile.write(item.getData());
        outFile.close();
        file.setPath(getPathToUploadedFiles() + item.getName());
        file.setResultFile(null);
        file.setOwner(userBean.getCurrentUser());
        SipUploadedFile fileForDelete = uploadedFileFacade.getByName(file.getName());
        if (fileForDelete != null) {
            uploadedFileFacade.remove(fileForDelete);
        }
        uploadedFileFacade.create(file);
        file.setLoaded(true);

        System.out.println("File is open to write");

    }

    public String clearUploadData() {
        files.clear();
        return null;
    }

    public int getSize() {
        if (getFiles().size() > 0) {
            return getFiles().size();
        } else {
            return 0;
        }
    }

    public long getTimeStamp() {
        return System.currentTimeMillis();
    }

    public ArrayList<SipUploadedFile> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<SipUploadedFile> files) {
        this.files = files;
    }

    public void definePaths() {
        FacesContext ctx = FacesContext.getCurrentInstance();


        /*
         * ResourceBundle rb = ctx.getApplication().getResourceBundle(ctx,
         * "conf");
         *
         * this.setPathToUploadedFiles(rb.getString("pathToUploadedFiles"));
         * this.setPathToResultFiles(rb.getString("pathToResultFiles"));
         * System.out.println(getPathToUploadedFiles()+getPathToResultFiles());
         */
        String realPath = ctx.getExternalContext().getRealPath("/resources/files/conf.properties");
        try {
            BufferedReader in = new BufferedReader(new FileReader(realPath));
            String str = in.readLine();
            //      System.out.println(str);
            int pos_pathToUploadedFiles = str.lastIndexOf('=');
            String tmp_pathToUploadedFiles = "";
            if (pos_pathToUploadedFiles > 0) {
                tmp_pathToUploadedFiles = str.substring(pos_pathToUploadedFiles + 1);
            }
            //       System.out.println(tmp_pathToUploadedFiles);
            this.setPathToUploadedFiles(tmp_pathToUploadedFiles);
            str = in.readLine();
            System.out.println(str);
            int pos_pathToResultFiles = str.lastIndexOf('=');
            String tmp_pathToResultFiles = "";
            if (pos_pathToResultFiles > 0) {
                tmp_pathToResultFiles = str.substring(pos_pathToResultFiles + 1);
            }
            //    System.out.println(tmp_pathToResultFiles);
            this.setPathToResultFiles(tmp_pathToResultFiles);
        } catch (IOException ex1) {
            Logger.getLogger(SipFileUploadBean.class.getName()).log(Level.SEVERE, null, ex1);
        }
    }
}
