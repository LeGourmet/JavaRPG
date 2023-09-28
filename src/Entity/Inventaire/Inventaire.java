// Blanchard Allan
package Entity.Inventaire;

import Item.Item;
import java.util.ArrayList;

public class Inventaire {
    // ------------------------------- attribute --------------------------------
    private ArrayList<Item> items;
    private final int maxObj;

    // ------------------------------- constructor -------------------------------
    public Inventaire(int m) {
        this.items = new ArrayList<>();
        this.maxObj = m;
    }

    public Inventaire(int m, ArrayList<Item> i) {
        this.items = i;
        this.maxObj = m;
    }

    // --------------------------------- getter ----------------------------------
    public int getMaxObj() {
        return this.maxObj;
    }
    public ArrayList<Item> getItems() {
        return this.items;
    }
    public Item getItem(int i) {
        return items.get(i);
    }

    // --------------------------------- methode ----------------------------------
    public int sell(int i) {
        if (i >= 0 && i < items.size()) {
            return (items.remove(i)).getValeurGold();
        }
        return 0;
    }

    public int sell(Item it) {
        items.remove(it);
        return it.getValeurGold();
    }

    public boolean add(Item i) {
        if (items.size() < maxObj) {
            items.add(i);
            return true;
        }
        return false;
    }

    public void swapItem(Item AEquiper, Item Equipe) {
        items.remove(AEquiper);
        items.add(Equipe);
    }
}
