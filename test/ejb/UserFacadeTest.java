/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.User;
import javax.ejb.embeddable.EJBContainer;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author olga
 */
public class UserFacadeTest {

    private static EJBContainer container;
    private static IUserFacade instance;

    public UserFacadeTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        instance = (IUserFacade) container.getContext().lookup("java:global/classes/UserFacade");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
  //      container.close();
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class UserFacade.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        User entity = new User ("test","test");        
        instance.create(entity);
        Long id = entity.getId();
        assertEquals(entity, instance.find(id));
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of edit method, of class UserFacade.
     */
  /*  @Test
    public void testEdit() throws Exception {
        System.out.println("edit");
        User entity = null;
        UserFacade instance = new UserFacade();
        instance.edit(entity);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class UserFacade.
     */
 /*   @Test
    public void testRemove() throws Exception {
        System.out.println("remove");
        User entity = null;
        UserFacade instance = new UserFacade();
        instance.remove(entity);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of find method, of class UserFacade.
     */
 /*   @Test
    public void testFind() throws Exception {
        System.out.println("find");
        Object id = null;
        UserFacade instance = new UserFacade();
        User expResult = null;
        User result = instance.find(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserByLoginAndPassword method, of class UserFacade.
     */
   /* @Test
    public void testGetUserByLoginAndPassword() throws Exception {
        System.out.println("getUserByLoginAndPassword");
        String login = "";
        String passwd = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        IUserFacade instance = (IUserFacade) container.getContext().lookup("java:global/classes/UserFacade");
        User expResult = null;
        User result = instance.getUserByLoginAndPassword(login, passwd);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserByLogin method, of class UserFacade.
     */
   /* @Test
    public void testGetUserByLogin() throws Exception {
        System.out.println("getUserByLogin");
        String login = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        IUserFacade instance = (IUserFacade) container.getContext().lookup("java:global/classes/UserFacade");
        User expResult = null;
        User result = instance.getUserByLogin(login);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
}
