//DELON ARTHUR
package Frontend;

import Entity.mob.Ennemie;
import Entity.sort.Sort;
import Entity.sort.personnage.SortPerso;
import Item.Item;
import Item.armes.Arme;
import Item.popo.Potion;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.StringBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;


public class FightController extends BaseController {
    private int id;
    private boolean init = false;
    private Ennemie adversaire;
    @FXML
    private Label playerInfo;
    @FXML
    private HBox buttons;
    @FXML
    private Label monsterInfo;
    @FXML
    private Label playerName;
    @FXML
    private Label playerLevel;
    @FXML
    private Label monsterLevel;
    @FXML
    private ProgressBar playerHealthBar;
    @FXML
    private Label playerHealth;
    @FXML
    private ProgressBar playerManaBar;
    @FXML
    private Label playerMana;
    @FXML
    private Label monsterName;
    @FXML
    private ProgressBar monsterHealthBar;
    @FXML
    private Label monsterHealth;
    @FXML
    private ProgressBar monsterManaBar;
    @FXML
    private Label monsterMana;
    @FXML
    private AnchorPane playerSpriteBound;
    @FXML
    private AnchorPane monsterSpriteBound;
    @FXML
    private Button Attack1;
    @FXML
    private Label attack1Name;
    @FXML
    private ImageView attack1Icon;
    @FXML
    private Label attack1Cost;
    @FXML
    private Label attack1Damage;
    @FXML
    private Button Attack2;
    @FXML
    private Label attack2Name;
    @FXML
    private ImageView attack2Icon;
    @FXML
    private Label attack2Cost;
    @FXML
    private Label attack2Damage;
    @FXML
    private AnchorPane pause;
    @FXML
    private AnchorPane inventory;
    @FXML
    private AnchorPane pausebody;
    @FXML
    private ToggleGroup Items;
    @FXML
    private Button use;
    @FXML
    private Label itemDescription;

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
        BooleanBinding canUse = new BooleanBinding() {
            {
                super.bind(Items.selectedToggleProperty());
            }

            @Override
            protected boolean computeValue() {
                if (Items.getSelectedToggle() != null) {
                    Item data = (Item) Items.getSelectedToggle().getUserData();
                    return data instanceof Arme;
                }
                return true;
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
        use.disableProperty().bind(canUse);
        use.textProperty().bind(useText);
        itemDescription.textProperty().bind(itemDescriptionText);
    }

    public void skip() {
        adversaire.takeDammage(100000000);
        update();
    }

    public void setId(int id) {
        this.id = id;
    }


    public void start(Ennemie e) {
        BooleanBinding hasEnoughMana = new BooleanBinding() {
            {
                super.bind(playerManaBar.progressProperty());
            }

            @Override
            protected boolean computeValue() {
                return joueur.getMana() < joueur.getListSort()[1].getCout();
            }
        };
        buttons.setDisable(false);
        init = false;
        playerSpriteBound.getChildren().clear();
        monsterSpriteBound.getChildren().clear();
        Attack2.disableProperty().bind(hasEnoughMana);
        adversaire = e;
        playerSpriteBound.getChildren().add(joueur.getSprite().getDisplay());
        playerSpriteBound.setBottomAnchor(joueur.getSprite().getDisplay(), 0.0);
        joueur.getSprite().start();
        monsterSpriteBound.getChildren().add(adversaire.getSprite().getDisplay());
        monsterSpriteBound.setBottomAnchor(adversaire.getSprite().getDisplay(), 0.0);
        adversaire.getSprite().start();

        Attack1.setPadding(Insets.EMPTY);
        Attack2.setPadding(Insets.EMPTY);

        if (adversaire.getSpeed() >= Math.random()) {
            update();
        }
    }

    public void update() {//Do updaty things
        if (!init) {
            playerInfo.setText(joueur.afficher());
            monsterInfo.setText(adversaire.afficher());
            playerName.setText(joueur.getNom());
            playerLevel.setText(Integer.toString(joueur.getNiveau()));
            monsterLevel.setText(Integer.toString(adversaire.getNiveau()));
            playerHealth.setText(joueur.getVie() + "/" + joueur.getMvie());
            playerHealthBar.setProgress((float) joueur.getVie() / (float) joueur.getMvie());
            playerMana.setText(joueur.getMana() + "/" + joueur.getMmana());
            playerManaBar.setProgress((float) joueur.getMana() / (float) joueur.getMmana());
            monsterName.setText(adversaire.getNom());
            monsterHealth.setText(adversaire.getVie() + "/" + adversaire.getMvie());
            monsterHealthBar.setProgress((float) adversaire.getVie() / (float) adversaire.getMvie());
            monsterMana.setText(adversaire.getMana() + "/" + adversaire.getMmana());
            monsterManaBar.setProgress((float) adversaire.getMana() / (float) adversaire.getMmana());
            Sort[] sorts = joueur.getListSort();
            attack1Name.setText(sorts[0].getNom());
            attack1Cost.setText(((SortPerso) sorts[0]).calcCout(joueur));
            attack1Damage.setText(((SortPerso) sorts[0]).calcDammage(joueur));
            attack1Icon.setImage(((SortPerso) sorts[0]).getSprite().getDisplay().getImage());
            attack2Name.setText(sorts[1].getNom());
            attack2Cost.setText(((SortPerso) sorts[1]).calcCout(joueur));
            attack2Damage.setText(((SortPerso) sorts[1]).calcDammage(joueur));
            attack2Icon.setImage(((SortPerso) sorts[1]).getSprite().getDisplay().getImage());
            playerLevel.setText(Integer.toString(joueur.getNiveau()));
            init = true;
        } else {
            Sort[] sorts = joueur.getListSort();
            attack1Name.setText(sorts[0].getNom());
            attack1Cost.setText(((SortPerso) sorts[0]).calcCout(joueur));
            attack1Damage.setText(((SortPerso) sorts[0]).calcDammage(joueur));
            attack1Icon.setImage(((SortPerso) sorts[0]).getSprite().getDisplay().getImage());
            attack2Name.setText(sorts[1].getNom());
            attack2Cost.setText(((SortPerso) sorts[1]).calcCout(joueur));
            attack2Damage.setText(((SortPerso) sorts[1]).calcDammage(joueur));
            attack2Icon.setImage(((SortPerso) sorts[1]).getSprite().getDisplay().getImage());
            playerLevel.setText(Integer.toString(joueur.getNiveau()));
            //player attack animation
            playerInfo.setText(joueur.afficher());
            playerManaBar.setProgress((float) joueur.getMana() / (float) joueur.getMmana());
            playerMana.setText(joueur.getMana() + "/" + joueur.getMmana());
            //monster damage animation
            monsterHealthBar.setProgress((float) adversaire.getVie() / (float) adversaire.getMvie());
            monsterHealth.setText(adversaire.getVie() + "/" + adversaire.getMvie());
            if (adversaire.dead()) {

                int money = adversaire.getGold();
                int exp = adversaire.getExperience();
                boolean hasLooted = adversaire.lootObj(joueur);
                joueur.addMoney(money);
                int level = joueur.addExp(exp);
                if (level > 0) {
                    ((ShopController) controllers.get("Shop")).handleRerollPotion(null);
                    ((ShopController) controllers.get("Shop")).handleRerollArme(null);
                }
                String text = "You Win:\n+" + exp + " xp\n+" + money + "gold\n" + (hasLooted ? "vous avez gagné un objet\n" : "") + (level > 1 ? "Vous êtes monté de " + level + " niveaux\n" : level == 1 ? "Vous êtes monté d'un niveau\n" : "");
                if (moblist.isEmpty()) {
                    init = false;
                    ((NextStageController) controllers.get("NextStage")).setup(text, "YouWin", id);
                    changeScene("NextStage");
                } else {
                    init = false;
                    text += "Stage suivant: " + (dungeonSize - moblist.size()) + "/" + dungeonSize;
                    ((NextStageController) controllers.get("NextStage")).setup(text, "NextStage", id);
                    changeScene("NextStage");
                }
            } else {
                //monster attack animation
                monsterInfo.setText(adversaire.afficher());
                monsterManaBar.setProgress((float) adversaire.getMana() / (float) adversaire.getMmana());
                monsterMana.setText(adversaire.getMana() + "/" + adversaire.getMmana());
                //player damage animation
                if (joueur.dead()) {
                    joueur.getSprite().setAnimation("Death");
                    Waiter wait = new Waiter(joueur.getSprite());
                    wait.whenDone(event -> {
                        init = false;
                        ((NextStageController) controllers.get("NextStage")).setup("Game over", "GameOver", id);
                        changeScene("NextStage");
                    });
                    wait.start();
                } else {
                    playerHealthBar.setProgress((float) joueur.getVie() / (float) joueur.getMvie());
                    playerHealth.setText(joueur.getVie() + "/" + joueur.getMvie());
                    buttons.setDisable(false);
                }
            }
            if (gamePaused) {
                pause();
                buttons.setDisable(false);
            }
        }
    }

    private void finishTurn() {
        joueur.endTurn();
        Sleeper sleep = new Sleeper(500);
        sleep.whenDone(event -> {
            adversaire.playTurn(joueur);
            adversaire.endTurn();
            update();
        });
        sleep.start();
    }


    @FXML
    public void handleSpell0(ActionEvent e) {
        buttons.setDisable(true);
        if (joueur.castSpell(0, adversaire)) {
            joueur.getSprite().setAnimation("Attack0");
            Waiter wait = new Waiter(joueur.getSprite());
            wait.whenDone(event -> {
                finishTurn();
            });
            wait.start();
        }
    }

    @FXML
    public void handleSpell1(ActionEvent e) {
        buttons.setDisable(true);
        if (joueur.castSpell(1, adversaire)) {
            joueur.getSprite().setAnimation("Attack1");
            Waiter wait = new Waiter(joueur.getSprite());
            wait.whenDone(event -> {
                finishTurn();
            });
            wait.start();
        }
    }

    @FXML
    public void pass(ActionEvent e) {
        buttons.setDisable(true);
        finishTurn();
    }

    private void pauseupdate() {
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
            pause();
        }
    }

    @FXML
    public void handleUse(ActionEvent event) {
        if (Items.getSelectedToggle() != null) {
            if (Items.getSelectedToggle().getUserData() instanceof Potion) {
                if (joueur.boire((Potion) Items.getSelectedToggle().getUserData())) {
                    pauseupdate();
                    update();
                }
            }
        }
    }

    @FXML
    public void handleResume() {
        pause();
    }

    @FXML
    public void handleRestart() {
        moblist.clear();
        pause();
        changeScene("NewGame");
    }

    @FXML
    public void handleMain() {
        moblist.clear();
        pause();
        changeScene("MainMenu");
    }

    @FXML
    public void handleExit() {
        exit();
    }

    @FXML
    public void handleInv() {
        pauseupdate();
        pause.setVisible(false);
        inventory.setVisible(true);
    }

    private void pause() {
        if (pause.isVisible()) {
            pause.setVisible(false);
            inventory.setVisible(false);
            pausebody.setVisible(false);
            gamePaused = false;
        } else {
            pausebody.setVisible(true);
            pause.setVisible(true);
            inventory.setVisible(false);
            gamePaused = true;
            for (Toggle slot : Items.getToggles()) {
                ((RadioButton) slot).setGraphic(null);
            }
        }
        pauseupdate();
    }
}
