// Blanchard Allan
package Entity.mob.Boss;

import Entity.Entity;
import Entity.Inventaire.Inventaire;
import Entity.personnages.Personnage;
import Entity.sort.Sort;
import Entity.sort.mob.AutoMob;
import Entity.sort.mob.Malediction;
import Frontend.AnimatedSprite;
import Frontend.Animation;
import Frontend.Constantes;
import Item.armes.Epee;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class RoiS extends Boss {
    // ------------------------------- constructor -------------------------------
    public RoiS() {
        super("Leoric", new AnimatedSprite(Constantes.ROIS,
                        new HashMap<>(Map.ofEntries(
                                entry("Idle", new Animation(4, 0, 32, 48, 9))
                        )),
                        "Idle"), 0.6f, 1000, 1000, 40, 40,
                new Sort[]{new Malediction(), new AutoMob()},
                new Inventaire(1), 5, 0, 10, 10, 100, 35, 0.3f, 125);
        inventaire.add(new Epee(45, 600, "Thanatos", 20));
    }

    // --------------------------------- methode ----------------------------------
    public String playTurn(Entity adversaire) {
        if (!dead()) {
            if (vie < (2 * mvie / 5) && adversaire.getVie() > 2 * (niveau + listSort[1].getDommage()) && listSort[0].effect(this, adversaire)) {
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
        mvie += 31 * lv;
        gold += 15 * lv;
        vie = mvie;
        experience += 8 * lv;
        inventaire.sell(0);
        inventaire.add(new Epee(niveau + 10, niveau * niveau, "Epee de niveau " + niveau, niveau));
    }
}
