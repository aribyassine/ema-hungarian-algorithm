/*
 * To change this template, choose Tools ?5| Templates
 * and open the template in the editor.?3
 */

package model;

/**
 *
 * @author laurent
 */
public class Algo implements AlgoInterface{

	private int tab[][]={{1,2,3,4,5},{1,4,2,5,3},{3,2,1,5,4},{1,2,3,5,4},{2,1,4,3,5}};
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

        public void step10AffectZeroByRow(){
            Integer nbZero=0, xZero=null, yZero=null;

            for(int row=0;row<tab.length;row++)
            {
                for(int col=0;col<tab.length;col++)
                {
                    if(tab[row][col]==0)
                    {
                        xZero=row;
                        yZero=col;
                        nbZero++;
                    }
                }
                if(nbZero==1){
                    //COMMENT MARQUER LE ZERO
                }
            }
        }

        public void step10AffectZeroByCol(){
            
        }

	public void step1SubstractAllRow() {
            for(int i=0;i<this.tab[0].length; i++)
            {
                step1SubstractRow(i);
            }
	}

        public void step1SubstractRow(int row) {
            int valueToSobstract;
            if(this.preference==true){
                valueToSobstract=chercheMinRow(row);

            }else{
                valueToSobstract=chercheMaxRow(row);
            }
            for(int i=0;i<this.tab[row].length;i++)
            {
                tab[row][i]=tab[row][i]-valueToSobstract;
            }
	}

	public void step2SubstractAllCol() {
            for(int i=0;i<this.tab[0].length; i++)
            {
                step2SubstractCol(i);
            }
	}

        public void step2SubstractCol(int col) {
            int valueToSobstract;
            if(this.preference==true){
                valueToSobstract=chercheMinCol(col);

            }else{
                valueToSobstract=chercheMaxCol(col);
            }
            for(int i=0;i<this.tab[col].length;i++)
            {
                tab[i][col]=tab[i][col]-valueToSobstract;
            }
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

	private int chercheMinRow(int row)
	{
            int min=Integer.MAX_VALUE;
            for(int i=0;i<tab[0].length;i++)
            {
                if(tab[row][i]<min)
                min=tab[row][i];
            }
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

    private int chercheMaxRow(int row)
    {
        int max=Integer.MIN_VALUE;
        for(int i=0;i<tab[0].length;i++)
        {
            if(tab[row][i]>max)
                max=tab[row][i];
        }
        return max;
    }

    private int chercheMinCol(int col) {
        int min=Integer.MAX_VALUE;
        for(int i=0;i<tab[0].length;i++)
        {
            if(tab[i][col]<min)
                min=tab[i][col];
        }
        return min;
    }

    private int chercheMaxCol(int col) {
        int max=Integer.MIN_VALUE;
        for(int i=0;i<tab[0].length;i++)
        {
            if(tab[i][col]>max)
                max=tab[i][col];
        }
        return max;
    }

}
