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
import model.SimpleHungarianAlgo;
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
}
