/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.util.Vector;
import model.Algo;
import model.AlgoInterface;
import model.SimpleHungarian;

/**
 *
 * @author andre
 */
public class HungarianAlgorithmController
{

    private int matrix[][];
    private AlgoInterface algo;
    
    HungarianAlgorithmController(int[][] matrix)
    {
        this.matrix = matrix;
        algo = new SimpleHungarian(matrix, true, matrix.length); // TODO: fix hardcoded value
    }
    // private AlgoInterface algo;

    public Vector<boolean[][]> resolve()
    {
        return algo.getResolvedMatrix();
    }

    // for testing purpose only, but will be later removed
    public void affiche(boolean  [][]tab)
    {
        String sortie = new String();
        for(int row=0;row<tab.length;row++)
        {
            for(int col=0;col<tab[row].length;col++)
            {
                sortie +=tab[row][col]+" ";
            }
            sortie+="\n";
        }
        System.out.println(sortie);
    }
    

}
