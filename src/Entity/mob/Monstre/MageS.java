// Blanchard Allan
package Entity.mob.Monstre;

import Entity.Entity;
import Entity.Inventaire.Inventaire;
import Entity.personnages.Personnage;
import Entity.sort.Sort;
import Entity.sort.mob.Rmana;
import Entity.sort.personnage.mage.AutoMage;
import Frontend.AnimatedSprite;
import Frontend.Animation;
import Frontend.Constantes;
import Item.popo.PopoMana;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class MageS extends Monstre {
    // ------------------------------- constructor -------------------------------
    public MageS() {
        super("Mage squelette", new AnimatedSprite(Constantes.MAGES,
                        new HashMap<>(Map.ofEntries(
                                entry("Idle", new Animation(9, 0, 64, 64, 9))
                        )),
                        "Idle"),
                0, 100, 100, 30, 30,
                new Sort[]{new AutoMage(), new Rmana()},
                new Inventaire(1), 0, 0, 10, 10, 0.5f, 5
        );
        inventaire.add(new PopoMana(0.3f, niveau));
    }

    // --------------------------------- methode ----------------------------------
    public String playTurn(Entity adversaire) {
        if (!dead()) {
            if (Math.random() > chanceMiss) {
                if (listSort[0].effect(this, adversaire)) {
                    return "Utilise : " + listSort[0].getNom();
                }
                if (listSort[1].effect(this, adversaire)) {
                    return "Utilise : " + listSort[1].getNom();
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
        mvie += 9 * lv;
        gold += 8 * lv;
        vie = mvie;
        experience += 2 * lv;
        inventaire.sell(0);
        inventaire.add(new PopoMana(0.3f, niveau));
    }
}
