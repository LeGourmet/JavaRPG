// Blanchard Allan
package Entity.sort.mob;

import Entity.Entity;
import Entity.mob.Monstre.Monstre;
import Entity.sort.Sort;

public class Buff extends Sort {
    // ------------------------------- constructor -------------------------------
    public Buff() {
        super("Concentration", 10, 10);
    }

    // --------------------------------- methode ----------------------------------
    public boolean effect(Entity lanceur, Entity vise) {
        if (removeMana(lanceur) && lanceur instanceof Monstre && ((Monstre) lanceur).getChanceEsquive() > 0. + 2. * (dommage / 100.) && ((Monstre) lanceur).getChanceMiss() > 0. + 2. * (dommage / 100.)) {
            rollCrit();
            ((Monstre) lanceur).lessMiss((float) ((critique ? 2. : 1.) * dommage / 100.));
            ((Monstre) lanceur).lessEsq((float) ((critique ? 2. : 1.) * dommage / 100.));
            return true;
        }
        return false;
    }
}
