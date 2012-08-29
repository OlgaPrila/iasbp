/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.ResultFileFacade;
import ejb.UploadedFileFacade;
import entity.ResultFile;
import entity.SipUploadedFile;
import java.io.*;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;

/**
 *
 * @author olga
 */
@ManagedBean(name = "resultFileUploadBean")
@SessionScoped
public class ResultFileUploadBean implements Serializable {

    private static final long serialVersionUID = 8301770521196265438L;
    private ArrayList<ResultFile> files = new ArrayList<ResultFile>();
    private String pathToUploadedFiles = null;
    private String pathToResultFiles = null;

    public boolean isAlreadyExists() {
        return alreadyExists;
    }

    public void setAlreadyExists(boolean alreadyExists) {
        this.alreadyExists = alreadyExists;
    }
    private boolean alreadyExists = false;
    private ResultFile currentFileResult;

    public ResultFile getCurrentFileResult() {
        return currentFileResult;
    }

    public void setCurrentFileResult(ResultFile currentFileResult) {
        this.currentFileResult = currentFileResult;
    }

    public ResultFileFacade getResultFileFacade() {
        return resultFileFacade;
    }

    public void setResultFileFacade(ResultFileFacade resultFileFacade) {
        this.resultFileFacade = resultFileFacade;
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
    public ResultFileUploadBean() {
    }
    @EJB
    private ResultFileFacade resultFileFacade;

    public UploadedFileFacade getUploadedFileFacade() {
        return uploadedFileFacade;
    }

    public void setUploadedFileFacade(UploadedFileFacade uploadedFileFacade) {
        this.uploadedFileFacade = uploadedFileFacade;
    }
    @EJB
    private UploadedFileFacade uploadedFileFacade;

    public void paint(OutputStream stream, Object object) throws IOException {
        stream.write(getFiles().get((Integer) object).getData_());
        stream.close();
    }

    public void listener(FileUploadEvent event) throws Exception {

        System.out.println("ResultUpload GONE");
        UploadedFile item = event.getUploadedFile();
        System.out.println(item.getName());

        ResultFile file = new ResultFile();

        //     SipUploadedFile file = new SipUploadedFile();
        file.setLength_(item.getData().length);
        file.setName(item.getName());
        file.setData_(item.getData());
        files.add(file);

        this.definePaths();
        File f = new File(getPathToResultFiles() + item.getName());

        if (f.exists()) {

            setAlreadyExists(true);
            files.remove(file);
            return;
        }
        setAlreadyExists(false);
        f.createNewFile();
        FileOutputStream outFile = new FileOutputStream(getPathToResultFiles() + item.getName());
        // записать массив
        outFile.write(item.getData());
        outFile.close();
        file.setPath(getPathToResultFiles() + item.getName());

        this.currentFileResult = file;


    }

    public String afterLoaded() {
        return "setSipResult";
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

    public ArrayList<ResultFile> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<ResultFile> files) {
        this.files = files;
    }

    public void definePaths() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        String realPath = ctx.getExternalContext().getRealPath("/resources/files/conf.properties");
        //    System.out.println (realPath);




        /*
         * ResourceBundle rb = ctx.getApplication().getResourceBundle(ctx,
         * "conf");
         *
         * this.setPathToUploadedFiles(rb.getString("pathToUploadedFiles"));
         * this.setPathToResultFiles(rb.getString("pathToResultFiles"));
         * System.out.println(getPathToUploadedFiles()+getPathToResultFiles());
         */



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
