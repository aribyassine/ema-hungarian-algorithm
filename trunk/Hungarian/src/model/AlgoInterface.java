/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.Vector;

/**
 *
 * @author laurent
 */
public interface AlgoInterface {
	public void init(int tab[][], boolean preference, int taille);
        
        public void goToNextStep();
        public boolean isFirstStep();
        public boolean isLastStep();
        public String getStepShortDescription();
        public int getStepNum();
        
        public void addAlgoModelListener (final AlgoModelListener algoModelListener);
        public void removeAlgoModelListener(final AlgoModelListener algoModelListener);


        /*
         * Call up the step in the right order
         */
        public void resolveMatrix();

        /*
         * Return the resolved matrix
         * TODO: not too sure about the signature
         */
        public Vector<boolean[][]> getResolvedMatrix();

        public int[][] getTab();
}
