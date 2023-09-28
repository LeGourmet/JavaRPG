// Blanchard Allan
package Entity.sort;

import Entity.Entity;

public abstract class Sort implements Comparable<Sort> {
    // ------------------------------- attribute --------------------------------
    protected final String nom;
    protected final int cout;
    protected final int dommage;
    protected boolean critique;

    // ------------------------------- constructor -------------------------------
    public Sort(String n, int c, int d) {
        this.nom = n;
        this.cout = c;
        this.dommage = d;
        this.critique = false;
    }

    // --------------------------------- getter ----------------------------------
    public int getDommage() {
        return this.dommage;
    }
    public int getCout() {
        return this.cout;
    }
    public String getNom() {
        return this.nom;
    }
    public boolean isCritique() {
        return this.isCritique();
    }

    // ---------------------------- abstract methode ------------------------------
    public abstract boolean effect(Entity lanceur, Entity vise);

    // --------------------------------- methode ----------------------------------
    protected void rollCrit() {
        critique = (Math.random() < 0.1);
    }
    protected boolean removeMana(Entity entity) {
        return entity.removeMana(cout);
    }
    protected boolean removeVie(Entity entity) {
        return entity.removeVie(cout);
    }
    public int compareTo(Sort o) {
        return Integer.compare(cout, o.getCout());
    }
}
