// Blanchard Allan
package Entity.sort.personnage.chasseur;

import Entity.Entity;
import Entity.mob.Monstre.ArcherS;
import Entity.personnages.Personnage;
import Entity.sort.personnage.SortPerso;
import Frontend.AnimatedSprite;
import Frontend.Animation;
import Frontend.Constantes;
import Item.armes.Arc;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class AutoChasseur extends SortPerso {
    // ------------------------------- constructor -------------------------------
    public AutoChasseur() {
        super("Tire rapide", 0, 0, new AnimatedSprite(Constantes.AUTOCHASSEUR, new HashMap<>(Map.ofEntries(entry("Idle", new Animation(1, 0, 48, 48, 9)))), "Idle"));
    }

    // --------------------------------- methode ----------------------------------
    public boolean effect(Entity lanceur, Entity vise) {
        rollCrit();
        if (lanceur instanceof Personnage && removeMana(lanceur) && ((Personnage) lanceur).getListArmes().length > 0 &&
                ((Personnage) lanceur).getListArmes()[0] instanceof Arc &&
                ((Arc) ((Personnage) lanceur).getListArmes()[0]).useArrow()) {
            vise.takeDammage(((Personnage) lanceur).getListArmes()[0].getDommage() * (critique ? 2 : 1));
            return true;
        }
        if (lanceur instanceof ArcherS && ((ArcherS) lanceur).useArrow() && removeMana(lanceur)) {
            vise.takeDammage(lanceur.getNiveau() * 2 * (critique ? 2 : 1));
            return true;
        }
        return false;
    }

    public String calcDammage(Personnage personnage) {
        return "" + personnage.getListArmes()[0].getDommage();
    }
    public String calcCout(Personnage personnage) {
        return "" + getCout();
    }
}
