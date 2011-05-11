/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import model.Algo;
import model.AlgoInterface;

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
        algo = new Algo(matrix, true, matrix.length); // TODO: fix hardcoded value
    }
    // private AlgoInterface algo;

    public boolean[][] resolve()
    {
        return algo.getResolvedMatrix();
    }
}
