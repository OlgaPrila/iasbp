package entity;

import entity.ResultFile;
import entity.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-12-20T10:18:45")
@StaticMetamodel(SipUploadedFile.class)
public class SipUploadedFile_ extends DomainObject_ {

    public static volatile SingularAttribute<SipUploadedFile, ResultFile> resultFile;
    public static volatile SingularAttribute<SipUploadedFile, String> name;
    public static volatile SingularAttribute<SipUploadedFile, String> path;
    public static volatile SingularAttribute<SipUploadedFile, User> owner;
    public static volatile SingularAttribute<SipUploadedFile, Long> length_;
    public static volatile SingularAttribute<SipUploadedFile, String> mime;

}