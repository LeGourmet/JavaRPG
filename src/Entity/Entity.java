// Blanchard Allan
package Entity;

import Entity.Inventaire.Inventaire;
import Entity.sort.Sort;
import Frontend.AnimatedSprite;

public abstract class Entity {
    // ------------------------------- attribute --------------------------------
    protected final String nom;
    protected AnimatedSprite sprite;
    protected int vie;
    protected int mvie;
    protected int mana;
    protected int mmana;
    protected int niveau;
    protected Sort[] listSort;
    protected Inventaire inventaire;
    protected int experience;
    protected int gold;

    // ------------------------------- constructor -------------------------------
    public Entity(String nom, AnimatedSprite sprite, int vie, int maxVie, int mana, int maxMana, int gold, int exp, int niveau, Inventaire inventaire, Sort[] sorts) {
        this.nom = nom;
        this.sprite = sprite;
        this.vie = vie;
        this.mvie = maxVie;
        this.mana = mana;
        this.mmana = maxMana;
        this.gold = gold;
        this.experience = exp;
        this.inventaire = inventaire;
        this.listSort = sorts;
        this.niveau = niveau;
    }

    // --------------------------------- getter ----------------------------------
    public String getNom() {
        return this.nom;
    }
    public int getVie() {
        return this.vie;
    }
    public int getMvie() {
        return this.mvie;
    }
    public int getMana() {
        return this.mana;
    }
    public int getMmana() {
        return this.mmana;
    }
    public int getGold() {
        return this.gold;
    }
    public int getExperience() {
        return this.experience;
    }
    public Sort[] getListSort() {
        return this.listSort;
    }
    public Inventaire getInventaire() {
        return this.inventaire;
    }
    public int getNiveau() {
        return this.niveau;
    }
    public AnimatedSprite getSprite() {
        return this.sprite;
    }

    // ---------------------------- abstract methode ------------------------------
    public abstract void takeDammage(int dmg);
    public abstract String afficher();
    public abstract String playTurn(Entity adversaire);
    public abstract void endTurn();

    // --------------------------------- methode ----------------------------------
    public void addMoney(int g) {
        this.gold = Math.max(this.gold + g, 0);
    }
    public boolean dead() {
        return this.vie <= 0;
    }

    public boolean removeMana(int m) {
        if (mana - m >= 0) {
            this.mana = mana - m;
            return true;
        }
        return false;
    }

    public boolean removeVie(int v) {
        if (vie - v >= 0) {
            this.vie = vie - v;
            return true;
        }
        return false;
    }

    public int heal(int h) {
        if (vie < mvie) {
            int regen = Math.min(h, mvie - vie);
            vie = Math.min(mvie, vie + h);
            return regen;
        }
        return -1;
    }

    public int addMana(int m) {
        if (mana < mmana) {
            int regen = Math.min(m, mmana - mana);
            mana = Math.min(mmana, mana + m);
            return regen;
        }
        return -1;
    }

    public int donGold(int g) {
        gold = Math.max(0, gold - g);
        return g;
    }
}
