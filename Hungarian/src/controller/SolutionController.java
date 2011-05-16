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

        /*
         * Ne pas afficher le widget de solution tant qu'il n'y a pas de solution
         */
        solutionPanel.setVisible(false);
    }

    public void setSolutionMatrix(Boolean[][] solutionMatrix)
    {
        solutionPanel.displaySolutionFromBooleanSolutionMatrix(solutionMatrix);

        /*
         * N'afficher le widget que lorsque la solution a ete trouvee
         */
        solutionPanel.setVisible(true);
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
        setSolutionMatrix(solutionMatrixBoolean);
    }

    public void setResources(String[] resources)
    {
        solutionPanel.setResources(resources);
    }

    public void setTasks(String[] tasks)
    {
        solutionPanel.setTasks(tasks);
    }

    public void setCostMatrix(int[][] costMatrix)
    {
        solutionPanel.setCostMatrix(costMatrix);
    }
}
