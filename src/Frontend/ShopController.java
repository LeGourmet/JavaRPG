//DELON ARTHUR
package Frontend;

import Item.Item;
import Item.armes.Arme;
import Item.popo.Potion;
import javafx.beans.binding.StringBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class ShopController extends BaseController {
    private Arme arme;
    private Potion popo;
    @FXML
    private ImageView potionIcon;
    @FXML
    private ImageView armeIcon;
    @FXML
    private Button armeReroll;
    @FXML
    private Button potionReroll;
    @FXML
    private Button armeBuy;
    @FXML
    private Button potionBuy;
    @FXML
    private ToggleGroup Items;
    @FXML
    private Label itemDescription;
    private boolean init = true;
    @FXML
    private Label potionDesc;
    @FXML
    private Label armeDesc;
    @FXML
    private int rerollCost;
    @FXML
    private Label coins;

    @FXML
    public void initialize() {
        super.initialize();
        StringBinding itemDescriptionText = new StringBinding() {
            {
                super.bind(Items.selectedToggleProperty());
            }

            @Override
            protected String computeValue() {
                if (Items.getSelectedToggle() != null) {
                    Item data = (Item) Items.getSelectedToggle().getUserData();
                    return data.afficher();
                } else {
                    return "";
                }
            }
        };
        itemDescription.textProperty().bind(itemDescriptionText);
    }

    public void update() {
        if (init) {
            init = false;
            handleRerollArme(null);
            handleRerollPotion(null);
        }
        if (Items.getSelectedToggle() != null) {
            Toggle current = Items.getSelectedToggle();
            current.setSelected(false);
        }
        ArrayList<Item> inventaire = joueur.getInventaire().getItems();
        int i = 0;
        for (Toggle slot : Items.getToggles()) {
            if (i < inventaire.size()) {
                ((RadioButton) slot).setDisable(false);
                slot.setUserData(inventaire.get(i));
                ImageView graphic = inventaire.get(i).getSprite().getDisplay();
                graphic.setFitHeight(48);
                graphic.setFitWidth(48);
                ((RadioButton) slot).setGraphic(graphic);
            } else {
                ((RadioButton) slot).setDisable(true);
                ((RadioButton) slot).setGraphic(null);
            }
            i++;
        }
        rerollCost = (int) (Math.max(0.5 * joueur.getNiveau(), 1));
        armeReroll.setText("REROLL " + rerollCost);
        armeBuy.setText("BUY " + arme.getValeurGold());
        armeIcon.setImage(arme.getSprite().getDisplay().getImage());
        armeDesc.setText(arme.afficher());
        potionReroll.setText("REROLL " + rerollCost);
        potionIcon.setImage(popo.getSprite().getDisplay().getImage());
        potionDesc.setText(popo.afficher());
        potionBuy.setText("BUY " + popo.getValeurGold());
        coins.setText("" + joueur.getGold());
    }

    @FXML
    @Override
    public void handleKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ESCAPE)) {
            previousScene();
            for (Toggle slot : Items.getToggles()) {
                ((RadioButton) slot).setGraphic(null);
            }
        }
    }

    @FXML
    public void handleSell(ActionEvent event) {
        if (Items.getSelectedToggle() != null) {
            joueur.addMoney(joueur.getInventaire().sell((Item) Items.getSelectedToggle().getUserData()));
            update();
        }
    }

    @FXML
    public void handleBuyArme(ActionEvent event) {
        if (joueur.getGold() >= arme.getValeurGold()) {
            if (joueur.getInventaire().add(arme)) {
                joueur.donGold(arme.getValeurGold());
                handleRerollArme(null);
                update();
            }
        }
    }

    @FXML
    public void handleBuyPotion(ActionEvent event) {
        if (joueur.getGold() >= popo.getValeurGold()) {
            if (joueur.getInventaire().add(popo)) {
                joueur.donGold(popo.getValeurGold());
                handleRerollPotion(null);
                update();
            }
        }
    }

    @FXML
    public void handleRerollPotion(ActionEvent event) {
        if (event != null) {
            if (joueur.getGold() >= rerollCost) {
                joueur.donGold(rerollCost);
            } else {
                return;
            }
            popo = joueur.rollPotion();
            update();
        } else {
            popo = joueur.rollPotion();
        }

    }

    @FXML
    public void handleRerollArme(ActionEvent event) {
        if (event != null) {
            if (joueur.getGold() >= rerollCost) {
                joueur.donGold(rerollCost);
            } else {
                return;
            }
            arme = joueur.rollArme();
            update();
        } else {
            arme = joueur.rollArme();
        }
    }
}
