/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.util.Vector;
import model.AlgoInterface;
import model.AlgoModelListener;
import view.MultipleSolutionPanel;

/**
 *
 * @author andre
 */
class MultipleSolutionController
{
    private MultipleSolutionPanel multipleSolutionPanel;

    /*
     * To get notified of model changes
     */
    private AlgoInterface algo;

    public MultipleSolutionController(MultipleSolutionPanel multipleSolutionPanel)
    {
        this(multipleSolutionPanel, null);
    }

    public MultipleSolutionController(MultipleSolutionPanel multipleSolutionPanel,
            AlgoInterface algo)
    {
        this.multipleSolutionPanel = multipleSolutionPanel;
        setAlgo(algo);
    }

    public void setSolutionMatrix2(Vector<Boolean[][]> solutionMatrices)
    {
        multipleSolutionPanel.displaySolutionFromBooleanSolutionMatrices(solutionMatrices);
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

    // TODO: some code redundancy (see addAlgoModelListener HungarianView)
    private void addAlgoModelListener(AlgoInterface algo)
    {
        if (algo != null)
        {
            algo.addAlgoModelListener(new AlgoModelListener()
            {
                public void algoModelChanged(final AlgoInterface algo)
                {
                    onModelChange();
                }
            });
        }
    }

    private void onModelChange()
    {
        System.out.println("Model changes");
        Vector<boolean[][]> tmpSolutions = algo.getSoluce();
        
        if(!tmpSolutions.isEmpty())
        {
            setSolutionMatrix(tmpSolutions);
        }
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

    public final void setAlgo(AlgoInterface algo)
    {
        this.algo = algo;
        addAlgoModelListener(algo);
    }

    public void clearSolutions()
    {
        multipleSolutionPanel.clearSolutions();
    }
}
