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

	private int tab[][]={{0,0,0,0,0,0},{0,1,2,3,4,5},{0,1,4,2,5,3},{0,3,2,1,5,4},{0,1,2,3,5,4},{0,2,1,4,3,5}};
        private boolean  tabMarkedZero[][]=new boolean[5][5];
	private boolean preference;
        private final static int MARKED=1;
        private final static int NOT_MARKED=0;

	
	public Algo(int[][] tab, boolean preference) {
		init(tab, preference);
	}

	public void init(int[][] tab, boolean preference) {
		this.tab = tab;
		this.preference = preference;
                for(int i=0;i<tabMarkedZero.length;i++)
                {
                    for(int j=0;j<tabMarkedZero.length;j++)
                    {
                        tabMarkedZero[i][j]=false;
                    }
                }
	}

        //selection de zeros encadrées
	public void step10Affect0() {
            step10AffectZeroByRow();
            step10AffectZeroByCol();
	}

        //selection de zeros encadres par ligne
        public void step10AffectZeroByRow(){
            Integer nbZero=0, xZero=null, yZero=null;

            for(int row=1;row<tab.length;row++)
            {
                for(int col=1;col<tab.length;col++)
                {
                    if(tab[row][col]==0)
                    {
                        xZero=row-1;
                        yZero=col-1;
                        nbZero++;
                    }
                }
                /* on selectionne si il n'y a qu'un seul zero sur la ligne et
                 si il n'y a pas de zeros encadres sur la colonne*/
                if(nbZero==1 && !isMarkedZeroCol(yZero)){
                    tabMarkedZero[xZero][yZero]=true;
                }
                nbZero=0;
            }
        }
//L'accummulation de l'information ne fait pas plus de connaissance que l'acummulation de brique ne fait un mur

        /* selection de zeros encadrees par colonnes */
        public void step10AffectZeroByCol(){
            Integer nbZero=0, xZero=null, yZero=null;

            for(int col=1;col<tab.length;col++)
            {
                for(int row=1;row<tab.length;row++)
                {
                    if(tab[row][col]==0)
                    {
                        xZero=row-1;
                        yZero=col-1;
                        nbZero++;
                    }
                }
                /* on selectionne si il n'y a qu'un seul zero sur la colonne et
                 qu'il n'y pas de zero encadres sur la ligne */
                if(nbZero==1 && !isMarkedZeroRow(xZero)){
                    tabMarkedZero[xZero][yZero]=true;
                }
                nbZero=0;
            }
        }

        /*on creer un zero par ligne par soustraction*/
	public void step1SubstractAllRow() {
            for(int i=1;i<this.tab[0].length; i++)
            {
                step1SubstractRow(i);
            }
	}

        public void step1SubstractRow(int row) {
            int valueToSobstract;
            /*si la "meilleur valeurs" est la plus petite, on recherche le mini
             sinon le maxi*/
            if(this.preference==true){
                valueToSobstract=chercheMinRow(row);
            }else{
                valueToSobstract=chercheMaxRow(row);
            }
            /*on soustrait ensuite cette valeur à chaque élément de la ligne*/
            for(int i=1;i<this.tab[row].length;i++)
            {
                tab[row][i]=tab[row][i]-valueToSobstract;
            }
	}

        /*pour chaque colonne, on creer un zero par soustraction*/
	public void step2SubstractAllCol() {
            for(int i=1;i<this.tab[0].length; i++)
            {
                step2SubstractCol(i);
            }
	}

        public void step2SubstractCol(int col) {
            int valueToSobstract;
            /*si la "meilleur valeurs" est la plus petite, on recherche le mini
             sinon le maxi*/
            if(this.preference==true){
                valueToSobstract=chercheMinCol(col);

            }else{
                valueToSobstract=chercheMaxCol(col);
            }
            /*on soustrait ensuite cette valeur à chaque élément de la ligne*/
            for(int i=1;i<this.tab[col].length;i++)
            {
                tab[i][col]=tab[i][col]-valueToSobstract;
            }
	}

        /*on marque toute ligne n'ayant pas de zero encadrer*/
	public void step3MarkRow() {
            for(int row=0;row<tabMarkedZero.length;row++)
            {
                if(!isMarkedZeroRow(row))
                    tab[row+1][0]=MARKED;
            }
	}

        /*on marque toute colonne ayant un 0 barré sur une ligne marqué*/
	public void step4MarkCol() {
            //recherche de ligne marqué
            for(int row=0;row<tab.length;row++)
            {
                for(int col=1;col<tab.length;col++)
                {
                    //si on trouve une marqué,
                    if(tab[row][0]==MARKED)
                    {
                        //ATTENTION, IL FAUDRAIT RECHERCHER UN ZERO NON ENCADRER
                        if(nbZeroCol(col)>1)
                        {
                            tab[0][col]=MARKED;
                        }
                    }
                }
            }
	}

        /*on marque toute ligne ayant un 0 encadré sur une colonne marqué*/
	public void step5MarkRowCol() {
            for(int col=1;col<tab.length;col++)
            {
                if(tab[0][col]==MARKED)
                {
                    if(isMarkedZeroCol(col-1))
                    {
                        for(int row=0;row<tabMarkedZero.length;row++)
                        {
                            if(tabMarkedZero[row][col-1]==true)
                                tab[row+1][0]=MARKED;
                        }
                    }
                }
            }
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
            for(int i=1;i<tab[0].length;i++)
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
        for(int i=1;i<tab[0].length;i++)
        {
            if(tab[row][i]>max)
                max=tab[row][i];
        }
        return max;
    }

    private int chercheMinCol(int col) {
        int min=Integer.MAX_VALUE;
        for(int i=1;i<tab[0].length;i++)
        {
            if(tab[i][col]<min)
                min=tab[i][col];
        }
        return min;
    }

    private int chercheMaxCol(int col) {
        int max=Integer.MIN_VALUE;
        for(int i=1;i<tab[0].length;i++)
        {
            if(tab[i][col]>max)
                max=tab[i][col];
        }
        return max;
    }

    private boolean isMarkedZeroRow(int row)
    {
        boolean retour=false;
        for(int i=0;i<tabMarkedZero.length;i++)
        {
            if(tabMarkedZero[row][i])
                retour=true;
        }
        return retour;
    }

    private boolean isMarkedZeroCol(int col)
    {
        boolean retour=false;
        for(int i=0;i<tabMarkedZero.length;i++)
        {
            if(tabMarkedZero[i][col])
                retour=true;
        }
        return retour;
    }

    private int nbZeroRow(int row){
        int nbZero=0;
        for(int col=1;col<tab.length;col++)
        {
            if(tab[row][col]==0)
                nbZero++;
        }
        return nbZero;
    }

    private int nbZeroCol(int col){
        int nbZero=0;
        for(int row=1;row<tab.length;row++)
        {
            if(tab[row][col]==0)
                nbZero++;
        }
        return nbZero;
    }

    public Algo(boolean preference) {
        this.preference = preference;
        for(int i=0;i<tabMarkedZero.length;i++)
        {
            for(int j=0;j<tabMarkedZero.length;j++)
            {
                tabMarkedZero[i][j]=false;
            }
        }
    }

    public boolean[][] getTabMarkedZero() {
        return tabMarkedZero;
    }

    private void affiche(boolean  [][]tab)
    {
        String sortie = new String();
        for(int row=0;row<tab.length;row++)
        {
            for(int col=0;col<tab.length;col++)
            {
                sortie +=tab[row][col]+" ";
            }
            sortie+="\n";
        }
        System.out.println(sortie);
    }

    private void affiche(int [][]tab)
    {
        String sortie = new String();
        for(int row=0;row<tab.length;row++)
        {
            for(int col=0;col<tab.length;col++)
            {
                sortie +=tab[row][col]+" ";
            }
            sortie+="\n";
        }
        System.out.println(sortie);
    }

    public static void main(String[] args) {
        Algo algo = new Algo(true);
        algo.step1SubstractAllRow();
        System.out.println("soustraction ligne");
        algo.affiche(algo.getTab());
        algo.step2SubstractAllCol();
        System.out.println("soustraction colonne");
        algo.affiche(algo.getTab());
        algo.step10Affect0();
        System.out.println("zeros encadre");
        algo.affiche(algo.getTabMarkedZero());
        algo.step3MarkRow();
        System.out.println("marquage ligne");
        algo.affiche(algo.getTab());
        //algo.affiche(algo.getTabMarkedZero());
        algo.step4MarkCol();
        System.out.println("marqage colonne");
        algo.affiche(algo.getTab());
        //algo.affiche(algo.getTabMarkedZero());
        algo.step5MarkRowCol();
        System.out.println("marquage ligne/colonne");
        algo.affiche(algo.getTab());
    }
}
