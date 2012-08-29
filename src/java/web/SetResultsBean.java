/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.ResultFileFacade;
import ejb.UploadedFileFacade;
import entity.ResultFile;
import entity.SipUploadedFile;
import java.util.*;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author olga
 */
@ManagedBean
@SessionScoped
public class SetResultsBean {

    @ManagedProperty("#{resultFileUploadBean}")
    private web.ResultFileUploadBean resultFileUploadBean;

    public boolean isChanging() {
        return changing;
    }

    public void setChanging(boolean changing) {
        this.changing = changing;
    }
    private boolean changing = false;

    public UploadedTableData getCurrentFile() {
        return currentFile;
    }

    public UploadedTableDataSetted getCurrentFileForChange() {
        return currentFileForChange;
    }

    public void setCurrentFileForChange(UploadedTableDataSetted currentFileForChange) {
        this.currentFileForChange = currentFileForChange;
    }

    public void setCurrentFile(UploadedTableData currentFile) {
        this.currentFile = currentFile;
    }
    private UploadedTableData currentFile;
    private UploadedTableDataSetted currentFileForChange;

    public boolean isSetting() {
        return setting;

    }

    public void setSetting(boolean setting) {
        this.setting = setting;
    }
    private List<SipUploadedFile> unsetted;
    private List<SipUploadedFile> setted;

    public List<SipUploadedFile> getSetted() {
        return uf.getAllSetted();
    }

    public void setSetted(List<SipUploadedFile> setted) {
        this.setted = setted;
    }

    public List<UploadedTableDataSetted> getTableDataSetted() {
        if (this.tableDataSetted != null && this.changing == true) {
            return this.tableDataSetted;
        }
        return initTableDataSetted();
    }

    public void setTableDataSetted(List<UploadedTableDataSetted> tableDataSetted) {
        this.tableDataSetted = tableDataSetted;
    }
    private List<UploadedTableData> tableData = new ArrayList<UploadedTableData>();
    private List<UploadedTableDataSetted> tableDataSetted = new ArrayList<UploadedTableDataSetted>();

    public List<UploadedTableData> getTableData() {

        if (this.tableData != null && this.setting == true) {
            return this.tableData;
        }

        return initTableData();
    }

    public List<UploadedTableData> initTableData() {

        this.tableData.clear();
        Iterator<SipUploadedFile> fileIter = getUnsetted().iterator();
        while (fileIter.hasNext()) {
            UploadedTableData td = new UploadedTableData();
            td.setName(fileIter.next().getName());
            td.setStatus("Set");
            tableData.add(td);
        }
        return tableData;
    }

    public List<UploadedTableDataSetted> initTableDataSetted() {

        this.tableDataSetted.clear();
        Iterator<SipUploadedFile> fileIter = getSetted().iterator();
        SipUploadedFile curFile;
        while (fileIter.hasNext()) {
            UploadedTableDataSetted td = new UploadedTableDataSetted();
            curFile = fileIter.next();
            td.setFileName(curFile.getName());
            td.setResultFileName(curFile.getResultFile().getName());
            td.setStatus("Change");
            tableDataSetted.add(td);
        }
        return tableDataSetted;
    }

    public void setTableData(List<UploadedTableData> tableData) {
        this.tableData = tableData;
    }
    private boolean setting = false;
    @EJB
    private UploadedFileFacade uf;
    @EJB
    private ResultFileFacade resultFileFacade;

    public ResultFileFacade getResultFileFacade() {
        return resultFileFacade;
    }

    public void setResultFileFacade(ResultFileFacade resultFileFacade) {
        this.resultFileFacade = resultFileFacade;
    }

    public ResultFileUploadBean getResultFileUploadBean() {
        return resultFileUploadBean;
    }

    public void setResultFileUploadBean(ResultFileUploadBean resultFileUploadBean) {
        this.resultFileUploadBean = resultFileUploadBean;
    }

    public UploadedFileFacade getUf() {
        return uf;
    }

    public void setUf(UploadedFileFacade uf) {
        this.uf = uf;
    }

    public List<SipUploadedFile> getUnsetted() {
        return uf.getAllUnsetted();
    }

    public void setUnsetted(List<SipUploadedFile> unsetted) {
        this.unsetted = unsetted;
    }

    /**
     * Creates a new instance of SetResultsBean
     */
    public SetResultsBean() {
    }

    public void resetAllToSet() {
        Iterator<UploadedTableData> dataIter = getTableData().iterator();
        while (dataIter.hasNext()) {
            dataIter.next().setStatus("Set");
        }

    }

    public void resetAllToChange() {
        Iterator<UploadedTableDataSetted> dataIter = getTableDataSetted().iterator();
        while (dataIter.hasNext()) {
            dataIter.next().setStatus("Change");
        }
    }

    public String setResult() {
        //      SipUploadedFile file = (SipUploadedFile) dataTable.getRowData();
        //     setCurrentFile(file);
        this.setting = true;
        if (getTableData().contains(currentFile)) {
            resetAllToSet();
            int num = tableData.indexOf(currentFile);
            tableData.get(num).setStatus("Setting");
        }
        System.out.println(currentFile.getName());
        //  currentFile.setName("TTT");

        //   this.setting = false;

        return "setSipResult";
    }

    public String doCancel() {

        resetAllToSet();
        this.setting = false;
        return "setSipResult";
    }

    public String doCommit() {

        SipUploadedFile sipFile = uf.getByName(getCurrentFile().getName());
        if (resultFileUploadBean.isAlreadyExists()) {
            FacesMessage facesMessage = new FacesMessage("File with the same name already exists. Change name");
            facesMessage.setSeverity(facesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage("sendResult", facesMessage); //$NON-NLS-1$
            return null;
        }
        resultFileUploadBean.getCurrentFileResult().setSipUploadedFile(sipFile);

        sipFile.setResultFile(resultFileUploadBean.getCurrentFileResult());

        resultFileFacade.create(resultFileUploadBean.getCurrentFileResult());
        uf.change(sipFile);
        getCurrentFile().setStatus("Set");
        setSetting(false);

        return "setSipResult";
    }

    public String changeResult() {
        //      SipUploadedFile file = (SipUploadedFile) dataTable.getRowData();
        //     setCurrentFile(file);
        this.changing = true;
        if (getTableDataSetted().contains(currentFileForChange)) {
            resetAllToChange();
            int num = tableDataSetted.indexOf(currentFileForChange);
            tableDataSetted.get(num).setStatus("Changing");
        }
        //   System.out.println(currentFile.getName());
        //  currentFile.setName("TTT");

        //   this.setting = false;

        return "setSipResult";
    }

    public String doChange() {

        SipUploadedFile sipFileForChange = uf.getByName(getCurrentFileForChange().getFileName());
        if (resultFileUploadBean.isAlreadyExists()) {
            FacesMessage facesMessage = new FacesMessage("File with the same name already exists. Change name");
            facesMessage.setSeverity(facesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage("changeResult", facesMessage); //$NON-NLS-1$
            return null;
        }
        sipFileForChange.setResultFile(null);
        uf.change(sipFileForChange);
        ResultFile oldResult = resultFileFacade.getByName(getCurrentFileForChange().getResultFileName());
        resultFileFacade.remove(oldResult);

        resultFileUploadBean.getCurrentFileResult().setSipUploadedFile(sipFileForChange);

        sipFileForChange.setResultFile(resultFileUploadBean.getCurrentFileResult());

        resultFileFacade.create(resultFileUploadBean.getCurrentFileResult());
        uf.change(sipFileForChange);
        getCurrentFileForChange().setStatus("Change");
        setChanging(false);

        return "setSipResult";
    }

    public String doCancelChange() {

        resetAllToChange();
        this.changing = false;
        return "setSipResult";
    }
}
