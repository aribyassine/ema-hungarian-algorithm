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
     * Test of getResolvedMatrix method, of class Algo.
     * This is the main test of the Algo class
     */
    @Test
    public void testGetResolvedMatrix1()
    {
        System.out.println("getResolvedMatrix");

        int matrix1[][] = ExampleMatrix.MATRIX1;
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

        int matrix2[][] = ExampleMatrix.MATRIX2;
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
        
        int matrix3[][] = ExampleMatrix.MATRIX3;
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


}