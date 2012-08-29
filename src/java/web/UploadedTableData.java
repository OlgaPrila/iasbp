/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

/**
 *
 * @author olga
 */
public class UploadedTableData {

    public String getName() {
        return name;
    }

    public UploadedTableData() {
        
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    private String name;
    private String status = "Set";
    
}
