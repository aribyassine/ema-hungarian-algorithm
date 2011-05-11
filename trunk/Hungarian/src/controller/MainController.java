/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import hungarian.HungarianView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.MatrixPanel;

/**
 *
 * @author andre
 */
public class MainController
{

    private HungarianView hungarianView;
    private MatrixController matrixController;

    public MainController(HungarianView hungarianView)
    {
        this.hungarianView = hungarianView;
        MatrixPanel matrixPanel = hungarianView.getMatrixPanel();
        this.matrixController = new MatrixController(matrixPanel);
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
            System.out.println("Running hungarianAlgorithmController.resolve()");
            hungarianAlgorithmController.affiche(resolvedMatrix);
        }
    }
}
