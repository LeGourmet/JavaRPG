// Blanchard Allan
package Entity.sort.personnage.guerrier;

import Entity.Entity;
import Entity.mob.Ennemie;
import Entity.mob.Monstre.BarbareS;
import Entity.personnages.Personnage;
import Entity.personnages.guerrier.Guerrier_Epee;
import Entity.sort.personnage.SortPerso;
import Frontend.AnimatedSprite;
import Frontend.Animation;
import Frontend.Constantes;
import Item.armes.Epee;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class AutoGuerrier extends SortPerso {
    // ------------------------------- constructor -------------------------------
    public AutoGuerrier() {
        super("Coup tranchant", 0, 0, new AnimatedSprite(Constantes.AUTOGUERRIER, new HashMap<>(Map.ofEntries(entry("Idle", new Animation(1, 0, 48, 48, 9)))), "Idle"));
    }

    // --------------------------------- methode ----------------------------------
    public boolean effect(Entity lanceur, Entity vise) {
        rollCrit();
        if (lanceur instanceof Personnage && removeMana(lanceur) && ((Personnage) lanceur).getListArmes().length >= 1 &&
                ((Personnage) lanceur).getListArmes()[0] instanceof Epee) {
            vise.takeDammage(((Personnage) lanceur).getListArmes()[0].getDommage() * (critique ? 2 : 1) * (lanceur instanceof Guerrier_Epee ? 2 : 1));
            return true;
        }
        if (lanceur instanceof Ennemie && removeMana(lanceur)) {
            vise.takeDammage(lanceur.getNiveau() * (critique ? 2 : 1) * (lanceur instanceof BarbareS ? 2 : 1));
            return true;
        }
        return false;
    }

    public String calcDammage(Personnage personnage) {
        return "" + personnage.getListArmes()[0].getDommage() * (personnage instanceof Guerrier_Epee ? 2 : 1);
    }
    public String calcCout(Personnage personnage) {
        return "" + getCout();
    }
}
