// Blanchard Allan
package Entity.sort.mob;

import Entity.Entity;
import Entity.mob.Ennemie;
import Entity.sort.Sort;

public class Fauche extends Sort {
    // ------------------------------- constructor -------------------------------
    public Fauche(int position) {
        super("Fauche", 30, position + 1);
    }

    // --------------------------------- methode ----------------------------------
    public boolean effect(Entity lanceur, Entity vise) {
        if (lanceur instanceof Ennemie && removeMana(lanceur)) {
            vise.takeDammage((int) (3. * lanceur.getNiveau() / 4.) * dommage * (critique ? 2 : 1));
            return true;
        }
        return false;
    }
}
