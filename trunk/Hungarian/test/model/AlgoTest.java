/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.Vector;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author andre
 */
public class AlgoTest {

    public AlgoTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception
    {
    }

    @AfterClass
    public static void tearDownClass() throws Exception
    {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of init method, of class Algo.
     */
    @Test
    public void testInit()
    {
        System.out.println("init");
        int[][] tab = null;
        boolean preference = false;
        int taille = 0;
        Algo instance = null;
        instance.init(tab, preference, taille);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resolveMatrix method, of class Algo.
     */
    // DISABLED @Test
    public void testResolveMatrix()
    {
        System.out.println("resolveMatrix");
        Algo instance = null;
        instance.resolveMatrix();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of goToNextStep method, of class Algo.
     */
    // DISABLED @Test
    public void testGoToNextStep()
    {
        System.out.println("goToNextStep");
        Algo instance = null;
        instance.goToNextStep();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getResolvedMatrix method, of class Algo.
     */
    @Test
    public void testGetResolvedMatrix()
    {
        System.out.println("getResolvedMatrix");
        Algo instance = null;
        Vector expResult = null;
        Vector result = instance.getResolvedMatrix();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTabMarkedZero method, of class Algo.
     */
    @Test
    public void testGetTabMarkedZero()
    {
        System.out.println("getTabMarkedZero");
        Algo instance = null;
        boolean[][] expResult = null;
        boolean[][] result = instance.getTabMarkedZero();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


    /**
     * Test of getArbre method, of class Algo.
     */
    // DISABLED @Test
    public void testGetArbre()
    {
        System.out.println("getArbre");
        Algo instance = null;
        ArbreNAire expResult = null;
        ArbreNAire result = instance.getArbre();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSoluce method, of class Algo.
     */
    // DISABLED @Test
    public void testGetSoluce()
    {
        System.out.println("getSoluce");
        Algo instance = null;
        Vector expResult = null;
        Vector result = instance.getSoluce();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOneSoluce method, of class Algo.
     */
    // DISABLED @Test
    public void testGetOneSoluce()
    {
        System.out.println("getOneSoluce");
        Algo instance = null;
        boolean[][] expResult = null;
        boolean[][] result = instance.getOneSoluce();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class Algo.
     */
    // DISABLED @Test
    public void testMain()
    {
        System.out.println("main");
        String[] args = null;
        Algo.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isFirstStep method, of class Algo.
     */
    // DISABLED @Test
    public void testIsFirstStep()
    {
        System.out.println("isFirstStep");
        Algo instance = null;
        boolean expResult = false;
        boolean result = instance.isFirstStep();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isLastStep method, of class Algo.
     */
    // DISABLED @Test
    public void testIsLastStep()
    {
        System.out.println("isLastStep");
        Algo instance = null;
        boolean expResult = false;
        boolean result = instance.isLastStep();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}