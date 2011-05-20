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
public class Algo implements AlgoInterface{

    //private int tab[][]={{1,2,3,4,5},{1,4,2,5,3},{3,2,1,5,4},{1,2,3,5,4},{2,1,4,3,5}};
    //private int tab[][]={{4,5,3,2,3},{3,2,4,3,4},{3,3,4,4,3},{2,4,3,2,4},{2,1,3,4,3}};
    private int tab[][]={{3,4,5,6,2,1},{3,0,1,2,3,4},{7,6,0,2,1,1},{4,4,5,0,1,2},{0,1,0,1,0,0},{0,3,2,2,2,0}};
    //private int tab[][]={{14,5,8,7},{2,12,6,5},{7,8,3,9},{2,4,6,10}};
    //private int tab[][]={{0,0,0,1,0},{0,0,2,0,3},{4,5,0,0,6},{0,7,0,8,0},{9,0,10,0,0}};
    private boolean  tabMarkedZero[][];
    // TODO: find a more relevant name than "preference", e.g. maximize/minimize
    private boolean preference;
    private boolean  markRow[];
    private boolean  markCol[];
    private int tabTemp[][];
    private ArbreNAire<Integer> arbre;
    
    // vecteur de solutions
    private Vector<boolean[][]> soluce = new Vector<boolean[][]>();
    boolean oneSoluce[][]= new boolean[tab.length][tab.length];

    public Algo(int[][] tab, boolean preference, int taille) {
        init(tab, preference, taille);
    }

    public Algo(boolean preference, int taille) {
        this.preference = preference;
        this.tabMarkedZero=new boolean[taille][taille];
        markCol = new boolean[taille];
        markRow = new boolean[taille];
        arbre = new ArbreNAire<Integer>();
        arbre.initRacine(Integer.MIN_VALUE, Integer.MIN_VALUE);
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
        initTab(false,this.tabMarkedZero);
        
    }

    private void initTab(boolean b, boolean[] markRow) {
        for(int j=0;j<tab.length;j++)
        {
            markRow[j]=b;
        }
    }
    
    private void initTab(boolean marked, boolean tab[][])
    {
        for(int i=0;i<tab.length;i++)
        {
            for(int j=0;j<tab.length;j++)
            {
                tab[i][j]=marked;
            }
        }
    }

    /*
     * selection de zeros encadres par ligne
     */
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
        initTab(false, markRow);
        for(int row=0;row<tabMarkedZero.length;row++)
        {
            if(!isMarkedZeroRow(row))
                markRow[row]=true;
        }
    }

    /*on marque toute colonne ayant un 0 barré sur une ligne marqué*/
    public void step5MarkCol() {
        initTab(false, markCol);
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

    public void step10AddMarkTwice() {
            throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean  step11Affect0() {
        /* avant utilisatin d'un arbre
         initTabTemp(false, this.tabMarkedZero);
        step3SelectMarkZero();
        for(int row=0; row<tab.length;row++)
        {
            for(int col=0;col<tab.length;col++)
            {
                if(tab[row][col]==0 && !isMarkedZeroRow(row) && !isMarkedZeroCol(col))
                    tabMarkedZero[row][col]=true;
            }
        }*/
        arbre = new ArbreNAire<Integer>();
        arbre.initRacine(Integer.MIN_VALUE, Integer.MIN_VALUE);
        buildArbreZero(0, 0);
        soluce.clear();
        initTab(false, oneSoluce);
        searchSoluce(arbre, oneSoluce);
        if(soluce.isEmpty())
            return false;
        else
            return true;
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
        step7Iterate();
        affiche(getTab());
        System.out.println("nouveau tableau");
        step8StrikeRowCol();
        affiche(getTabTemp());
        step9SubstractNoMark();
        affiche(getTab());
        while(!step11Affect0()){
            resolveMatrix();
        }
    }

    /*
     * TODO: finish this up
     */
    public Vector<boolean[][]> getResolvedMatrix()
    {
       if ((soluce == null) || (soluce.isEmpty()))
       {
           resolveMatrix();
       }
        return soluce;
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
    private void afficheSoluce()
    {
        for(int i=0; i<soluce.size();i++)
        {
            affiche(soluce.get(i));
        }
    }


    public int[][] getTabTemp() {
        return tabTemp;
    }

    public ArbreNAire<Integer> getArbre() {
        return arbre;
    }

    public Vector<boolean[][]> getSoluce() {
        return soluce;
    }

    public boolean[][] getOneSoluce() {
        return oneSoluce;
    }


    private boolean searchSoluce(ArbreNAire arbre, boolean oneSoluce[][]){
        if(!arbre.isNoeudFeuille())
        {
            for(int i=0; i<arbre.getNbFils(); i++)
            {
                if(!arbre.isRacine())
                {
                    for(int k=0;k<tab.length;k++)
                    {
                        oneSoluce[(Integer)(arbre.getVue().getRow())][k]=false;
                    }
                    oneSoluce[(Integer)(arbre.getVue().getRow())][(Integer)(arbre.getVue().getCol())]=true;
                }
                else{

                }
                arbre.goToFils(i);
                searchSoluce(arbre, oneSoluce);
                arbre.goToPere();
            }

        }
        if(arbre.isNoeudFeuille())
        {
            for(int k=0;k<tab.length;k++)
            {
                oneSoluce[(Integer)(arbre.getVue().getRow())][k]=false;
            }
            oneSoluce[(Integer)(arbre.getVue().getRow())][(Integer)(arbre.getVue().getCol())]=true;
            if((Integer)(arbre.getVue().getRow())==(tab.length-1))
            {
                boolean tmp[][] = new boolean[tab.length][tab.length];
                for(int i=0;i<tab.length;i++)
                {
                    for(int j=0;j<tab.length;j++)
                    {
                        tmp[i][j]=oneSoluce[i][j];
                    }
                }
                this.soluce.add(tmp);
            }
        }
        return true;
    }

    private void buildArbreZero(int row, int col){
        int fils;
        Noeud<Integer> vue;
        
        if(row<tab.length)
        {
            for(int i=col;i<tab.length;i++)
            {
                if(tab[row][i]==0)
                {
                    // Save de la vue pour ne pas la perdre pendant le parcours
                    vue = arbre.getVue();
                    if(isPossibleToCreate(arbre, row, i))
                    {
                        arbre.setVue(vue);
                        fils = arbre.addFils(row, i);
                        arbre.goToFils(fils);
                        row++;
                        buildArbreZero(row, 0);
                        row--;
                        arbre.goToPere();
                    }else{
                        // restore la vue a la sauvegarde
                        arbre.setVue(vue);
                    }
                }
            }
        }
    }

    private boolean isPossibleToCreate(ArbreNAire<Integer> arbre, Integer row, Integer col)
    {
        boolean result=true;
        if(arbre.getVue().getRow()!=row && arbre.getVue().getCol()!=col && !arbre.isRacine() && result==true)
        {
            arbre.goToPere();
            result=isPossibleToCreate(arbre, row, col);
        }else if(arbre.isRacine()){
            result=true;
        }else{
            return false;
        }
        return result;
    }

    public static void main(String[] args) {
        Algo algo = new Algo(true,6);
        
        algo.resolveMatrix();
        
        System.out.println("solutions");
        algo.afficheSoluce();
        System.out.println("");
    }

}
