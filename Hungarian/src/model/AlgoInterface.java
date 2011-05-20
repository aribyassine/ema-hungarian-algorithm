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
        public String getStepName();
	public void step1SubstractAllRow();
	public void step2SubstractAllCol();
        public boolean step3SelectMarkZero();
	public void step4MarkRow();
	public void step5MarkCol();
	public void step6MarkRowCol();
	public void step7Iterate();
	public void step8StrikeRowCol();
	public void step9SubstractNoMark();
	public boolean step10Affect0Mark();
	public boolean  step11Affect0Soluce();

        /*
         * Call up the step in the right order
         */
        public void resolveMatrix();

        /*
         * Return the resolved matrix
         * TODO: not too sure about the signature
         */
        public Vector<boolean[][]> getResolvedMatrix();
}
