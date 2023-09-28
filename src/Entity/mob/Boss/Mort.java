// Blanchard Allan
package Entity.mob.Boss;

import Entity.Entity;
import Entity.Inventaire.Inventaire;
import Entity.personnages.Personnage;
import Entity.sort.Sort;
import Entity.sort.mob.Fauche;
import Frontend.AnimatedSprite;
import Frontend.Animation;
import Frontend.Constantes;
import Item.armes.Epee;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class Mort extends Boss {
    // ------------------------------- constructor -------------------------------
    public Mort(int position) {
        super("Mort", new AnimatedSprite(Constantes.MORT,
                        new HashMap<>(Map.ofEntries(
                                entry("Idle", new Animation(3, 0, 48, 48, 9))
                        )),
                        "Idle"),
                0.f, 1500, 1500, 40, 40,
                new Sort[]{new Fauche(position)}, new Inventaire(1),
                0, 0, 10, 0, 300, 40, 0.1f, 200);
        inventaire.add(new Epee(75, 3750, "Deuille-givre", 50));
    }

    // --------------------------------- methode ----------------------------------
    public String playTurn(Entity adversaire) {
        if (!dead()) {
            if (listSort[0].effect(this, adversaire)) {
                return "Utlise : " + listSort[0].getNom();
            }
            return "Passe son tour";
        }
        return "";
    }

    public void adjust(Personnage adversaire) {
        int lv = Math.max(adversaire.getNiveau() - this.niveau, 0);
        niveau += lv;
        mvie += 42 * lv;
        gold += 30 * lv;
        vie = mvie;
        experience += 12 * lv;
        if (niveau > 50) {
            inventaire.sell(0);
            if (niveau <= 80) {
                inventaire.add(new Epee(105, 9600, "Porte-Cendre", 80));
            } else {
                inventaire.add(new Epee(125, 15000, "Lame de la prophétie", 100));
            }
        }
    }
}
