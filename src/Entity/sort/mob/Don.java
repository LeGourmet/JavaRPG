// Blanchard Allan
package Entity.sort.mob;

import Entity.Entity;
import Entity.sort.Sort;

public class Don extends Sort {
    // ------------------------------- constructor -------------------------------
    public Don() {
        super("Don d'or", 0, 0);
    }

    // --------------------------------- methode ----------------------------------
    public boolean effect(Entity lanceur, Entity vise) {
        float fact = (lanceur.getGold() / 10.f) * (vise.getNiveau() / 10.f);
        if (removeMana(lanceur) && lanceur.getGold() > fact) {
            vise.addMoney(lanceur.donGold((int) (fact / 2.f)));
            return true;
        }
        return false;
    }
}
