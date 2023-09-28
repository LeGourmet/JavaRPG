// Blanchard Allan
package Entity.mob.Boss;

import Entity.Entity;
import Entity.Inventaire.Inventaire;
import Entity.personnages.Personnage;
import Entity.sort.Sort;
import Entity.sort.mob.AutoCavalier;
import Frontend.AnimatedSprite;
import Frontend.Animation;
import Frontend.Constantes;
import Item.armes.Arc;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class Conquete extends Boss {
    // ------------------------------- attribute --------------------------------
    private boolean buff;
    private int position;

    // ------------------------------- constructor -------------------------------
    public Conquete(int position) {
        super("Conquête", new AnimatedSprite(Constantes.CONQUETE,
                        new HashMap<>(Map.ofEntries(
                                entry("Idle", new Animation(3, 0, 48, 48, 9))
                        )),
                        "Idle"), 1.f, 1600, 1600, 30, 30,
                new Sort[]{new AutoCavalier(position)}, new Inventaire(1),
                0, 0, 10, 0, 300, 40, 0.1f, 200);
        inventaire.add(new Arc(150, 1, 3750, "Gandiva", 50, true));
        this.buff = false;
        this.position = position;
    }

    // --------------------------------- methode ----------------------------------
    public String playTurn(Entity adversaire) {
        if (!dead()) {
            if (!buff) {
                vie -= (int) (getNiveau() * 5. * (3. - position));
                buff = true;
                return "Se buff!";
            }
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
        mvie += 44 * lv;
        gold += 30 * lv;
        vie = mvie;
        experience += 12 * lv;
        if (niveau > 50) {
            inventaire.sell(0);
            if (niveau <= 80) {
                inventaire.add(new Arc(210, 1, 9600, "L'arc d'épire", 80, true));
            } else {
                inventaire.add(new Arc(250, 1, 15000, "Arc courbe de Yang", 100, true));
            }
        }
    }
}
