/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;


/**
 *
 * @author 47
 */
public class ArbreNAire<typeItem> {

    private Noeud<typeItem> racine;
    private Noeud<typeItem> vue;
    
    public ArbreNAire() {
        racine = new Noeud<typeItem>();
        racine.setPere(null);
        racine.setRow(null);
        racine.setCol(null);
        vue = racine;
    }

    public ArbreNAire(typeItem infoRacineRow, typeItem infoRacineCol){
        this.racine = new Noeud<typeItem>();
        this.racine.setPere(null);
        initRacine(infoRacineRow, infoRacineCol);
        vue = this.racine;
    }
    //initialisation de la racine de l'arbre
    public void initRacine(typeItem infoRacineRow, typeItem infoRacineCol){
        this.racine.setRow(infoRacineRow);
        this.racine.setCol(infoRacineCol);
    }
    //ajoute un fils au noeud courant
    public int addFils(typeItem row, typeItem col){
        Noeud<typeItem> fils = new Noeud<typeItem>();
        fils.setRow(row);
        fils.setCol(col);
        fils.setPere(vue);
        vue.getFils().add(fils);
        return vue.getFils().indexOf(fils);

    }
    //accès au contenu du noeud courant
    
    //teste si le noeud courant est la racine
    public boolean isRacine(){
        return vue.getPere()==null?true:false;
    }
    //teste si le noeud courant est un noeud feuille
    public boolean isNoeudFeuille(){
        return vue.getFils().size()==0?true:false;
    }
    //retourne le nombre de fils du noeud courant
    public int getNbFils(){
        return vue.getFils().size();
    }

    //positionne le noeud courant sur la racine
    public void goToRacine(){
        vue=racine;
    }

    public Noeud<typeItem> getRacine() {
        return racine;
    }

    public void setRacine(Noeud<typeItem> racine) {
        this.racine = racine;
        if(racine!=null)
            this.racine.setPere(null);
    }

    public Noeud<typeItem> getVue() {
        return vue;
    }

    public void setVue(Noeud<typeItem> vue) {
        this.vue = vue;
    }

    //positionne le noeud courant sur le ième fils du noeud courant
    //les indices commencent à zéro;
    public boolean goToFils(int i){
        if(vue.getFils().size()<i)
        {
            return false;
        }
        else
        {
            if(vue.getFils().get(i)==null)
                return false;
            vue=vue.getFils().get(i);
            return true;
        }
    }
    //positionne le noeud courant sur le père du noeud courant
    public void goToPere(){
        if(!isRacine())
            vue=vue.getPere();
    }
    // supprime le noeud courant et sa descendance
    // le père du noeud supprimé devient le noeud courant
    public void suppressNoeud(){

    }
}
