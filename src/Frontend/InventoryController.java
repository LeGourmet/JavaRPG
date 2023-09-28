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
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class InventoryController extends BaseController {
    @FXML
    private ToggleGroup Items;
    @FXML
    private Button use;
    @FXML
    private Label itemDescription;
    @FXML
    private Label playerInfo;
    @FXML
    private Label primary;
    @FXML
    private Label secondary;
    @FXML
    private AnchorPane playerSprite;
    private boolean init = true;

    @FXML
    public void initialize() {
        super.initialize();
        StringBinding useText = new StringBinding() {
            {
                super.bind(Items.selectedToggleProperty());
            }

            @Override
            protected String computeValue() {
                if (Items.getSelectedToggle() != null) {
                    Item data = (Item) Items.getSelectedToggle().getUserData();
                    return data instanceof Potion ? "Boire" : "Equiper";
                } else {
                    return "Equiper";
                }
            }
        };
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
        use.textProperty().bind(useText);
        itemDescription.textProperty().bind(itemDescriptionText);
    }

    public void update() {
        if (init) {
            playerSprite.getChildren().clear();
            playerSprite.getChildren().add(joueur.getSprite().getDisplay());
            playerSprite.setBottomAnchor(joueur.getSprite().getDisplay(), 0.0);
            joueur.getSprite().getDisplay().setFitHeight(192);
            joueur.getSprite().getDisplay().setFitWidth(192);
            joueur.getSprite().start();
            init = false;
        }
        primary.setVisible(false);
        secondary.setVisible(false);
        Arme[] armes = joueur.getListArmes();
        if (armes.length > 0) {
            if (armes.length == 2) {
                secondary.setVisible(true);
                secondary.setText(armes[1].afficher());
            }
            primary.setVisible(true);
            primary.setText(armes[0].afficher());
        }
        playerInfo.setText(joueur.infos());
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
    }

    @FXML
    @Override
    public void handleKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ESCAPE)) {
            previousScene();
            for (Toggle slot : Items.getToggles()) {
                ((RadioButton) slot).setGraphic(null);
            }
            joueur.getSprite().getDisplay().setFitHeight(0);
            joueur.getSprite().getDisplay().setFitWidth(0);
            init = true;
        }
    }

    @FXML
    public void handleUse(ActionEvent event) {
        if (Items.getSelectedToggle() != null) {
            if (Items.getSelectedToggle().getUserData() instanceof Potion) {
                if (joueur.boire((Potion) Items.getSelectedToggle().getUserData())) {
                    update();
                }
            } else {
                if (joueur.equiper((Arme) Items.getSelectedToggle().getUserData())) {
                    update();
                }
            }
        }
    }
}
