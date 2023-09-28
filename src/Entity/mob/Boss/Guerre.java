// Blanchard Allan
package Entity.mob.Boss;

import Entity.Entity;
import Entity.Inventaire.Inventaire;
import Entity.personnages.Personnage;
import Entity.sort.Sort;
import Entity.sort.personnage.guerrier.AutoGuerrier;
import Frontend.AnimatedSprite;
import Frontend.Animation;
import Frontend.Constantes;
import Item.armes.Bouclier;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class Guerre extends Boss {
    // ------------------------------- attribute --------------------------------
    private boolean buff;
    private int position;

    // ------------------------------- constructor -------------------------------
    public Guerre(int position) {
        super("Guerre", new AnimatedSprite(Constantes.GUERRE,
                        new HashMap<>(Map.ofEntries(
                                entry("Idle", new Animation(3, 0, 48, 48, 9))
                        )),
                        "Idle"), 1, 1500, 1500, 30, 30,
                new Sort[]{new AutoGuerrier()}, new Inventaire(1),
                0, 0, 0, 0, 300, 40, 0.1f, 200);
        inventaire.add(new Bouclier(30, 3750, "Yata-no-Kagami", 50));
        this.buff = false;
        this.position = position;
    }

    // --------------------------------- methode ----------------------------------
    public String playTurn(Entity adversaire) {
        if (!dead()) {
            if (!buff) {
                resist += (int) ((getNiveau() / 10.) * (4. - position));
                armor += (int) ((getNiveau() / 5.) * (4. - position));
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
        mvie += 42 * lv;
        gold += 30 * lv;
        vie = mvie;
        experience += 12 * lv;
        if (niveau > 50) {
            inventaire.sell(0);
            if (niveau <= 80) {
                inventaire.add(new Bouclier(42, 9600, "Providence", 80));
            } else {
                inventaire.add(new Bouclier(50, 15000, "Dernier Rampart", 100));
            }
        }
    }
}
