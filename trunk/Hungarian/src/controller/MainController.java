/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import hungarian.HungarianView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author andre
 */
public class MainController
{
    private HungarianView hungarianView;

    public MainController(HungarianView hungarianView)
    {
        this.hungarianView = hungarianView;
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
            HungarianAlgorithmController hungarianAlgorithmController =
                    new HungarianAlgorithmController();
            System.out.println("Running hungarianAlgorithmController TODO");
        }
    }

}
