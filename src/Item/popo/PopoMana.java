// Blanchard Allan
package Item.popo;

import Entity.Entity;
import Frontend.AnimatedSprite;
import Frontend.Animation;
import Frontend.Constantes;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class PopoMana extends Potion {
    // ------------------------------- constructor -------------------------------
    public PopoMana(float power, int niveau) {
        super((int) (power * (50. + (1. * niveau * niveau) / 3.)), "Potion de Mana", power, niveau, new AnimatedSprite(Constantes.POTIONMANA, new HashMap<>(Map.ofEntries(entry("Idle", new Animation(1, 0, 32, 32, 9)))), "Idle"));
    }

    // --------------------------------- methode ----------------------------------
    public boolean effect(Entity lanceur) {
        return lanceur.addMana((int) (lanceur.getMmana() * power)) != -1;
    }
}
