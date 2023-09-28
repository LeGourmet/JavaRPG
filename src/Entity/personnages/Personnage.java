// Blanchard Allan
package Entity.personnages;

import Entity.Entity;
import Entity.Inventaire.Inventaire;
import Entity.personnages.chasseur.Chasseur;
import Entity.personnages.guerrier.Guerrier_Epee;
import Entity.personnages.mage.Mage;
import Entity.sort.Sort;
import Frontend.AnimatedSprite;
import Frontend.Constantes;
import Item.Item;
import Item.armes.Arc;
import Item.armes.Arme;
import Item.armes.Bouclier;
import Item.armes.Epee;
import Item.popo.PopoMana;
import Item.popo.PopoVie;
import Item.popo.Potion;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public abstract class Personnage extends Entity {
    // ------------------------------- attribute --------------------------------
    protected final static int lvMax = 100;
    protected int rmana;
    protected Arme[] listArmes;
    protected boolean[] listChecks;

    // ------------------------------- constructor -------------------------------
    public Personnage(String nom, AnimatedSprite sprite, int vie, int maxVie, int mana, int maxMana, int rmana, int niveau, int gold,
                      Arme[] armes, Inventaire inventaire, Sort[] sorts, int exp, boolean[] listChecks) {
        super(nom, sprite, vie, maxVie, mana, maxMana, gold, exp, niveau, inventaire, sorts);
        this.rmana = rmana;
        this.listArmes = armes;
        this.listSort = sorts;
        this.listChecks = listChecks;
    }

    // --------------------------------- getter ----------------------------------
    public int getDommageArme() {
        if (listArmes.length > 0) {
            return listArmes[0].getDommage();
        }
        return -1;
    }

    public int getRmana() {
        return this.rmana;
    }

    public Arme[] getListArmes() {
        return this.listArmes;
    }

    public boolean getCheck(int i) {
        if (i < listChecks.length) {
            return listChecks[i];
        }
        return false;
    }

    // ---------------------------- abstract methode ------------------------------
    public abstract void takeDammage(int d);
    public abstract boolean equiper(Arme arme);
    public abstract void levelUp(int nb);

    // --------------------------------- methode ----------------------------------
    public String playTurn(Entity adversaire) {
        if (!dead()) {
            int n, i;
            do {
                do {
                    for (i = 0; i < listSort.length; i++) {
                        System.out.println("\t- " + i + " : " + listSort[i].getNom());
                    }
                    System.out.println("\t- " + i + " : Passer son tour");
                    n = 0;
                    System.out.println(n);
                } while (n < 0 || n > listSort.length);
            } while (n != listSort.length && !castSpell(n, adversaire));
            if (n == listSort.length) {
                return "Passe son tour";
            }
            return "Utilise : " + listSort[n].getNom();
        }
        return "";
    }

    public String afficher() {
        String Arme1 = (listArmes.length > 0 ? "Arme :\t\t\t" + listArmes[0].getNom() + "\n" +
                "Dégâts de l'arme :\t" + listArmes[0].getDommage() + "\n" +
                (listArmes[0] instanceof Arc ?
                        "Nombre de fleches :\t" +
                                ((((Arc) listArmes[0]).isInfini() ? "ilimité" : ((Arc) listArmes[0]).getNbFleche() + "/" + ((Arc) listArmes[0]).getMaxFleche()) + "\n")
                        : "") : "");
        String Bouclier = (listArmes.length > 1 ? "Bouclier :\t\t\t" + listArmes[1].getNom() + "\n" +
                "Protection du bouclier :\t" + ((Bouclier) listArmes[1]).getArmure() + "\n" : "");
        String regen = "Regen Mana par tour :\t" + rmana + "\n";
        String exp = "Experience :\t\t" + experience + "/" + (int) (0.35 * niveau * niveau + 2 * niveau + 17.5) + "\n";
        String g = "Gold :\t\t\t\t" + gold + "\n";
        return "Mes Stats :\n" + Arme1 + Bouclier + regen + exp + g;
    }

    public String infos() {
        String name = nom + ", " +
                (this instanceof Mage ? "Mage" :
                        (this instanceof Chasseur ? "Chasseur" :
                                (this instanceof Guerrier_Epee ? "Barbare" : "Chevalier")))
                + " de niveau " + niveau + "\n";
        String pv = "Vie :\t\t\t\t" + vie + "/" + mvie + "\n";
        String m = "Mana :\t\t\t" + mana + "/" + mmana + "\n";
        String regen = "Regen Mana par tour :\t" + rmana + "\n";
        String exp = "Experience :\t\t" + experience + "/" + (int) (0.35 * niveau * niveau + 2 * niveau + 17.5) + "\n";
        String g = "Gold :\t\t\t\t" + gold + "\n";
        return name + pv + m + regen + exp + g;
    }

    public boolean castSpell(int n, Entity E) {
        return listSort[n].effect(this, E);
    }
    public void recupMana() {
        mana = Math.min(mmana, mana + rmana);
    }

    public void endTurn() {
        if (!dead()) {
            recupMana();
        }
    }

    private float fctXP(int lv) {
        return (float) (0.35 * lv * lv + 2 * lv + 17.5);
    }

    public int addExp(int exp) {
        experience += exp;
        int levels = 0;
        while (experience >= fctXP(niveau + levels) && (niveau + levels <= lvMax)) {
            experience -= fctXP(niveau + levels);
            levels++;
        }
        levelUp(levels);
        return levels;
    }

    public void reset() {
        this.vie = mvie;
        this.mana = mmana;
        if (this.listArmes.length == 1 && listArmes[0] instanceof Arc) {
            ((Arc) listArmes[0]).reset();
        }
    }

    public boolean boire(Potion popo) {
        if (popo.effect(this)) {
            inventaire.getItems().remove(popo);
            return true;
        }
        return false;
    }

    public Arme rollArme() {
        int cost = niveau * niveau;
        String name = " de niveau " + niveau;
        int r = (int) (Math.random() * 3);//0,1,2
        return (r == 0 ? new Arc(2 * niveau + 25, 15, cost, "Arc" + name, niveau, false) :
                (r == 1 ? new Epee(niveau + 10, cost, "Epee" + name, niveau) :
                        new Bouclier((int) (0.4 * niveau + 5.), cost, "Bouclier" + name, niveau)));
    }

    public Potion rollPotion() {
        float r = (float) Math.random();
        return (Math.random() > 0.5 ? new PopoVie(r, niveau) : new PopoMana(r, niveau));
    }

    public void check(int i) {
        if (i < listChecks.length) {
            listChecks[i] = true;
        }
    }

    protected String exportToString() {
        String exp = this.getClass().getSimpleName() + "\n"
                + nom + "\n"
                + vie + "\n"
                + mvie + "\n"
                + mana + "\n"
                + mmana + "\n"
                + rmana + "\n"
                + niveau + "\n"
                + gold + "\n";
        if (!Arrays.equals(listArmes, null)) {
            exp += "listArmes;" + listArmes.length + "\n";
            for (Arme item : listArmes) {
                if (item instanceof Arc) {
                    exp += "Arc" + ";" // type
                            + item.getDommage() + ";" // dammage
                            + ((Arc) item).getBaseNbFleche() + ";" // nbFleche
                            + item.getValeurGold() + ";"// value
                            + item.getNom() + ";" // nom
                            + item.getLevelMin() + ";" // levelMin
                            + ((Arc) item).isInfini() + "\n"; //infini
                } else if (item instanceof Bouclier) {
                    exp += "Bouclier" + ";" // type
                            + ((Bouclier) item).getArmure() + ";" // armure
                            + item.getValeurGold() + ";" // value
                            + item.getNom() + ";" // nom
                            + item.getLevelMin() + "\n"; // levelMin
                } else if (item instanceof Epee) {
                    exp += "Epee" + ";" // type
                            + item.getDommage() + ";" // dammage
                            + item.getValeurGold() + ";" // value
                            + item.getNom() + ";" // nom
                            + item.getLevelMin() + "\n"; // levelMin
                }
            }
        }
        Item[] items = inventaire.getItems().toArray(new Item[0]);
        exp += "Inventaire;" + inventaire.getMaxObj() + ";" + items.length + "\n";
        for (Item item : items) {
            if (item instanceof Arc) {
                exp += "Arc" + ";" // type
                        + ((Arc) item).getDommage() + ";" // dammage
                        + ((Arc) item).getBaseNbFleche() + ";" // nbFleche
                        + item.getValeurGold() + ";"// value
                        + item.getNom() + ";" // nom
                        + ((Arc) item).getLevelMin() + ";" // levelMin
                        + ((Arc) item).isInfini() + "\n"; //infini
            } else if (item instanceof Bouclier) {
                exp += "Bouclier" + ";" // type
                        + ((Bouclier) item).getArmure() + ";" // armure
                        + item.getValeurGold() + ";" // value
                        + item.getNom() + ";" // nom
                        + ((Bouclier) item).getLevelMin() + "\n"; // levelMin
            } else if (item instanceof Epee) {
                exp += "Epee" + ";" // type
                        + ((Epee) item).getDommage() + ";" // dammage
                        + item.getValeurGold() + ";" // value
                        + item.getNom() + ";" // nom
                        + ((Epee) item).getLevelMin() + "\n"; // levelMin
            } else if (item instanceof PopoMana) {
                exp += "PopoMana" + ";" // type
                        + ((PopoMana) item).getPower() + ";" // power
                        + ((PopoMana) item).getNiveau() + "\n"; // niveau
            } else if (item instanceof PopoVie) {
                exp += "PopoVie" + ";" // type
                        + ((PopoVie) item).getPower() + ";" // power
                        + ((PopoVie) item).getNiveau() + "\n"; // niveau
            }
        }
        exp += experience + "\n"
                + "listChecks;" + listChecks.length + "\n";
        for (boolean check : listChecks) {
            exp += check + "\n";
        }

        return exp;
    }

    public void save(String filename) {
        String path = Constantes.SAVEPATH;
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdir();
        }

        File file = new File(path + "/" + filename);
        try {
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), false);
            fw.write(filename.split(".sav")[0] + exportToString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
