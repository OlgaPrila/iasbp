/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.DomainObject;
import javax.ejb.Local;

/**
 *
 * @author olga
 */
@Local
interface IAbstractFacade<T extends DomainObject> {

    public void create(T entity);

    public void edit(T entity);

    public void remove(T entity);

    public T find(Object id);
}
