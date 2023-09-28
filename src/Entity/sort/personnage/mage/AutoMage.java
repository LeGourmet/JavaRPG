// Blanchard Allan
package Entity.sort.personnage.mage;

import Entity.Entity;
import Entity.personnages.Personnage;
import Entity.sort.personnage.SortPerso;
import Frontend.AnimatedSprite;
import Frontend.Animation;
import Frontend.Constantes;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class AutoMage extends SortPerso {
    // ------------------------------- constructor -------------------------------
    public AutoMage() {
        super("Projectile des arcanes", 10, 15, new AnimatedSprite(Constantes.AUTOMAGE, new HashMap<>(Map.ofEntries(entry("Idle", new Animation(1, 0, 48, 48, 9)))), "Idle"));
    }

    // --------------------------------- methode ----------------------------------
    public boolean effect(Entity lanceur, Entity vise) {
        if (removeMana(lanceur)) {
            rollCrit();
            vise.takeDammage((int) (2.5 * lanceur.getNiveau() + dommage) * (critique ? 2 : 1));
            return true;
        }
        return false;
    }

    public String calcDammage(Personnage personnage) {
        return "" + ((int) (2.5 * personnage.getNiveau() + dommage));
    }

    public String calcCout(Personnage personnage) {
        return "" + getCout();
    }
}
