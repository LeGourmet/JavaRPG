// Blanchard Allan
package Entity.mob.Boss;

import Entity.Entity;
import Entity.Inventaire.Inventaire;
import Entity.personnages.Personnage;
import Entity.sort.Sort;
import Entity.sort.mob.Chatiment;
import Entity.sort.mob.Don;
import Frontend.AnimatedSprite;
import Frontend.Animation;
import Frontend.Constantes;
import Item.armes.Bouclier;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class Charon extends Boss {
    // ------------------------------- constructor -------------------------------
    public Charon() {
        super("Charon", new AnimatedSprite(Constantes.CHARON,
                        new HashMap<>(Map.ofEntries(
                                entry("Idle", new Animation(2, 0, 48, 48, 9))
                        )),
                        "Idle"),
                0.5f, 500, 500, 50, 50,
                new Sort[]{new Chatiment(), new Don()},
                new Inventaire(1), 0, 0, 10, 0, 40, 20, 0.3f, 100);
        inventaire.add(new Bouclier(18, 600, "Egide", 20));
    }

    // --------------------------------- methode ----------------------------------
    public String playTurn(Entity adversaire) {
        if (!dead()) {
            if (((gold / 10.f) * (adversaire.getNiveau() / 10.f)) < 1.f && listSort[1].effect(this, adversaire)) {
                return "Utilise : " + listSort[1].getNom();
            }
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
        mvie += 19 * lv;
        gold += 18 * lv;
        vie = mvie;
        experience += 5 * lv;
        inventaire.sell(0);
        inventaire.add(new Bouclier((int) (0.4 * niveau + 10.), niveau * niveau, "Bouclier de niveau " + niveau, niveau));
    }
}
