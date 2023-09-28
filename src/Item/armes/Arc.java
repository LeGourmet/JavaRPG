// Blanchard Allan
package Item.armes;

import Frontend.AnimatedSprite;
import Frontend.Animation;
import Frontend.Constantes;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class Arc extends Arme {
    // ------------------------------- attribute --------------------------------
    private final static int maxFleche = 99;
    private final static int baseNbFleche = 15;
    private final boolean infini;
    private int nbFleche;

    // ------------------------------- constructor -------------------------------
    public Arc() {
        super(25, 1, "Baton et ficelle", 1, new AnimatedSprite(Constantes.ARC, new HashMap<>(Map.ofEntries(entry("Idle", new Animation(1, 0, 32, 32, 9)))), "Idle"));
        this.nbFleche = baseNbFleche;
        this.infini = false;
    }

    public Arc(int dammage, int nbFleche, int value, String nom, int levelMin, boolean infini) {
        super(dammage, value, nom, levelMin, new AnimatedSprite(Constantes.ARC, new HashMap<>(Map.ofEntries(entry("Idle", new Animation(1, 0, 32, 32, 9)))), "Idle"));
        this.nbFleche = nbFleche;
        this.infini = infini;
    }

    // --------------------------------- getter ----------------------------------
    public int getNbFleche() {
        return this.nbFleche;
    }
    public int getBaseNbFleche() {
        return baseNbFleche;
    }
    public int getMaxFleche() {
        return maxFleche;
    }
    public boolean isInfini() {
        return infini;
    }

    // --------------------------------- methode ----------------------------------
    public boolean useArrow() {
        if (nbFleche > 0) {
            if (!infini) {
                --nbFleche;
            }
            return true;
        }
        return false;
    }

    public boolean recharge(int nb) {
        if (nbFleche < maxFleche) {
            nbFleche = Math.min(maxFleche, nbFleche + nb);
            return true;
        }
        return false;
    }

    public String afficher() {
        String n = nom + "\n";
        String dmg = "Dégâts : " + dommage + "\n";
        String munitions = "Nombre de fleches : " + (infini ? "ilimité" : nbFleche + "/" + maxFleche) + "\n";
        String lmin = "Niveau minimum : " + levelMin + "\n";
        String valeur = "Prix : " + valeurGold + " gold\n";
        return n + dmg + munitions + lmin + valeur;
    }

    public void reset() {
        this.nbFleche = baseNbFleche;
    }
}
