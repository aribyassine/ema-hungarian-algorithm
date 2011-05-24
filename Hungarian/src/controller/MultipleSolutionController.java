/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.util.Vector;
import view.MultipleSolutionPanel;
import view.SolutionPanel;

/**
 *
 * @author andre
 */
class MultipleSolutionController
{
    private MultipleSolutionPanel multipleSolutionPanel;

    public MultipleSolutionController(MultipleSolutionPanel multipleSolutionPanel)
    {
        this.multipleSolutionPanel = multipleSolutionPanel;

        /*
         * Ne pas afficher le widget de solution tant qu'il n'y a pas de solution
         */
        // solutionPanel.setVisible(false);
    }

    public void setSolutionMatrix2(Vector<Boolean[][]> solutionMatrices)
    {
        multipleSolutionPanel.displaySolutionFromBooleanSolutionMatrices(solutionMatrices);

        /*
         * N'afficher le widget que lorsque la solution a ete trouvee
         * FIXME: we actually want to display an empty panel even
         * when the solution wasn't found yet
         */
        multipleSolutionPanel.setVisible(true);
    }


    /*
     * Converts to Boolean[][] and set the solution matrix
     */
    public void setSolutionMatrix(Vector<boolean[][]> solutionMatrices)
    {
        Boolean[][] solutionMatrixBoolean;
        Vector<Boolean[][]> solutionMatricesBoolean = new Vector<Boolean[][]>();
        
        for(int k=0; k<solutionMatrices.size(); k++)
        {
            solutionMatrixBoolean =
                new Boolean[solutionMatrices.get(0).length][solutionMatrices.get(0)[0].length];
            for(int i=0; i<solutionMatrices.get(k).length; i++)
            {
                for(int j=0; j<solutionMatrices.get(k)[i].length; j++)
                {
                    solutionMatrixBoolean[i][j] = solutionMatrices.get(k)[i][j];
                }
            }
            solutionMatricesBoolean.add(solutionMatrixBoolean);
        }
        setSolutionMatrix2(solutionMatricesBoolean);
    }

    public void setResources(String[] resources)
    {
        multipleSolutionPanel.setResources(resources);
    }

    public void setTasks(String[] tasks)
    {
        multipleSolutionPanel.setTasks(tasks);
    }

    public void setCostMatrix(int[][] costMatrix)
    {
        multipleSolutionPanel.setCostMatrix(costMatrix);
    }
}
