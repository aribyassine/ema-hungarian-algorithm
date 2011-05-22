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
     * Test of getStepShortDescription method, of class Algo.
     */
    @Test
    public void testGetStepShortDescription()
    {
        System.out.println("getStepShortDescription");
        Algo instance = null;
        String expResult = "";
        String result = instance.getStepShortDescription();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of step10AffectZeroByRow method, of class Algo.
     */
    @Test
    public void testStep10AffectZeroByRow()
    {
        System.out.println("step10AffectZeroByRow");
        Algo instance = null;
        instance.step10AffectZeroByRow();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of step1SubstractAllRow method, of class Algo.
     */
    @Test
    public void testStep1SubstractAllRow()
    {
        System.out.println("step1SubstractAllRow");
        Algo instance = null;
        instance.step1SubstractAllRow();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of step1SubstractRow method, of class Algo.
     */
    @Test
    public void testStep1SubstractRow()
    {
        System.out.println("step1SubstractRow");
        int row = 0;
        Algo instance = null;
        instance.step1SubstractRow(row);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of step2SubstractAllCol method, of class Algo.
     */
    @Test
    public void testStep2SubstractAllCol()
    {
        System.out.println("step2SubstractAllCol");
        Algo instance = null;
        instance.step2SubstractAllCol();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of step2SubstractCol method, of class Algo.
     */
    @Test
    public void testStep2SubstractCol()
    {
        System.out.println("step2SubstractCol");
        int col = 0;
        Algo instance = null;
        instance.step2SubstractCol(col);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of step3SelectMarkZero method, of class Algo.
     */
    @Test
    public void testStep3SelectMarkZero()
    {
        System.out.println("step3SelectMarkZero");
        Algo instance = null;
        boolean expResult = false;
        boolean result = instance.step3SelectMarkZero();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of step4MarkRow method, of class Algo.
     */
    @Test
    public void testStep4MarkRow()
    {
        System.out.println("step4MarkRow");
        Algo instance = null;
        instance.step4MarkRow();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of step5MarkCol method, of class Algo.
     */
    @Test
    public void testStep5MarkCol()
    {
        System.out.println("step5MarkCol");
        Algo instance = null;
        instance.step5MarkCol();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of step6MarkRowCol method, of class Algo.
     */
    @Test
    public void testStep6MarkRowCol()
    {
        System.out.println("step6MarkRowCol");
        Algo instance = null;
        instance.step6MarkRowCol();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of step7Iterate method, of class Algo.
     */
    @Test
    public void testStep7Iterate()
    {
        System.out.println("step7Iterate");
        Algo instance = null;
        instance.step7Iterate();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of step8StrikeRowCol method, of class Algo.
     */
    @Test
    public void testStep8StrikeRowCol()
    {
        System.out.println("step8StrikeRowCol");
        Algo instance = null;
        instance.step8StrikeRowCol();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of step9SubstractNoMark method, of class Algo.
     */
    @Test
    public void testStep9SubstractNoMark()
    {
        System.out.println("step9SubstractNoMark");
        Algo instance = null;
        instance.step9SubstractNoMark();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of step10Affect0Mark method, of class Algo.
     */
    @Test
    public void testStep10Affect0Mark()
    {
        System.out.println("step10Affect0Mark");
        Algo instance = null;
        boolean expResult = false;
        boolean result = instance.step10Affect0Mark();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of step11Affect0Soluce method, of class Algo.
     */
    @Test
    public void testStep11Affect0Soluce()
    {
        System.out.println("step11Affect0Soluce");
        Algo instance = null;
        boolean expResult = false;
        boolean result = instance.step11Affect0Soluce();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isPreference method, of class Algo.
     */
    @Test
    public void testIsPreference()
    {
        System.out.println("isPreference");
        Algo instance = null;
        boolean expResult = false;
        boolean result = instance.isPreference();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPreference method, of class Algo.
     */
    @Test
    public void testSetPreference()
    {
        System.out.println("setPreference");
        boolean preference = false;
        Algo instance = null;
        instance.setPreference(preference);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTab method, of class Algo.
     */
    @Test
    public void testGetTab()
    {
        System.out.println("getTab");
        Algo instance = null;
        int[][] expResult = null;
        int[][] result = instance.getTab();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTab method, of class Algo.
     */
    @Test
    public void testSetTab()
    {
        System.out.println("setTab");
        int[][] tab = null;
        Algo instance = null;
        instance.setTab(tab);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resolveMatrix method, of class Algo.
     */
    @Test
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
    @Test
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
     * Test of getMarkCol method, of class Algo.
     */
    @Test
    public void testGetMarkCol()
    {
        System.out.println("getMarkCol");
        Algo instance = null;
        boolean[] expResult = null;
        boolean[] result = instance.getMarkCol();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMarkRow method, of class Algo.
     */
    @Test
    public void testGetMarkRow()
    {
        System.out.println("getMarkRow");
        Algo instance = null;
        boolean[] expResult = null;
        boolean[] result = instance.getMarkRow();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTabTemp method, of class Algo.
     */
    @Test
    public void testGetTabTemp()
    {
        System.out.println("getTabTemp");
        Algo instance = null;
        int[][] expResult = null;
        int[][] result = instance.getTabTemp();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArbre method, of class Algo.
     */
    @Test
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
    @Test
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
    @Test
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
    @Test
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
    @Test
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
    @Test
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

    /**
     * Test of addAlgoModelListener method, of class Algo.
     */
    @Test
    public void testAddAlgoModelListener()
    {
        System.out.println("addAlgoModelListener");
        AlgoModelListener algoModelListener = null;
        Algo instance = null;
        instance.addAlgoModelListener(algoModelListener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeAlgoModelListener method, of class Algo.
     */
    @Test
    public void testRemoveAlgoModelListener()
    {
        System.out.println("removeAlgoModelListener");
        AlgoModelListener algoModelListener = null;
        Algo instance = null;
        instance.removeAlgoModelListener(algoModelListener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}