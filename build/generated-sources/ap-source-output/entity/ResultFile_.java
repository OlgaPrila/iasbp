package entity;

import entity.SipUploadedFile;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-12-20T10:18:45")
@StaticMetamodel(ResultFile.class)
public class ResultFile_ extends DomainObject_ {

    public static volatile SingularAttribute<ResultFile, String> name;
    public static volatile SingularAttribute<ResultFile, String> path;
    public static volatile SingularAttribute<ResultFile, Long> length_;
    public static volatile SingularAttribute<ResultFile, String> mime;
    public static volatile SingularAttribute<ResultFile, SipUploadedFile> sipUploadedFile;

}