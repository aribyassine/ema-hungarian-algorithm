/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import hungarian.HungarianView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
            
            HungarianAlgorithmController hungarianAlgorithmController =
                    new HungarianAlgorithmController(matrix);
            boolean resolvedMatrix[][] = hungarianAlgorithmController.resolve();

            
            hungarianAlgorithmController.affiche(resolvedMatrix);

            solutionxController.setTasks(matrixController.getTasks());
            solutionxController.setSolutionMatrix(resolvedMatrix);
        }
    }
}
