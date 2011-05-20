/*
 * To change this template, choose Tools ?5| Templates
 * and open the template in the editor.?3
 */

package model;

import java.util.Vector;

/**
 *
 * @author laurent
 */
public class SimpleHungarian implements AlgoInterface{

    //private int tab[][]={{1,2,3,4,5},{1,4,2,5,3},{3,2,1,5,4},{1,2,3,5,4},{2,1,4,3,5}};
    private int tab[][]={{14,5,8,7},{2,12,6,5},{7,8,3,9},{2,4,6,10}};
    private boolean  tabMarkedZero[][];
    private boolean preference;
    private boolean  markRow[];
    private boolean  markCol[];
    private int tabTemp[][];


    public SimpleHungarian(int[][] tab, boolean preference, int taille) {
        init(tab, preference, taille);
    }

    public SimpleHungarian(boolean preference, int taille) {
        this.preference = preference;
        this.tabMarkedZero=new boolean[taille][taille];
        markCol = new boolean[taille];
        markRow = new boolean[taille];
        for(int i=0;i<tabMarkedZero.length;i++)
        {
            for(int j=0;j<tabMarkedZero.length;j++)
            {
                tabMarkedZero[i][j]=false;
            }
        }
    }

    public final void init(int[][] tab, boolean preference, int taille) {
        this.tab = tab;
        this.preference = preference;
        this.tabMarkedZero=new boolean[taille][taille];
        markCol = new boolean[taille];
        markRow = new boolean[taille];
        initTabTemp(false);
        
    }

    private void initTabTemp(boolean marked)
    {
        for(int i=0;i<tabMarkedZero.length;i++)
        {
            for(int j=0;j<tabMarkedZero.length;j++)
            {
                tabMarkedZero[i][j]=marked;
            }
        }
    }

    //selection de zeros encadres par ligne
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
            /* on selectionne si il n'y a qu'un seul zero sur la ligne et
             si il n'y a pas de zeros encadres sur la colonne*/
            if(nbZero==1 && !isMarkedZeroCol(yZero)){
                tabMarkedZero[xZero][yZero]=true;
            }
            nbZero=0;
        }
    }

//L'accummulation de l'information ne fait pas plus de connaissance que l'acummulation de brique ne fait un mur

    /*on creer un zero par ligne par soustraction*/
    public void step1SubstractAllRow() {
        for(int i=0;i<this.tab[0].length; i++)
        {
            step1SubstractRow(i);
        }
    }

    public void step1SubstractRow(int row) {
        int valueToSobstract;
        /*si la "meilleur valeurs" est la plus petite, on recherche le mini
         sinon le maxi*/
        if(this.preference==true){
            valueToSobstract=chercheMinRow(row,tab);
        }else{
            valueToSobstract=chercheMaxRow(row,tab);
        }
        /*on soustrait ensuite cette valeur à chaque élément de la ligne*/
        for(int i=0;i<this.tab[row].length;i++)
        {
            tab[row][i]=tab[row][i]-valueToSobstract;
        }
    }

    /*pour chaque colonne, on creer un zero par soustraction*/
    public void step2SubstractAllCol() {
        for(int i=0;i<this.tab[0].length; i++)
        {
            step2SubstractCol(i);
        }
    }

    public void step2SubstractCol(int col) {
        int valueToSobstract;
        /*si la "meilleur valeurs" est la plus petite, on recherche le mini
         sinon le maxi*/
        if(this.preference==true){
            valueToSobstract=chercheMinCol(col, tab);

        }else{
            valueToSobstract=chercheMaxCol(col, tab);
        }
        /*on soustrait ensuite cette valeur à chaque élément de la ligne*/
        for(int i=0;i<this.tab[col].length;i++)
        {
            tab[i][col]=tab[i][col]-valueToSobstract;
        }
    }

    public void step3SelectMarkZero() {
        Integer nbZero=0, xZero=null, yZero=null;

        for(int col=0;col<tab.length;col++)
        {
            for(int row=0;row<tab.length;row++)
            {
                if(tab[row][col]==0)
                {
                    xZero=row;
                    yZero=col;
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

        nbZero=0;
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
            /* on selectionne si il n'y a qu'un seul zero sur la ligne et
             qu'il n'y pas de zero encadres sur la colonne*/
            if(nbZero==1 && !isMarkedZeroCol(yZero)){
                tabMarkedZero[xZero][yZero]=true;
            }
            nbZero=0;
        }
    }

    /*on marque toute ligne n'ayant pas de zero encadrer*/
    public void step4MarkRow() {
        for(int row=0;row<tabMarkedZero.length;row++)
        {
            if(!isMarkedZeroRow(row))
                markRow[row]=true;
        }
    }

    /*on marque toute colonne ayant un 0 barré sur une ligne marqué*/
    public void step5MarkCol() {
        //recherche de ligne marqué
        for(int row=0;row<tab.length;row++)
        {
            for(int col=0;col<tab.length;col++)
            {
                //si on trouve une marqué,
                if(markRow[row]==true)
                {
                    //ATTENTION, IL FAUDRAIT RECHERCHER UN ZERO NON ENCADRER
                    if(nbZeroCol(col)>1)
                    {
                        markCol[col]=true;
                    }
                }
            }
        }
    }

    /*on marque toute ligne ayant un 0 encadré sur une colonne marqué*/
    public void step6MarkRowCol() {
        for(int col=0;col<tab.length;col++)
        {
            if(markCol[col]==true)
            {
                if(isMarkedZeroCol(col))
                {
                    for(int row=0;row<tabMarkedZero.length;row++)
                    {
                        if(tabMarkedZero[row][col]==true)
                            markRow[row]=true;
                    }
                }
            }
        }
    }

    public void step7Iterate() {
        int nbMarkedRowAfter=-1, nbMarkedRowBefore=nbMarkedRow();
        int nbMarkedColAfter=-1, nbMarkedColBefore=nbMarkedCol();
        do{
            step5MarkCol();
            step6MarkRowCol();
            nbMarkedColAfter=nbMarkedCol();
            nbMarkedRowAfter=nbMarkedRow();
            if(nbMarkedColAfter!=nbMarkedColBefore)
            {
                nbMarkedColBefore=nbMarkedColAfter;
                nbMarkedColAfter=-1;
            }
            if(nbMarkedRowAfter!=nbMarkedRowBefore)
            {
                nbMarkedRowBefore=nbMarkedRowAfter;
                nbMarkedRowAfter=-1;
            }
        }while(nbMarkedColAfter!=nbMarkedColBefore && nbMarkedRowAfter!=nbMarkedRowBefore);
    }

    public void step8StrikeRowCol() {
        tabTemp=new int[nbMarkedRow()][tab.length-nbMarkedCol()];
        int rowTabTemp=0, colTabTemp=0;
        for(int row=0;row<tab.length;row++)
        {
            for(int col=0;col<tab.length;col++)
            {
                if(markCol[col]==false && markRow[row]==true)
                {
                    tabTemp[rowTabTemp][colTabTemp]=tab[row][col];
                    colTabTemp++;
                }
            }
            if(colTabTemp==tabTemp[0].length){
                rowTabTemp++;
            }
            colTabTemp=0;
        }
    }

    public void step9SubstractNoMark() {
        int value;
        if(preference==true)
        {
             value = chercheMinTab(tabTemp);
        }else{
            value = chercheMaxTab(tabTemp);
        }
        for(int row=0; row<tab.length;row++)
        {
            for(int col=0;col<tab.length;col++)
            {
                //si element rayé deux fois
                if(this.markRow[row]==false && markCol[col]==true)
                {
                    tab[row][col]+=value;
                }
                //si element non rayé
                if(this.markRow[row]==true && markCol[col]==false)
                {
                    tab[row][col]-=value;
                }
            }
        }
    }

    public boolean step10Affect0Mark() {
            throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean step11Affect0Soluce()
    {
        boolean zeroAffected = false;
        initTabTemp(false);
        step3SelectMarkZero();
        for(int row=0; row<tab.length;row++)
        {
            for(int col=0;col<tab.length;col++)
            {
                if(tab[row][col]==0 && !isMarkedZeroRow(row) && !isMarkedZeroCol(col))
                {
                    tabMarkedZero[row][col] = true;
                    zeroAffected = true;
                }
            }
        }
        
        return zeroAffected;
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


    public void resolveMatrix()
    {
        step1SubstractAllRow();
        System.out.println("soustraction ligne");
        affiche(getTab());
        step2SubstractAllCol();
        System.out.println("soustraction colonne");
        affiche(getTab());
        step3SelectMarkZero();
        System.out.println("zeros encadre");
        affiche(getTabMarkedZero());
        step4MarkRow();
        System.out.println("marquage ligne");
        affiche(getMarkRow());
        //algo.affiche(algo.getTabMarkedZero());
        step5MarkCol();
        System.out.println("marqage colonne");
        affiche(getMarkCol());
        //algo.affiche(algo.getTabMarkedZero());
        step6MarkRowCol();
        System.out.println("marquage ligne/colonne");
        affiche(getMarkRow());
        affiche(getMarkCol());
    }

    /*
     * TODO: finish this up
     */
    public Vector<boolean[][]> getResolvedMatrix()
    {
        resolveMatrix();
        Vector<boolean[][]> oneSolutionVector = new Vector<boolean[][]>();
        oneSolutionVector.add(tabMarkedZero);
        
        return oneSolutionVector;
    }
    
    private int chercheMinRow(int row, int [][]tab)
    {
        int min=Integer.MAX_VALUE;
        for(int i=0;i<tab[0].length;i++)
        {
            if(tab[row][i]<min)
            min=tab[row][i];
        }
        return min;
    }

    private int chercheMaxRow(int row, int [][]tab)
    {
        int max=Integer.MIN_VALUE;
        for(int i=0;i<tab[0].length;i++)
        {
            if(tab[row][i]>max)
                max=tab[row][i];
        }
        return max;
    }

    private int chercheMinCol(int col,int [][]tab) {
        int min=Integer.MAX_VALUE;
        for(int i=0;i<tab[0].length;i++)
        {
            if(tab[i][col]<min)
                min=tab[i][col];
        }
        return min;
    }

    private int chercheMaxCol(int col,int [][]tab) {
        int max=Integer.MIN_VALUE;
        for(int i=0;i<tab[0].length;i++)
        {
            if(tab[i][col]>max)
                max=tab[i][col];
        }
        return max;
    }

    private int chercheMaxTab(int [][]tab)
    {
        Integer max= Integer.MIN_VALUE;
        int temp;
        for(int row=0;row<tab.length;row++)
        {
            temp = chercheMaxRow(row, tab);
            if(temp>max)
                max=temp;
        }
        return max;
    }

    private int chercheMinTab(int [][]tab)
    {
        Integer min= Integer.MAX_VALUE;
        int temp;
        for(int row=0;row<tab.length;row++)
        {
            temp = chercheMinRow(row, tab);
            if(temp<min)
                min=temp;
        }
        return min;
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
        for(int col=0;col<tab.length;col++)
        {
            if(tab[row][col]==0)
                nbZero++;
        }
        return nbZero;
    }

    private int nbZeroCol(int col){
        int nbZero=0;
        for(int row=0;row<tab.length;row++)
        {
            if(tab[row][col]==0)
                nbZero++;
        }
        return nbZero;
    }


    public boolean[][] getTabMarkedZero() {
        return tabMarkedZero;
    }

    private void affiche(boolean  []tab)
    {
        String sortie = new String();
        for(int row=0;row<tab.length;row++)
        {
            sortie +=tab[row]+" ";
        }
        System.out.println(sortie);
    }

    public boolean[] getMarkCol() {
        return markCol;
    }

    public boolean[] getMarkRow() {
        return markRow;
    }

    private int nbMarkedCol()
    {
        int nbMarkedCol=0;
        for(int i=0;i<markCol.length;i++)
        {
            if(markCol[i]==true)
                nbMarkedCol++;
        }
        return nbMarkedCol;
    }

    private int nbMarkedRow()
    {
        int nbMarkedRow=0;
        for(int i=0;i<markRow.length;i++)
        {
            if(markRow[i]==true)
                nbMarkedRow++;
        }
        return nbMarkedRow;
    }

    private void affiche(boolean  [][]tab)
    {
        String sortie = new String();
        for(int row=0;row<tab.length;row++)
        {
            for(int col=0;col<tab[row].length;col++)
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
            for(int col=0;col<tab[row].length;col++)
            {
                sortie +=tab[row][col]+" ";
            }
            sortie+="\n";
        }
        System.out.println(sortie);
    }

    public int[][] getTabTemp()
    {
        return tabTemp;
    }

}
