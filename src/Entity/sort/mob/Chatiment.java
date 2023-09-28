// Blanchard Allan
package Entity.sort.mob;

import Entity.Entity;
import Entity.mob.Ennemie;
import Entity.sort.Sort;

public class Chatiment extends Sort {
    // ------------------------------- constructor -------------------------------
    public Chatiment() {
        super("Chatiment vénale", 30, 10);
    }

    // --------------------------------- methode ----------------------------------
    public boolean effect(Entity lanceur, Entity vise) {
        if (removeMana(lanceur) && lanceur instanceof Ennemie) {
            vise.takeDammage((int) ((critique ? 2. : 1.) * (vise.getGold() / ((lanceur.getGold() / 10.f) * (vise.getNiveau() / 10.f))) * (dommage + lanceur.getNiveau())));
            return true;
        }
        return false;
    }
}
