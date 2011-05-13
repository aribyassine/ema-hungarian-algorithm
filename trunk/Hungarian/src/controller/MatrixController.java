/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import view.MatrixPanel;

/**
 *
 * @author andre
 */
public class MatrixController
{

    private MatrixPanel matrixPanel;

    public MatrixController(MatrixPanel matrixPanel)
    {
        this.matrixPanel = matrixPanel;
    }

    public int[][] getIntMatrix()
    {
        return matrixPanel.getIntMatrix();
    }

    public String[] getTasks()
    {
        return matrixPanel.getTasks();
    }

    public String[] getResources()
    {
        return matrixPanel.getResources();
    }
}
