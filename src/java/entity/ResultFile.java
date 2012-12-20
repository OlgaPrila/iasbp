/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author olga
 */
@Entity
@NamedQueries({
    @NamedQuery(name = QueryNames.RESULT_GET_BY_NAME, query = "SELECT x FROM ResultFile x WHERE x.name = ?1")})
public class ResultFile extends DomainObject {

    private String path;
    private String name;
    private String mime;
    private long length_;
    @Transient
    private byte[] data_;
    @OneToOne
    private SipUploadedFile sipUploadedFile;

    public byte[] getData_() {
        return data_;
    }

    public void setData_(byte[] data_) {
        this.data_ = data_;
    }

    public long getLength_() {
        return length_;
    }

    public void setLength_(long length_) {
        this.length_ = length_;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SipUploadedFile getSipUploadedFile() {
        return sipUploadedFile;
    }

    public void setSipUploadedFile(SipUploadedFile sipUploadedFile) {
        this.sipUploadedFile = sipUploadedFile;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

   
}
