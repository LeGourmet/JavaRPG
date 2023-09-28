//DELON ARTHUR
package Frontend;

import Entity.Inventaire.Inventaire;
import Entity.personnages.chasseur.Chasseur;
import Entity.personnages.guerrier.Guerrier_Epee;
import Entity.personnages.guerrier.Guerrier_Epee_Bouclier;
import Entity.personnages.mage.Mage;
import Item.Item;
import Item.armes.Arc;
import Item.armes.Arme;
import Item.armes.Bouclier;
import Item.armes.Epee;
import Item.popo.PopoMana;
import Item.popo.PopoVie;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LoadGameController extends BaseController {
    @FXML
    private ToggleGroup Saves;
    @FXML
    private Button play;

    @FXML
    public void initialize() {
        super.initialize();
        BooleanBinding isFormFilled = new BooleanBinding() {
            {
                super.bind(Saves.selectedToggleProperty());
            }

            @Override
            protected boolean computeValue() {
                return Saves.getSelectedToggle() == null;
            }
        };
        play.disableProperty().bind(isFormFilled);
    }

    public void update() {
        File saveDir = new File(Constantes.SAVEPATH);
        if (saveDir.exists()) {
            List<String> saves = Arrays.asList(saveDir.list());
            for (Toggle slot : Saves.getToggles()) {
                if (saves.contains(((RadioButton) slot).getId() + ".sav")) {
                    ((RadioButton) slot).setDisable(false);
                    String path = Constantes.SAVEPATH + "/" + ((RadioButton) slot).getId() + ".sav";
                    slot.setUserData(((RadioButton) slot).getId() + ".sav");
                    Scanner scanner = null;
                    try {
                        scanner = new Scanner(new File(path));
                        ((RadioButton) slot).setText(scanner.nextLine());
                        scanner.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    ((RadioButton) slot).setText(((Node) slot).getId());
                    ((RadioButton) slot).setDisable(true);
                }
            }
        }else{
            for (Toggle slot : Saves.getToggles()) {
                ((RadioButton) slot).setText(((Node) slot).getId());
                ((RadioButton) slot).setDisable(true);
            }
        }
    }

    @FXML
    @Override
    public void handleKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ESCAPE)) {
            previousScene();
        }
    }

    @FXML
    public void handlePlay(ActionEvent event) {
        update();
        if (Saves.getSelectedToggle() != null) {
            String path = Constantes.SAVEPATH + "/" + Saves.getSelectedToggle().getUserData();
            Scanner scanner = null;
            try {
                scanner = new Scanner(new File(path));
                Arme[] armes = null;
                Inventaire inventaire = null;
                scanner.nextLine();
                String classe = scanner.nextLine();
                String nom = scanner.nextLine();
                int vie = Integer.parseInt(scanner.nextLine());
                int maxVie = Integer.parseInt(scanner.nextLine());
                int mana = Integer.parseInt(scanner.nextLine());
                int maxMana = Integer.parseInt(scanner.nextLine());
                int rmana = Integer.parseInt(scanner.nextLine());
                int niveau = Integer.parseInt(scanner.nextLine());
                int gold = Integer.parseInt(scanner.nextLine());
                if (!classe.equals("Mage")) {
                    armes = new Arme[Integer.parseInt(scanner.nextLine().split(";")[1])];
                    for (int i = 0; i < armes.length; i++) {
                        String item = scanner.nextLine();
                        String[] object = item.split(";");
                        switch (object[0]) { // type
                            case "Arc":
                                armes[i] = new Arc(Integer.parseInt(object[1]), // dammage
                                        Integer.parseInt(object[2]), // nbFleche
                                        Integer.parseInt(object[3]), // value
                                        object[4], // nom
                                        Integer.parseInt(object[5]), // levelMin
                                        Boolean.parseBoolean(object[6])); //infini
                                break;
                            case "Bouclier":
                                armes[i] = new Bouclier(Integer.parseInt(object[1]), // armure
                                        Integer.parseInt(object[2]), // value
                                        object[3], // nom
                                        Integer.parseInt(object[4])); // levelMin
                                break;
                            case "Epee":
                                armes[i] = new Epee(Integer.parseInt(object[1]), // dammage
                                        Integer.parseInt(object[2]), // value
                                        object[3], // nom
                                        Integer.parseInt(object[4])); // levelMin
                                break;
                        }
                    }
                } else {
                    scanner.nextLine();
                }
                String[] infoinv = scanner.nextLine().split(";");
                if (!infoinv[2].equals("0")) {
                    Item[] items = new Item[Integer.parseInt(infoinv[2])];
                    for (int i = 0; i < items.length; i++) {
                        String item = scanner.nextLine();
                        String[] object = item.split(";");
                        switch (object[0]) { // type
                            case "Arc":
                                items[i] = new Arc(Integer.parseInt(object[1]), // dammage
                                        Integer.parseInt(object[2]), // nbFleche
                                        Integer.parseInt(object[3]), // value
                                        object[4], // nom
                                        Integer.parseInt(object[5]), // levelMin
                                        Boolean.parseBoolean(object[6])); //infini
                                break;
                            case "Bouclier":
                                items[i] = new Bouclier(Integer.parseInt(object[1]), // armure
                                        Integer.parseInt(object[2]), // value
                                        object[3], // nom
                                        Integer.parseInt(object[4])); // levelMin
                                break;
                            case "Epee":
                                items[i] = new Epee(Integer.parseInt(object[1]), // dammage
                                        Integer.parseInt(object[2]), // value
                                        object[3], // nom
                                        Integer.parseInt(object[4])); // levelMin
                                break;
                            case "PopoMana":
                                items[i] = new PopoMana(Float.parseFloat(object[1]), // power
                                        Integer.parseInt(object[2])); // niveau
                                break;
                            case "PopoVie":
                                items[i] = new PopoVie(Float.parseFloat(object[1]), // power
                                        Integer.parseInt(object[2])); // niveau
                                break;
                        }
                    }
                    inventaire = new Inventaire(Integer.parseInt(infoinv[1]), new ArrayList<>(Arrays.asList(items)));
                } else {
                    inventaire = new Inventaire(Integer.parseInt(infoinv[1]));
                }
                int exp = Integer.parseInt(scanner.nextLine());
                boolean[] listChecks = new boolean[Integer.parseInt(scanner.nextLine().split(";")[1])];
                for (int i = 0; i < listChecks.length; i++) {
                    listChecks[i] = Boolean.parseBoolean(scanner.nextLine());
                }
                boolean count = false;
                if (classe.equals("Mage")) {
                    count = Boolean.parseBoolean(scanner.nextLine());
                }
                switch (classe) {
                    case "Guerrier_Epee":
                        joueur = new Guerrier_Epee(nom, vie, maxVie, mana, maxMana, rmana, niveau, gold, armes, inventaire, exp, listChecks);
                        break;
                    case "Guerrier_Epee_Bouclier":
                        joueur = new Guerrier_Epee_Bouclier(nom, vie, maxVie, mana, maxMana, rmana, niveau, gold, armes, inventaire, exp, listChecks);
                        break;
                    case "Chasseur":
                        joueur = new Chasseur(nom, vie, maxVie, mana, maxMana, rmana, niveau, gold, armes, inventaire, exp, listChecks);
                        break;
                    case "Mage":
                        joueur = new Mage(nom, vie, maxVie, mana, maxMana, rmana, niveau, gold, inventaire, exp, listChecks, count);
                        break;
                }
                scanner.close();
                changeScene("Lobby");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void handleFDelete(ActionEvent event) {
        ((RadioButton) ((Button) event.getSource()).getParent()).setSelected(false);
        File file = new File(Constantes.SAVEPATH + "/" + ((Node) event.getSource()).getId() + ".sav");
        if (file.exists()) {
            file.delete();
            update();
        }
    }
}
