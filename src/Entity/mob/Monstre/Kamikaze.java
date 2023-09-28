// Blanchard Allan
package Entity.mob.Monstre;

import Entity.Entity;
import Entity.Inventaire.Inventaire;
import Entity.personnages.Personnage;
import Entity.sort.Sort;
import Entity.sort.mob.Explosion;
import Entity.sort.mob.Rmana;
import Frontend.AnimatedSprite;
import Frontend.Animation;
import Frontend.Constantes;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class Kamikaze extends Monstre {
    // ------------------------------- constructor -------------------------------
    public Kamikaze() {
        super("Kamikaze", new AnimatedSprite(Constantes.KAMIKAZE,
                        new HashMap<>(Map.ofEntries(
                                entry("Idle", new Animation(8, 0, 100, 100, 9))
                        )),
                        "Idle"),
                0.5f, 250, 250, 0, 120,
                new Sort[]{new Rmana(), new Explosion()},
                new Inventaire(0), 0, 0, 2, 15, 0, 1
        );
    }

    // --------------------------------- methode ----------------------------------
    public String playTurn(Entity adversaire) {
        if (!dead()) {
            if (Math.random() > chanceMiss) {
                if (listSort[1].effect(this, adversaire)) {
                    return "Utilise : " + listSort[1].getNom();
                }
                if (listSort[0].effect(this, adversaire)) {
                    return "Utilise : " + listSort[0].getNom();
                }
                return "Passe son tour";
            }
            return "Trébuche!";
        }
        return "";
    }

    public void adjust(Personnage adversaire) {
        int lv = Math.max(adversaire.getNiveau() - this.niveau, 0);
        niveau += lv;
        mvie += 7 * lv;
        vie = mvie;
        gold += 3 * lv;
        experience += lv;
    }
}
