/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author olga
 */
@Entity
@NamedQueries({
    @NamedQuery(name = QueryNames.SIP_GET_ALL_UNSETTED, query = "SELECT x FROM SipUploadedFile x WHERE x.resultFile IS NULL"),
    @NamedQuery(name = QueryNames.SIP_GET_ALL_SETTED, query = "SELECT x FROM SipUploadedFile x WHERE x.resultFile IS NOT NULL"),
    @NamedQuery(name = QueryNames.SIP_GET_BY_NAME, query = "SELECT x FROM SipUploadedFile x WHERE x.name = ?1"),
    @NamedQuery(name = QueryNames.SIP_GET_BY_USER, query = "SELECT x FROM SipUploadedFile x WHERE x.owner = ?1")})
public class SipUploadedFile implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String mime;
    private long length_;
    @Transient
    private byte[] data_;
    @Transient
    private boolean loaded;

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ResultFile getResultFile() {
        return resultFile;
    }

    public void setResultFile(ResultFile resultFile) {
        this.resultFile = resultFile;
    }
    private String path;
    @OneToOne
    private ResultFile resultFile;
    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    private User owner;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        int extDot = name.lastIndexOf('.');
        if (extDot > 0) {
            String extension = name.substring(extDot + 1);
            if ("txt".equals(extension)) {
                mime = "txt";
            } else {
                mime = "image/unknown";
            }

        }
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMime() {
        return mime;
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
        if (!(object instanceof SipUploadedFile)) {
            return false;
        }
        SipUploadedFile other = (SipUploadedFile) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.UploadedFile[ id=" + id + " ]";
    }
}
