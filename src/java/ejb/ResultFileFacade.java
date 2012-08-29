/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.QueryNames;
import entity.ResultFile;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author olga
 */
@Stateless
public class ResultFileFacade extends AbstractFacade<ResultFile> {

    @PersistenceContext(unitName = "IASBP-GridPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ResultFileFacade() {
        super(ResultFile.class);
    }

    public void change(ResultFile resultFile) {
        em.merge(resultFile);
    }
    public ResultFile getByName (String name) throws IllegalArgumentException {
        if (name == null) {
            throw new IllegalArgumentException("ResultFileJpa.ErrorNameNull");
        }
        try {
            List<ResultFile> ls = em.createNamedQuery(QueryNames.RESULT_GET_BY_NAME).setParameter(1, name).getResultList();
            if (ls == null || ls.isEmpty() == true) {
                return null;
            }
            return ls.get(0);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
}
