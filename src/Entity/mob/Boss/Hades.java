// Blanchard Allan
package Entity.mob.Boss;

import Entity.Entity;
import Entity.Inventaire.Inventaire;
import Entity.personnages.Personnage;
import Entity.sort.Sort;
import Entity.sort.mob.DrainM;
import Entity.sort.mob.DrainV;
import Frontend.AnimatedSprite;
import Frontend.Animation;
import Frontend.Constantes;
import Item.armes.Arc;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class Hades extends Boss {
    // ------------------------------- constructor -------------------------------
    public Hades() {
        super("Hades", new AnimatedSprite(Constantes.HADES,
                        new HashMap<>(Map.ofEntries(
                                entry("Idle", new Animation(10, 0, 184, 172, 9))
                        )),
                        "Idle"), 0.0f, 1200, 1200, 100, 100,
                new Sort[]{new DrainV(), new DrainM()},
                new Inventaire(1), 0, 0, 10, 0, 100, 35, 0.3f, 150);
        inventaire.add(new Arc(90, 1, 600, "Aile du corbeau", 20, true));
    }

    // --------------------------------- methode ----------------------------------
    public String playTurn(Entity adversaire) {
        if (!dead()) {
            if (listSort[0].effect(this, adversaire)) {
                return "Utilise : " + listSort[0].getNom();
            }
            if (listSort[1].effect(this, adversaire)) {
                return "Utilise : " + listSort[1].getNom();
            }
            return "Passe son tour";
        }
        return "";
    }

    public void adjust(Personnage adversaire) {
        int lv = Math.max(adversaire.getNiveau() - this.niveau, 0);
        niveau += lv;
        mvie += 34 * lv;
        gold += 20 * lv;
        vie = mvie;
        experience += 8 * lv;
        inventaire.sell(0);
        inventaire.add(new Arc(2 * niveau + 25, 15, niveau * niveau, "Arc de niveau " + niveau, niveau, false));
    }
}
