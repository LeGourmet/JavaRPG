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

public class Recharge extends SortPerso {
    // ------------------------------- constructor -------------------------------
    public Recharge() {
        super("Recharger", 10, 0, new AnimatedSprite(Constantes.RECHARGE, new HashMap<>(Map.ofEntries(entry("Idle", new Animation(1, 0, 48, 48, 9)))), "Idle"));
    }

    // --------------------------------- methode ----------------------------------
    public boolean effect(Entity lanceur, Entity vise) {
        rollCrit();
        int Mrecup = 3;
        if (lanceur instanceof Personnage && removeMana(lanceur) && ((Personnage) lanceur).getListArmes().length > 0 &&
                ((Personnage) lanceur).getListArmes()[0] instanceof Arc) {
            return ((Arc) ((Personnage) lanceur).getListArmes()[0]).recharge(critique ? Mrecup + 1 : (int) (Math.random() * Mrecup + 1));
        }
        if (lanceur instanceof ArcherS && removeMana(lanceur)) {
            return ((ArcherS) lanceur).recharge(critique ? Mrecup + 1 : (int) (Math.random() * Mrecup + 1));
        }
        return false;
    }

    public String calcDammage(Personnage personnage) {
        return "" + dommage;
    }
    public String calcCout(Personnage personnage) {
        return "" + cout;
    }
}
