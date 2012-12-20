package entity;

import entity.UserRole;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-12-20T10:18:45")
@StaticMetamodel(User.class)
public class User_ extends DomainObject_ {

    public static volatile SingularAttribute<User, String> passwd;
    public static volatile SingularAttribute<User, UserRole> iasbpRole;
    public static volatile SingularAttribute<User, String> login;

}