/*
 * Copyright 2010 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.DomainObject;
import java.util.List;
import javax.persistence.EntityManager;


/**
 *
 * @author ian
 */
public abstract class AbstractFacade<T extends DomainObject> {
    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager()
            .persist(entity);
    }

    public void edit(T entity) {
        getEntityManager()
            .merge(entity);
    }

    public void remove(T entity) {
        getEntityManager()
            .remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager()
                   .find(entityClass, id);
    }

  /*  public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager()
                                                          .getCriteriaBuilder()
                                                          .createQuery();
        cq.select(cq.from(entityClass));

        return getEntityManager()
                   .createQuery(cq)
                   .getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager()
                                                          .getCriteriaBuilder()
                                                          .createQuery();
        cq.select(cq.from(entityClass));

        javax.persistence.Query q = getEntityManager()
                                        .createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);

        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager()
                                                          .getCriteriaBuilder()
                                                          .createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));

        javax.persistence.Query q = getEntityManager()
                                        .createQuery(cq);

        return ((Long) q.getSingleResult()).intValue();
    } */
}
