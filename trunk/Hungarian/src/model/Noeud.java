/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;

/**
 *
 * @author lmouterde
 */
public class Noeud <typeItem> {
    private typeItem row;
    private typeItem col;
    private Noeud<typeItem> pere;
    private ArrayList<Noeud<typeItem>> fils = new ArrayList<Noeud<typeItem>>();

    public Noeud()
    {
        
    }

    public ArrayList<Noeud<typeItem>> getFils() {
        return fils;
    }

    public void setFils(ArrayList<Noeud<typeItem>> fils) {
        this.fils = fils;
    }

    public typeItem getCol() {
        return col;
    }

    public void setCol(typeItem col) {
        this.col = col;
    }

    public typeItem getRow() {
        return row;
    }

    public void setRow(typeItem row) {
        this.row = row;
    }

    public Noeud<typeItem> getPere() {
        return pere;
    }

    public void setPere(Noeud<typeItem> pere) {
        this.pere = pere;
    }
    
}
