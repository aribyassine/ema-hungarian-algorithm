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
	public void init(int tab[][], boolean preference);
	public void step1SubstractRow();
	public void step2SubstractCol();
	public void step3MarkRow();
	public void step4MarkCol();
	public void step5MarkRowCol();
	public void step6Iterate();
	public void step7StrikeRowCol();
	public void step8SubstractNoMark();
	public void step9AddMarkTwice();
	public void step10Affect0();

        /*
         * Call up the step in the right order
         */
        public void resolveMatrix();

        /*
         * Return the resolved matrix
         * TODO: not too sure about the signature
         */
        public int[][] getResolvedMatrix();
}
