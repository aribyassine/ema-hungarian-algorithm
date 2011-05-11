/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author laurent
 */
public interface AlgoInterface {
	public void init(int tab[][], boolean preference, int taille);
	public void step1SubstractAllRow();
	public void step2SubstractAllCol();
        public void step3SelectMarkZero();
	public void step4MarkRow();
	public void step5MarkCol();
	public void step6MarkRowCol();
	public void step7Iterate();
	public void step8StrikeRowCol();
	public void step9SubstractNoMark();
	public void step10AddMarkTwice();
	public void step11Affect0();

        /*
         * Call up the step in the right order
         */
        public void resolveMatrix();

        /*
         * Return the resolved matrix
         * TODO: not too sure about the signature
         */
        public boolean[][] getResolvedMatrix();
}
