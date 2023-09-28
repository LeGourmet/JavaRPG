// Blanchard Allan
package Entity.sort.mob;

import Entity.Entity;
import Entity.mob.Ennemie;
import Entity.sort.Sort;

public class AutoMob extends Sort {
    // ------------------------------- constructor -------------------------------
    public AutoMob() {
        super("Vlan dans les dents!", 30, 5);
    }

    // --------------------------------- methode ----------------------------------
    public boolean effect(Entity lanceur, Entity vise) {
        if (removeMana(lanceur) && lanceur instanceof Ennemie) {
            rollCrit();
            vise.takeDammage((lanceur.getNiveau() + dommage) * (critique ? 2 : 1));
            return true;
        }
        return false;
    }
}
