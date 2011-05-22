/*
 * To change this template, choose Tools ?5| Templates
 * and open the template in the editor.?3
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author laurent
 */
public class Algo implements AlgoInterface
{

    private int tab[][]; // ={{1,2,3,4,5},{1,4,2,5,3},{3,2,1,5,4},{1,2,3,5,4},{2,1,4,3,5}};
    //private int tab[][]={{4,5,3,2,3},{3,2,4,3,4},{3,3,4,4,3},{2,4,3,2,4},{2,1,3,4,3}};
    //private int tab[][]={{13,4,25,6,2,68 107,-12,11,216},{22,-5,0,2,31,54,37,3,24,11},{7,6,0,2,1,1},{4,4,5,0,1,2},{0,1,0,1,0,0},{0,3,2,2,2,0}};
    //private int tab[][]={{3,4,5,6,2,1},{3,0,1,2,3,4},{7,6,0,2,1,1},{4,4,5,0,1,2},{0,1,0,1,0,0},{0,3,2,2,2,0}};
    //private int tab[][]={{14,5,8,7},{2,12,6,5},{7,8,3,9},{2,4,6,10}};
    //private int tab[][]={{0,0,0,1,0},{0,0,2,0,3},{4,5,0,0,6},{0,7,0,8,0},{9,0,10,0,0}};
    private boolean tabMarkedZero[][];

    /*
     * Garde en memoire l'etape de l'algorithme
     * TODO: changer le type int pour un type custom (int stepN, String name, String description?)
     */
    private int step;

    /*
     * minimise ou maximise
     */
    private boolean minimize;
    private boolean markRow[];
    private boolean markCol[];
    private int tabTemp[][];
    private String[] stepsShortDescription =
    {
        "tab init",                 // step0
        "soustraction ligne",       // step1
        "soustraction colonne",     // step2
        "zeros encadre",            // step3
        "marquage ligne",           // step4
        "marquage colonne",          // step5
        "marquage ligne/colonne",   // step6
        "nouveau tableau",          // step7
        "step8StrikeRowCol",        // step8
        "step9SubstractNoMark",     // step9
        "FIXME nothing to do",      // step10
        "step11Affect0Soluce",      // step11
    };
    
    private ArbreNAire<Integer> arbre;
    /*
     * used for notifying algo changes to the view
     * we might move that down to a common abstract class later on.
     */
    private final List<AlgoModelListener> algoModelListeners;
    // vecteur de solutions
    private Vector<boolean[][]> soluce = new Vector<boolean[][]>();
    private Vector<boolean[][]> partialSoluce = new Vector<boolean[][]>();
    boolean oneSoluce[][];

    public Algo(int[][] tab, boolean preference, int taille)
    {
        this.algoModelListeners = new ArrayList<AlgoModelListener>();
        init(tab, preference, taille);
    }

    public Algo(boolean preference, int taille)
    {
        this(
                null, // default test tabs passed if none were given
                preference,
                taille);
    }

    public final void init(int[][] tab, boolean preference, int taille)
    {
        /*
         * this is for testing purpose only, if no tab were passed
         * take the class-defined one
         */
        if (tab != null)
        {
            this.tab = tab;
        }
        this.minimize = preference;
        this.tabMarkedZero = new boolean[taille][taille];
        this.markCol = new boolean[taille];
        this.markRow = new boolean[taille];
        this.arbre = new ArbreNAire<Integer>();
        oneSoluce = new boolean[this.tab.length][this.tab.length];
        this.arbre.initRacine(Integer.MIN_VALUE, Integer.MIN_VALUE);

        /*
         * Multiply the tab by -1
         */
        if (!minimize)
        {
            for (int row = 0; row < tab.length; row++)
            {
                for (int col = 0; col < tab.length; col++)
                {
                    this.tab[row][col] = -1 * this.tab[row][col];
                }
            }
        }
        initTab(false, this.tabMarkedZero);
    }

    /*
     * Helper function, set the arraw to b
     */
    private void initTab(boolean b, boolean[] markRow)
    {
        for (int j = 0; j < tab.length; j++)
        {
            markRow[j] = b;
        }
    }

    /*
     * Helper function, set the matrix to marked
     */
    private void initTab(boolean marked, boolean tab[][])
    {
        for (int i = 0; i < tab.length; i++)
        {
            for (int j = 0; j < tab.length; j++)
            {
                tab[i][j] = marked;
            }
        }
    }

    public String getStepShortDescription()
    {
        return stepsShortDescription[step];
    }

    /*
     * selection de zeros encadres par ligne
     */
    private void step10AffectZeroByRow()
    {
        Integer nbZero = 0, xZero = null, yZero = null;

        for (int row = 0; row < tab.length; row++)
        {
            for (int col = 0; col < tab.length; col++)
            {
                if (tab[row][col] == 0)
                {
                    xZero = row;
                    yZero = col;
                    nbZero++;
                }
            }
            /* on selectionne si il n'y a qu'un seul zero sur la ligne et
            si il n'y a pas de zeros encadres sur la colonne*/
            if (nbZero == 1 && !isMarkedZeroCol(yZero))
            {
                tabMarkedZero[xZero][yZero] = true;
            }
            nbZero = 0;
        }
    }

    /*
     * on creer un zero par ligne par soustraction
     */
    private void step1SubstractAllRow()
    {
        for (int i = 0; i < this.tab[0].length; i++)
        {
            step1SubstractRow(i);
        }
    }

    private void step1SubstractRow(int row)
    {
        int valueToSobstract;
        /*
         * si la "meilleur valeur" est la plus petite, on recherche le mini
         * sinon le maxi
         * ceci n'est plus d'actualite depuis que l'on multiplie juste la
         * matrice par -1 en fonction de la recherche maximisation/minimisation
         */
//        if(this.minimize){
        valueToSobstract = chercheMinRow(row, tab);
//        }else{
//            valueToSobstract=chercheMaxRow(row,tab);
//        }
        /*on soustrait ensuite cette valeur à chaque élément de la ligne*/
        for (int i = 0; i < this.tab[row].length; i++)
        {
            tab[row][i] = tab[row][i] - valueToSobstract;
        }
    }

    /*
     * pour chaque colonne, on cree un zero par soustraction
     */
    private void step2SubstractAllCol()
    {
        for (int i = 0; i < this.tab[0].length; i++)
        {
            step2SubstractCol(i);
        }
    }

    private void step2SubstractCol(int col)
    {
        int valueToSobstract;
        /*
         * si la "meilleur valeur" est la plus petite, on recherche le mini
         * sinon le maxi
         * ceci n'est plus d'actualite depuis que l'on multiplie juste la
         * matrice par -1 en fonction de la recherche maximisation/minimisation
         */
//        if(this.minimize){
        valueToSobstract = chercheMinCol(col, tab);
//
//        }else{
//            valueToSobstract=chercheMaxCol(col, tab);
//        }
        /*on soustrait ensuite cette valeur à chaque élément de la ligne*/
        for (int i = 0; i < this.tab[col].length; i++)
        {
            tab[i][col] = tab[i][col] - valueToSobstract;
        }
    }

    private boolean step3SelectMarkZero()
    {
//        Integer nbZero=0, xZero=null, yZero=null;
//
//        for(int col=0;col<tab.length;col++)
//        {
//            for(int row=0;row<tab.length;row++)
//            {
//                if(tab[row][col]==0)
//                {
//                    xZero=row;
//                    yZero=col;
//                    nbZero++;
//                }
//            }
//            /* on selectionne si il n'y a qu'un seul zero sur la colonne et
//             qu'il n'y pas de zero encadres sur la ligne */
//            if(nbZero==1 && !isMarkedZeroRow(tabMarkedZero, xZero)){
//                tabMarkedZero[xZero][yZero]=true;
//            }
//            nbZero=0;
//        }
//
//        nbZero=0;
//        for(int row=0;row<tab.length;row++)
//        {
//            for(int col=0;col<tab.length;col++)
//            {
//                if(tab[row][col]==0)
//                {
//                    xZero=row;
//                    yZero=col;
//                    nbZero++;
//                }
//            }
//            /* on selectionne si il n'y a qu'un seul zero sur la ligne et
//             qu'il n'y pas de zero encadres sur la colonne*/
//            if(nbZero==1 && !isMarkedZeroCol(yZero)){
//                tabMarkedZero[xZero][yZero]=true;
//            }
//            nbZero=0;
//        }
        if (step11Affect0Soluce())
        {
            return true;
        }

        step10Affect0Mark();
        Vector<Integer> vectNbMarkedZero = new Vector<Integer>();
        int nbMarkedZero = 0;
        for (int nbPartielSoluce = 0; nbPartielSoluce < partialSoluce.size(); nbPartielSoluce++)
        {
            nbMarkedZero = 0;
            for (int i = 0; i < partialSoluce.get(nbPartielSoluce).length; i++)
            {
                if (isMarkedZeroRow(partialSoluce.get(nbPartielSoluce), i))
                {
                    nbMarkedZero++;
                }
            }
            vectNbMarkedZero.add(nbMarkedZero);
        }
        int maxZero = 0;
        for (int i = 0; i < vectNbMarkedZero.size(); i++)
        {
            if (maxZero < vectNbMarkedZero.get(i))
            {
                maxZero = vectNbMarkedZero.get(i);
            }
        }
        for (int i = 0; i < vectNbMarkedZero.size(); i++)
        {
            if (vectNbMarkedZero.get(i) == maxZero)
            {
                tabMarkedZero = partialSoluce.get(i);
            }
        }

        return false;
    }

    /*
     * on marque toute ligne n'ayant pas de zero encadre
     */
    private void step4MarkRow()
    {
        initTab(false, markRow);
        for (int row = 0; row < tabMarkedZero.length; row++)
        {
            if (!isMarkedZeroRow(tabMarkedZero, row))
            {
                markRow[row] = true;
            }
        }
    }

    /*
     * on marque toute colonne ayant un 0 barré sur une ligne marquée
     */
    private void step5MarkCol()
    {
        initTab(false, markCol);

        /*
         * recherche de ligne marquée
         */
        for (int row = 0; row < tab.length; row++)
        {
            if (markRow[row] == true)
            {
                for (int col = 0; col < tab.length; col++)
                {
                    if (tabMarkedZero[row][col] == false && tab[row][col] == 0)
                    {
                        markCol[col] = true;
                    }
                }
                //ATTENTION, IL FAUDRAIT RECHERCHER UN ZERO NON ENCADRE
            }
            //si on trouve une marquée,
        }
    }

    /*
     * on marque toute ligne ayant un 0 encadré sur une colonne marquée
     */
    private void step6MarkRowCol()
    {
        for (int col = 0; col < tab.length; col++)
        {
            if (markCol[col])
            {
                if (isMarkedZeroCol(col))
                {
                    for (int row = 0; row < tabMarkedZero.length; row++)
                    {
                        if (tabMarkedZero[row][col])
                        {
                            markRow[row] = true;
                        }
                    }
                }
            }
        }
    }

    private void step7Iterate()
    {
        int nbMarkedRowAfter = -1, nbMarkedRowBefore = nbMarkedRow();
        int nbMarkedColAfter = -1, nbMarkedColBefore = nbMarkedCol();
        do
        {
            step5MarkCol();
            step6MarkRowCol();
            nbMarkedColAfter = nbMarkedCol();
            nbMarkedRowAfter = nbMarkedRow();
            if (nbMarkedColAfter != nbMarkedColBefore)
            {
                nbMarkedColBefore = nbMarkedColAfter;
                nbMarkedColAfter = -1;
            }
            if (nbMarkedRowAfter != nbMarkedRowBefore)
            {
                nbMarkedRowBefore = nbMarkedRowAfter;
                nbMarkedRowAfter = -1;
            }
        } while (nbMarkedColAfter != nbMarkedColBefore && nbMarkedRowAfter != nbMarkedRowBefore);
    }

    private void step8StrikeRowCol()
    {
        tabTemp = new int[nbMarkedRow()][tab.length - nbMarkedCol()];
        int rowTabTemp = 0, colTabTemp = 0;
        for (int row = 0; row < tab.length; row++)
        {
            for (int col = 0; col < tab.length; col++)
            {
                if (markCol[col] == false && markRow[row] == true)
                {
                    tabTemp[rowTabTemp][colTabTemp] = tab[row][col];
                    colTabTemp++;
                }
            }
            if (colTabTemp == tabTemp[0].length)
            {
                rowTabTemp++;
            }
            colTabTemp = 0;
        }
    }

    private void step9SubstractNoMark()
    {
        int value;
        if (minimize)
        {
            value = chercheMinTab(tabTemp);
        } else
        {
            value = chercheMaxTab(tabTemp);
        }
        for (int row = 0; row < tab.length; row++)
        {
            for (int col = 0; col < tab.length; col++)
            {
                //si element rayé deux fois
                if (this.markRow[row] == false && markCol[col] == true)
                {
                    tab[row][col] += value;
                }
                //si element non rayé
                if (this.markRow[row] == true && markCol[col] == false)
                {
                    tab[row][col] -= value;
                }
            }
        }
    }

    private boolean step10Affect0Mark()
    {
        arbre = new ArbreNAire<Integer>();
        arbre.initRacine(Integer.MIN_VALUE, Integer.MIN_VALUE);
        buildArbreZero(0, 0);
        soluce.clear();
        initTab(false, oneSoluce);
        searchSoluce(arbre, oneSoluce);
        if (soluce.isEmpty())
        {
            return false;
        } else
        {
            return true;
        }
    }

    private boolean step11Affect0Soluce()
    {
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
        buildArbreSoluce(0, 0);
        soluce.clear();
        initTab(false, oneSoluce);
        searchSoluce(arbre, oneSoluce);
        if (soluce.isEmpty())
        {
            return false;
        } else
        {
            return true;
        }
    }

    public boolean isMinimize()
    {
        return minimize;
    }

    public void setMinimize(boolean minimize)
    {
        this.minimize = minimize;
    }

    public int[][] getTab()
    {
        return tab;
    }

    public void setTab(int[][] tab)
    {
        this.tab = tab;
    }

    // TODO[DRY]: code to be merged with goToNextStep
    public void resolveMatrix()
    {
        System.out.println("tab init");
        affiche(getTab());
        step1SubstractAllRow();
        System.out.println("soustraction ligne");
        affiche(getTab());
        step2SubstractAllCol();
        System.out.println("soustraction colonne");
        affiche(getTab());
        do
        {
            if (!step3SelectMarkZero())
            {
                System.out.println("zeros encadre");
                affiche(getTabMarkedZero());
                step4MarkRow();
                System.out.println("marquage ligne");
                affiche(getMarkRow());
                //algo.affiche(algo.getTabMarkedZero());
                step5MarkCol();
                System.out.println("marquage colonne");
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
            }
        } while (!step11Affect0Soluce());
    }


    /*
     * Execute l'operation suivante et incremente l'attribut step
     */
    public void goToNextStep()
    {
        switch (this.step)
        {
            case 0:
                System.out.println("tab init");
                step++;
                break;
            case 1:
                step1SubstractAllRow();
                System.out.println("soustraction ligne");
                affiche(getTab());
                step++;
                break;
            case 2:
                step2SubstractAllCol();
                System.out.println("soustraction colonne");
                affiche(getTab());
                step++;
                break;
            case 3:
                if (!step3SelectMarkZero())
                {
                    step++;
                } else
                {
                    step=11;
                }
                System.out.println("zeros encadre");
                affiche(getTabMarkedZero());
                break;
            case 4:
                step4MarkRow();
                System.out.println("marquage ligne");
                affiche(getMarkRow());
                //algo.affiche(algo.getTabMarkedZero());
                step++;
                break;
            case 5:
                step5MarkCol();
                System.out.println("marquage colonne");
                affiche(getMarkCol());
                //algo.affiche(algo.getTabMarkedZero());
                break;
            case 6:
                step6MarkRowCol();
                System.out.println("marquage ligne/colonne");
                affiche(getMarkRow());
                affiche(getMarkCol());
                break;
            case 7:
                step7Iterate();
                affiche(getTab());
                System.out.println("nouveau tableau");
                step++;
                break;
            case 8:
                step8StrikeRowCol();
                affiche(getTabTemp());
                step++;
                break;
            case 9:
                step9SubstractNoMark();
                affiche(getTab());
                step++;
                break;
            case 10:
                // TODO: nothing to do, where the hell is the step 10?
                step++;
                break;
            case 11:
                if (!step11Affect0Soluce())
                {
                    /*
                     * if still has zero to affect go back to step 3
                     */
                    step=3;
                } else
                {
                    /*
                     * otherwise finish
                     */
                    step++;
                }
                break;
            default:
                System.out.println("Finished");
        }
        
        /*
         * Notify listeners for the changes
         */
        notifyAlgoModelListeners();
    }


    public Vector<boolean[][]> getResolvedMatrix()
    {
        if ((soluce == null) || (soluce.isEmpty()))
        {
            resolveMatrix();
        }
        return soluce;
    }

    private int chercheMinRow(int row, int[][] tab)
    {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < tab[0].length; i++)
        {
            if (tab[row][i] < min)
            {
                min = tab[row][i];
            }
        }
        return min;
    }

    private int chercheMaxRow(int row, int[][] tab)
    {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < tab[0].length; i++)
        {
            if (tab[row][i] > max)
            {
                max = tab[row][i];
            }
        }
        return max;
    }

    private int chercheMinCol(int col, int[][] tab)
    {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < tab[0].length; i++)
        {
            if (tab[i][col] < min)
            {
                min = tab[i][col];
            }
        }
        return min;
    }

    private int chercheMaxCol(int col, int[][] tab)
    {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < tab[0].length; i++)
        {
            if (tab[i][col] > max)
            {
                max = tab[i][col];
            }
        }
        return max;
    }

    private int chercheMaxTab(int[][] tab)
    {
        Integer max = Integer.MIN_VALUE;
        int temp;
        for (int row = 0; row < tab.length; row++)
        {
            temp = chercheMaxRow(row, tab);
            if (temp > max)
            {
                max = temp;
            }
        }
        return max;
    }

    private int chercheMinTab(int[][] tab)
    {
        Integer min = Integer.MAX_VALUE;
        int temp;
        for (int row = 0; row < tab.length; row++)
        {
            temp = chercheMinRow(row, tab);
            if (temp < min)
            {
                min = temp;
            }
        }
        return min;
    }

    private boolean isMarkedZeroRow(boolean[][] tab, int row)
    {
        int col = 0;
        
        while (col < tab.length-1 && !(tab[row][col]))
        {
            col++;
        }
        return (tab[row][col]);
    }

    private boolean isMarkedZeroCol(int col)
    {
        int row = 0;
        
        while (row < tabMarkedZero.length-1 && !(tabMarkedZero[row][col]))
        {
            row++;
        }
        return (tabMarkedZero[row][col]);
    }

    private int nbZeroRow(int row)
    {
        int nbZero = 0;
        for (int col = 0; col < tab.length; col++)
        {
            if (tab[row][col] == 0)
            {
                nbZero++;
            }
        }
        return nbZero;
    }

    private int nbZeroCol(int col)
    {
        int nbZero = 0;
        for (int row = 0; row < tab.length; row++)
        {
            if (tab[row][col] == 0)
            {
                nbZero++;
            }
        }
        return nbZero;
    }

    public boolean[][] getTabMarkedZero()
    {
        return tabMarkedZero;
    }

    private void affiche(boolean[] tab)
    {
        String sortie = new String();
        for (int row = 0; row < tab.length; row++)
        {
            sortie += tab[row] + " ";
        }
        System.out.println(sortie);
    }

    public boolean[] getMarkCol()
    {
        return markCol;
    }

    public boolean[] getMarkRow()
    {
        return markRow;
    }

    private int nbMarkedCol()
    {
        int nbMarkedCol = 0;
        for (int i = 0; i < markCol.length; i++)
        {
            if (markCol[i])
            {
                nbMarkedCol++;
            }
        }
        return nbMarkedCol;
    }

    private int nbMarkedRow()
    {
        int nbMarkedRow = 0;
        for (int i = 0; i < markRow.length; i++)
        {
            if (markRow[i])
            {
                nbMarkedRow++;
            }
        }
        return nbMarkedRow;
    }

    public void affiche(boolean[][] tab)
    {
        String sortie = new String();
        for (int row = 0; row < tab.length; row++)
        {
            for (int col = 0; col < tab[row].length; col++)
            {
                sortie += tab[row][col] + " ";
            }
            sortie += "\n";
        }
        System.out.println(sortie);
    }

    private void affiche(int[][] tab)
    {
        String sortie = new String();
        for (int row = 0; row < tab.length; row++)
        {
            for (int col = 0; col < tab[row].length; col++)
            {
                sortie += tab[row][col] + " ";
            }
            sortie += "\n";
        }
        System.out.println(sortie);
    }

    private void afficheSoluce()
    {
        for (int i = 0; i < soluce.size(); i++)
        {
            affiche(soluce.get(i));
        }
    }

    public int[][] getTabTemp()
    {
        return tabTemp;
    }

    public ArbreNAire<Integer> getArbre()
    {
        return arbre;
    }

    public Vector<boolean[][]> getSoluce()
    {
        return soluce;
    }

    public boolean[][] getOneSoluce()
    {
        return oneSoluce;
    }

    private boolean searchSoluce(ArbreNAire arbre, boolean oneSoluce[][])
    {
        if (!arbre.isNoeudFeuille())
        {
            for (int i = 0; i < arbre.getNbFils(); i++)
            {
                if (!arbre.isRacine())
                {
                    for (int k = 0; k < tab.length; k++)
                    {
                        oneSoluce[(Integer) (arbre.getVue().getRow())][k] = false;
                    }
                    oneSoluce[(Integer) (arbre.getVue().getRow())][(Integer) (arbre.getVue().getCol())] = true;
                } else
                {
                }
                arbre.goToFils(i);
                searchSoluce(arbre, oneSoluce);
                arbre.goToPere();
            }

        }
        else // (arbre.isNoeudFeuille())
        {
            for (int k = 0; k < tab.length; k++)
            {
                oneSoluce[(Integer) (arbre.getVue().getRow())][k] = false;
            }
            oneSoluce[(Integer) (arbre.getVue().getRow())][(Integer) (arbre.getVue().getCol())] = true;
            boolean tmp[][] = new boolean[tab.length][tab.length];
            for (int i = 0; i < tab.length; i++)
            {
                for (int j = 0; j < tab.length; j++)
                {
                    tmp[i][j] = oneSoluce[i][j];
                }
            }
            this.partialSoluce.add(tmp);
            if ((Integer) (arbre.getVue().getRow()) == (tab.length - 1))
            {
                this.soluce.add(tmp);
            }
        }
        return true;
    }

    private void buildArbreSoluce(int row, int col)
    {
        if (row < tab.length)
        {
            for (int i = col; i < tab.length; i++)
            {
                if (tab[row][i] == 0)
                {
                    Noeud<Integer> vue = arbre.getVue();
                    if (isPossibleToCreate(arbre, row, i))
                    {
                        arbre.setVue(vue);
                        int fils = arbre.addFils(row, i);
                        arbre.goToFils(fils);
                        row++;
                        buildArbreSoluce(row, 0);
                        row--;
                        arbre.goToPere();
                    } else
                    {
                        arbre.setVue(vue);
                    }
                }
            }
        }
    }

    private void buildArbreZero(int row, int col)
    {
        int fils;
        Noeud<Integer> vue;
        boolean temp = false;
        if (row < tab.length)
        {
            for (int i = col; i < tab.length; i++)
            {
                if (arbre.getNbFils() > 0)
                {
                    for (int k = 0; k < arbre.getNbFils(); k++)
                    {
                        if (!arbre.getVue().getFils().get(k).getCol().equals(k))
                        {
                            temp = true;
                        }

                    }

                }
                if (tab[row][i] == 0 && !temp)
                {
                    // Save de la vue pour ne pas la perdre pendant le parcours
                    vue = arbre.getVue();
                    if (isPossibleToCreate(arbre, row, i))
                    {
                        arbre.setVue(vue);
                        // equivalent a un marquage ligne/colonne
                        fils = arbre.addFils(row, i);
                        arbre.goToFils(fils);
                        row++;
                        buildArbreZero(row, 0);
                        row--;
                        arbre.goToPere();
                    } else
                    {
                        // restore la vue a la sauvegarde
                        arbre.setVue(vue);
                    }
                }
            }
            row++;
            buildArbreZero(row, 0);
        }
    }

    private boolean isPossibleToCreate(ArbreNAire<Integer> arbre, Integer row, Integer col)
    {
        boolean result = true;
        if (arbre.getVue().getRow() != row && arbre.getVue().getCol() != col && !arbre.isRacine() && result == true)
        {
            arbre.goToPere();
            result = isPossibleToCreate(arbre, row, col);
        } else if (arbre.isRacine())
        {
            result = true;
        } else
        {
            return false;
        }
        return result;
    }

    public static void main(String[] args)
    {
        Algo algo = new Algo(false, 5);

        algo.resolveMatrix();

        System.out.println("solutions");
        algo.afficheSoluce();
        System.out.println("");
    }

    // TODO[cleaning]: this could really get part of an abstract class Algo
    public boolean isFirstStep()
    {
        return step == 0;
    }

    public boolean isLastStep()
    {
        return step == 9;
    }

    public void addAlgoModelListener(final AlgoModelListener algoModelListener)
    {
        if (!this.algoModelListeners.contains(algoModelListener))
        {
            this.algoModelListeners.add(algoModelListener);
            notifyAlgoModelListener(algoModelListener);
        }
    }

    public void removeAlgoModelListener(final AlgoModelListener algoModelListener)
    {
        this.algoModelListeners.remove(algoModelListener);
    }

    private void notifyAlgoModelListeners()
    {
        for (final AlgoModelListener algoModelListener : this.algoModelListeners)
        {
            notifyAlgoModelListener(algoModelListener);
        }
    }

    private void notifyAlgoModelListener(final AlgoModelListener algoModelListener)
    {
        algoModelListener.algoModelChanged(this);
    }
}
