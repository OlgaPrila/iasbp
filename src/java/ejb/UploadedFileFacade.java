/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.QueryNames;
import entity.SipUploadedFile;
import entity.User;
import java.util.ArrayList;
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
public class UploadedFileFacade extends AbstractFacade<SipUploadedFile> {

    @PersistenceContext(unitName = "IASBP-test_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UploadedFileFacade() {
        super(SipUploadedFile.class);
        

    }

    public List<SipUploadedFile> getAllUnsetted() {
        //    List<SipUploadedFile> rez = new ArrayList ();
        List<SipUploadedFile> ls = em.createNamedQuery(QueryNames.SIP_GET_ALL_UNSETTED).getResultList();
        return ls;
    }
    
        public List<SipUploadedFile> getAllSetted() {
        //    List<SipUploadedFile> rez = new ArrayList ();
        List<SipUploadedFile> ls = em.createNamedQuery(QueryNames.SIP_GET_ALL_SETTED).getResultList();
        return ls;
    }

    public SipUploadedFile getByName(String name) throws IllegalArgumentException {
        if (name == null) {
            throw new IllegalArgumentException("SipUploadedFileJpa.ErrorNameNull");
        }
        try {
            List<SipUploadedFile> ls = em.createNamedQuery(QueryNames.SIP_GET_BY_NAME).setParameter(1, name).getResultList();
            if (ls == null || ls.isEmpty() == true) {
                return null;
            }
            return ls.get(0);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    public void change (SipUploadedFile sipUploadedFile) {
        em.merge(sipUploadedFile);
    }
    
     public List<SipUploadedFile> getUserLoadedFiles (User user) throws IllegalArgumentException  {
         
        if (user == null) {
            throw new IllegalArgumentException("SipUploadedFileJpa.ErrorUserNull");
        }
        try {
            List<SipUploadedFile> ls = em.createNamedQuery(QueryNames.SIP_GET_BY_USER).setParameter(1, user).getResultList();
            if (ls == null || ls.isEmpty() == true) {
                return null;
            }
            return ls;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
}
