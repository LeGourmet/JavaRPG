// Blanchard Allan
package Item.popo;

import Entity.Entity;
import Frontend.AnimatedSprite;
import Frontend.Animation;
import Frontend.Constantes;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class PopoVie extends Potion {
    // ------------------------------- constructor -------------------------------
    public PopoVie(float power, int niveau) {
        super((int) (power * (100. + (2. * niveau * niveau) / 3.)), "Potion de vie", power, niveau, new AnimatedSprite(Constantes.POTIONVIE, new HashMap<>(Map.ofEntries(entry("Idle", new Animation(1, 0, 32, 32, 9)))), "Idle"));
    }

    // --------------------------------- methode ----------------------------------
    public boolean effect(Entity lanceur) {
        return lanceur.heal((int) (lanceur.getMvie() * power)) != -1;
    }
}
