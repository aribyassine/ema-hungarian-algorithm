/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import hungarian.HungarianView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Algo;
import model.AlgoInterface;
import view.MatrixPanel;
import view.SolutionPanel;

/**
 *
 * @author andre
 */
public class MainController
{

    private HungarianView hungarianView;
    private MatrixController matrixController;
    private SolutionController solutionxController;
    private AlgoInterface algorithm;

    /*
     * keeping a copy of the original matrix
     */
    private int matrixToResolve[][];

    public MainController(HungarianView hungarianView)
    {
        this.hungarianView = hungarianView;

        /*
         * Ressource/Job Matrix View and controller
         */
        MatrixPanel matrixPanel = hungarianView.getMatrixPanel();
        this.matrixController = new MatrixController(matrixPanel);

        SolutionPanel solutionPanel = hungarianView.getSolutionPanel();
        this.solutionxController = new SolutionController(solutionPanel);
        
        addListeners();
    }

    private void addListeners()
    {
        hungarianView.addResolveButtonListener(
                new ResolveButtonListener());
        hungarianView.addNextStepButtonListener(
                new NextStepListener());
    }

    class ResolveButtonListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            int matrix[][] = matrixController.getIntMatrix();

            // algorithm = new SimpleHungarianAlgo(matrix, true, matrix.length); // TODO: fix hardcoded value
            algorithm = new Algo(matrix, true, matrix.length);
            // Getting the first solution for testing
            boolean resolvedMatrix[][] =
                    algorithm.getResolvedMatrix().firstElement();

            /*
             * After resolving the matrix[][] had been altered by the Algorithm
             * Reinit one.
             */
            matrix = matrixController.getIntMatrix();

            solutionxController.setTasks(matrixController.getTasks());
            solutionxController.setCostMatrix(matrix);
            solutionxController.setSolutionMatrix(resolvedMatrix);
        }
    }

    /*
     * TODO: last step handling
     */
    class NextStepListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            int matrix[][];


            /*
             * si l'algorithm n'a pas encore ete initialise (step 0)
             */
            if (algorithm == null)
            {
                matrixToResolve = matrixController.getIntMatrix();
                matrix = matrixController.getIntMatrix();
                algorithm = new Algo(matrix,
                    true, // TODO: hardcoded value
                    matrix.length);
                hungarianView.setAlgo(algorithm);
            }
            else if (algorithm.isLastStep())
            {
                // Getting the first solution for testing
                boolean resolvedMatrix[][] =
                    algorithm.getResolvedMatrix().firstElement();

                /*
                 * After resolving the matrix[][] had been altered by the Algorithm
                 */
                solutionxController.setTasks(matrixController.getTasks());
                solutionxController.setCostMatrix(matrixToResolve);
                solutionxController.setSolutionMatrix(resolvedMatrix);
            }
            else
            {
                algorithm.goToNextStep();
            }
        }
    }
}
