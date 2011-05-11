/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import view.SolutionPanel;

/**
 *
 * @author andre
 */
class SolutionController
{
    private SolutionPanel solutionPanel;

    public SolutionController(SolutionPanel solutionPanel)
    {
        this.solutionPanel = solutionPanel;
    }

    public void setSolutionMatrix(Boolean[][] solutionMatrix)
    {
        solutionPanel.displaySolutionFromBooleanSolutionMatrix(solutionMatrix);
    }

    
    public void setSolutionMatrix(boolean[][] solutionMatrix)
    {
        // converts boolean[][] to Boolean[][]
        Boolean[][] solutionMatrixBoolean =
                new Boolean[solutionMatrix.length][solutionMatrix[0].length];
        for(int i=0; i<solutionMatrix.length; i++)
        {
            for(int j=0; j<solutionMatrix[i].length; j++)
            {
                solutionMatrixBoolean[i][j] = solutionMatrix[i][j];
            }
        }
        solutionPanel.displaySolutionFromBooleanSolutionMatrix(solutionMatrixBoolean);
    }
}
