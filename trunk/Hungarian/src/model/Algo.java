/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author laurent
 */
public class Algo implements AlgoInterface{

	private int tab[][];
	private boolean preference;
	
	public Algo(int[][] tab, boolean preference) {
		init(tab, preference);
	}

	public void init(int[][] tab, boolean preference) {
		this.tab = tab;
		this.preference = preference;
	}

	public void step10Affect0() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void step1SubstractRow() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void step2SubstractCol() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void step3MarkRow() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void step4MarkCol() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void step5MarkRowCol() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void step6Iterate() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void step7StrikeRowCol() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void step8SubstractNoMark() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void step9AddMarkTwice() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public boolean isPreference() {
		return preference;
	}

	public void setPreference(boolean preference) {
		this.preference = preference;
	}

	public int[][] getTab() {
		return tab;
	}

	public void setTab(int[][] tab) {
		this.tab = tab;
	}

	private int chercheMin(int row)
	{
		int min=Integer.MAX_VALUE;
                //coucou
		return min;
	}

    public void resolveMatrix()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /*
     * TODO: finish this up
     */
    public int[][] getResolvedMatrix()
    {
        resolveMatrix();
        
        return null;
    }

}
