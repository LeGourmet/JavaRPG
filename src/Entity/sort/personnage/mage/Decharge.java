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

public class Decharge extends SortPerso {
    // ------------------------------- constructor -------------------------------
    public Decharge() {
        super("Décharge arcanique", 20, 15, new AnimatedSprite(Constantes.DECHARGE, new HashMap<>(Map.ofEntries(entry("Idle", new Animation(1, 0, 48, 48, 9)))), "Idle"));
    }

    // --------------------------------- methode ----------------------------------
    public boolean effect(Entity lanceur, Entity vise) {
        if ((lanceur.getMana() - cout) < 0) {
            return false;
        }
        int dmg = 0;
        while (removeMana(lanceur)) {
            dmg += dommage * Math.max(1., lanceur.getNiveau() / 20.);
        }
        vise.takeDammage(dmg);
        return true;
    }

    public String calcDammage(Personnage personnage) {
        return "" + (int) ((personnage.getMana() / cout) * (dommage * Math.max(1., personnage.getNiveau() / 20.)));
    }
    public String calcCout(Personnage personnage) {
        return "" + (personnage.getMana() / cout) * cout;
    }
}
