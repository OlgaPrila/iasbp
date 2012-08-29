/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

/**
 *
 * @author olga
 */
public class UploadedTableDataSetted {
    private String fileName;
    private String resultFileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getResultFileName() {
        return resultFileName;
    }

    public void setResultFileName(String resultFileName) {
        this.resultFileName = resultFileName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    private String status = "Change";
    
}
