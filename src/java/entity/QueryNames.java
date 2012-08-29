/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author olga
 */
public abstract class QueryNames {

    public static final String USER_GET_BY_LOGIN = "getByLogin"; //$NON-NLS-1$
    public static final String USER_GET_BY_LOGIN_PASSWD = "getByLoginAndPassword"; //$NON-NLS-1$
    public static final String SIP_GET_ALL_UNSETTED = "getAllUnsetted"; //$NON-NLS-1$
    public static final String SIP_GET_ALL_SETTED = "getAllSetted"; //$NON-NLS-1$
    public static final String SIP_GET_BY_NAME = "getSipByName";
    public static final String SIP_GET_BY_USER = "getSipsByUser";
    public static final String RESULT_GET_BY_NAME = "getResultByName";
}
