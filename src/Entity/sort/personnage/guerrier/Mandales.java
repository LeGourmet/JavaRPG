// Blanchard Allan
package Entity.sort.personnage.guerrier;

import Entity.Entity;
import Entity.personnages.Personnage;
import Entity.sort.personnage.SortPerso;
import Frontend.AnimatedSprite;
import Frontend.Animation;
import Frontend.Constantes;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class Mandales extends SortPerso {
    // ------------------------------- constructor -------------------------------
    public Mandales() {
        super("Mandales", 30, 10, new AnimatedSprite(Constantes.MANDALES, new HashMap<>(Map.ofEntries(entry("Idle", new Animation(1, 0, 48, 48, 9)))), "Idle"));
    }

    // --------------------------------- methode ----------------------------------
    public boolean effect(Entity lanceur, Entity vise) {
        if (removeMana(lanceur)) {
            rollCrit();
            int Mbaffes = 3;
            vise.takeDammage((int) ((critique ? Mbaffes + 1 : +(int) (Math.random() * Mbaffes + 1)) * Math.max(lanceur.getNiveau() / 10., 1.) * dommage));
            return true;
        }
        return false;
    }

    public String calcDammage(Personnage personnage) {
        return "1-4*" + Math.max(personnage.getNiveau() / 10., 1.) * dommage;
    }
    public String calcCout(Personnage personnage) {
        return "" + cout;
    }
}
