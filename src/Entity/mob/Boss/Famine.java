// Blanchard Allan
package Entity.mob.Boss;

import Entity.Entity;
import Entity.Inventaire.Inventaire;
import Entity.personnages.Personnage;
import Entity.sort.Sort;
import Entity.sort.mob.Rmana;
import Entity.sort.personnage.mage.Decharge;
import Frontend.AnimatedSprite;
import Frontend.Animation;
import Frontend.Constantes;
import Item.popo.PopoMana;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class Famine extends Boss {
    // ------------------------------- attribute --------------------------------
    private boolean buff;
    private int position;

    // ------------------------------- constructor -------------------------------
    public Famine(int position) {
        super("Famine", new AnimatedSprite(Constantes.FAMINE,
                        new HashMap<>(Map.ofEntries(
                                entry("Idle", new Animation(3, 0, 48, 48, 9))
                        )),
                        "Idle"), 1.f, 1500, 1500, 80, 80,
                new Sort[]{new Decharge(), new Rmana()}, new Inventaire(1),
                0, 0, 0, 0, 300, 40, 0.5f, 200);
        inventaire.add(new PopoMana(0.5f, niveau));
        this.buff = false;
        this.position = position;
    }

    // --------------------------------- methode ----------------------------------
    public String playTurn(Entity adversaire) {
        if (!dead()) {
            if (!buff) {
                rmana += 10 * (4 - position);
                rvie += (int) ((getNiveau() / 10.) * (4. - position));
                buff = true;
                return "Se buff!";
            }
            if (mmana == mana && listSort[0].effect(this, adversaire)) {
                return "Utilise : " + listSort[0].getNom();
            }
            if ((mvie / 2) > vie && listSort[0].effect(this, adversaire)) {
                return "Utilise : " + listSort[0].getNom();
            }
            if ((mmana * 2 / 3) > mana && listSort[1].effect(this, adversaire)) {
                return "Utilise : " + listSort[1].getNom();
            }
            if (listSort[0].effect(this, adversaire)) {
                return "Utilise : " + listSort[0].getNom();
            }
            if (listSort[1].effect(this, adversaire)) {
                return "Utlise : " + listSort[1].getNom();
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
        inventaire.sell(0);
        if (niveau > 50) {
            inventaire.sell(0);
            if (niveau <= 80) {
                inventaire.add(new PopoMana(0.8f, niveau));
            } else {
                inventaire.add(new PopoMana(1.f, niveau));
            }
        }
    }
}
