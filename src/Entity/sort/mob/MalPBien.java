// Blanchard Allan
package Entity.sort.mob;

import Entity.Entity;
import Entity.sort.Sort;

public class MalPBien extends Sort {
    // ------------------------------- constructor -------------------------------
    public MalPBien() {
        super("Un mal pour un bien", 0, 20);
    }

    // --------------------------------- methode ----------------------------------
    public boolean effect(Entity lanceur, Entity vise) {
        rollCrit();
        lanceur.takeDammage((int) (lanceur.getMvie() / 10.));
        vise.takeDammage((int) ((2. * (lanceur.getNiveau()) + dommage) * (critique ? 2. : 1.)));
        return true;
    }
}
