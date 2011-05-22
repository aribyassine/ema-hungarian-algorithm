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
    // DISABLED @Test
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
     * This is the main test of the Algo class
     */
    @Test
    public void testGetResolvedMatrix1()
    {
        System.out.println("getResolvedMatrix");

        int matrix1[][] = {{1,2,3,4,5},{1,4,2,5,3},{3,2,1,5,4},{1,2,3,5,4},{2,1,4,3,5}};
        Algo algorithm = new Algo(matrix1, true, matrix1.length);
        Vector<boolean[][]> expectedResolvedMatrices = new Vector<boolean[][]>();
        boolean resolvedMatrix1[][] = {
            {true, false, false, false, false},
            {false, false, false, false, true},
            {false, false, true, false, false},
            {false, true, false, false, false},
            {false, false, false, true, false}};
        boolean resolvedMatrix2[][] = {
            {false, true, false, false, false},
            {false, false, false, false, true},
            {false, false, true, false, false},
            {true, false, false, false, false},
            {false, false, false, true, false}};
        boolean resolvedMatrix3[][] = {
            {false, false, false, true, false},
            {false, false, false, false, true},
            {false, false, true, false, false},
            {true, false, false, false, false},
            {false, true, false, false, false}};
        expectedResolvedMatrices.add(resolvedMatrix1);
        expectedResolvedMatrices.add(resolvedMatrix2);
        expectedResolvedMatrices.add(resolvedMatrix3);

        Vector<boolean[][]> result = algorithm.getResolvedMatrix();
        for(int i=0; i<result.size(); i++)
        {
            // System.out.println("Matrix: " + (i+1));
            // algorithm.affiche(result.get(i));
            assertArrayEquals(result.get(i), expectedResolvedMatrices.get(i));
        }
    }

    /**
     * Test of getResolvedMatrix method, of class Algo.
     * This is the main test of the Algo class
     */
    @Test
    public void testGetResolvedMatrix2()
    {
        System.out.println("getResolvedMatrix");

        int matrix2[][] = {{4,5,3,2,3},{3,2,4,3,4},{3,3,4,4,3},{2,4,3,2,4},{2,1,3,4,3}};
        Algo algorithm = new Algo(matrix2, true, matrix2.length);
        Vector<boolean[][]> expectedResolvedMatrices = new Vector<boolean[][]>();
        boolean resolvedMatrix1[][] = {
            {false, false, true, false, false},
            {true, false, false, false, false},
            {false, false, false, false, true},
            {false, false, false, true, false},
            {false, true, false, false, false}};
        boolean resolvedMatrix2[][] = {
            {false, false, true, false, false},
            {false, true, false, false, false},
            {false, false, false, false, true},
            {false, false, false, true, false},
            {true, false, false, false, false}};
        boolean resolvedMatrix3[][] = {
            {false, false, true, false, false},
            {false, false, false, true, false},
            {false, false, false, false, true},
            {true, false, false, false, false},
            {false, true, false, false, false}};
        boolean resolvedMatrix4[][] = {
            {false, false, false, true, false},
            {true, false, false, false, false},
            {false, false, false, false, true},
            {false, false, true, false, false},
            {false, true, false, false, false}};
        boolean resolvedMatrix5[][] = {
            {false, false, false, true, false},
            {false, true, false, false, false},
            {false, false, false, false, true},
            {true, false, false, false, false},
            {false, false, true, false, false}};
        boolean resolvedMatrix6[][] = {
            {false, false, false, true, false},
            {false, true, false, false, false},
            {false, false, false, false, true},
            {false, false, true, false, false},
            {true, false, false, false, false}};
        boolean resolvedMatrix7[][] = {
            {false, false, false, true, false},
            {false, false, true, false, false},
            {false, false, false, false, true},
            {true, false, false, false, false},
            {false, true, false, false, false}};
        expectedResolvedMatrices.add(resolvedMatrix1);
        expectedResolvedMatrices.add(resolvedMatrix2);
        expectedResolvedMatrices.add(resolvedMatrix3);
        expectedResolvedMatrices.add(resolvedMatrix4);
        expectedResolvedMatrices.add(resolvedMatrix5);
        expectedResolvedMatrices.add(resolvedMatrix6);
        expectedResolvedMatrices.add(resolvedMatrix7);

        Vector<boolean[][]> result = algorithm.getResolvedMatrix();
        for(int i=0; i<result.size(); i++)
        {
            // System.out.println("Matrix: " + (i+1));
            // algorithm.affiche(result.get(i));
            assertArrayEquals(result.get(i), expectedResolvedMatrices.get(i));
        }
    }

    /**
     * Test of getResolvedMatrix method, of class Algo.
     * This is the main test of the Algo class
     */
    @Test
    public void testGetResolvedMatrix3()
    {
        System.out.println("getResolvedMatrix");
        
        int matrix3[][] = {{3,4,5,6,2,1},{3,0,1,2,3,4},{7,6,0,2,1,1},{4,4,5,0,1,2},{0,1,0,1,0,0},{0,3,2,2,2,0}};
        Algo algorithm = new Algo(matrix3, true, matrix3.length);
        Vector<boolean[][]> expectedResolvedMatrices = new Vector<boolean[][]>();
        boolean resolvedMatrix1[][] = {
            {false, false, false, false, false, true},
            {false, true, false, false, false, false},
            {false, false, true, false, false, false},
            {false, false, false, true, false, false},
            {false, false, false, false, true, false},
            {true, false, false, false, false, false}};
        expectedResolvedMatrices.add(resolvedMatrix1);

        Vector<boolean[][]> result = algorithm.getResolvedMatrix();
        for(int i=0; i<result.size(); i++)
        {
            // System.out.println("Matrix: " + (i+1));
            // algorithm.affiche(result.get(i));
            assertArrayEquals(result.get(i), expectedResolvedMatrices.get(i));
        }
    }

    /**
     * Test of getResolvedMatrix method, of class Algo.
     * This is the main test of the Algo class
     */
    // DISABLED broken @Test
    public void testGetResolvedMatrix4()
    {
        System.out.println("getResolvedMatrix");
        /*

         */
        int matrix4[][] = {{14,5,8,7},{2,12,6,5},{7,8,3,9},{2,4,6,10}};
        Algo algorithm = new Algo(matrix4, true, matrix4.length);
        Vector<boolean[][]> expectedResolvedMatrices = new Vector<boolean[][]>();
        boolean resolvedMatrix1[][] = {
            {false, false, false, false},
            {false, false, false, true},
            {false, false, false, false},
            {false, false, false, false}};
        expectedResolvedMatrices.add(resolvedMatrix1);

        Vector<boolean[][]> result = algorithm.getResolvedMatrix();
        for(int i=0; i<result.size(); i++)
        {
            System.out.println("Matrix: " + (i+1));
            algorithm.affiche(result.get(i));
            assertArrayEquals(result.get(i), expectedResolvedMatrices.get(i));
        }
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