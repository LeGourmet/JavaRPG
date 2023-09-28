//DELON ARTHUR
package Frontend;

import Entity.mob.Boss.*;
import Entity.mob.Ennemie;
import Entity.mob.Monstre.*;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DifficultyController extends BaseController {
    @FXML
    private CheckBox level0;
    @FXML
    private CheckBox level1;
    @FXML
    private CheckBox level2;
    @FXML
    private CheckBox level3;
    @FXML
    private CheckBox level4;
    @FXML
    private CheckBox level5;

    public void update() {
        CheckBox[] checkBoxes = {level0, level1, level2, level3, level4, level5};
        for (int i = 0; i < checkBoxes.length; i++) {
            checkBoxes[i].setSelected(joueur.getCheck(i));
        }
    }

    @FXML
    public void playLevel0() {
        moblist = new ArrayList<>();
        moblist.add(new ChevalierS());
        moblist.add(new BarbareS());
        moblist.add(new ArcherS());
        moblist.add(new MageS());
        moblist.add(new RoiS());
        dungeonSize = moblist.size();
        ((FightController) controllers.get("Fight")).start(moblist.remove(0));
        ((FightController) controllers.get("Fight")).setId(0);
        changeScene("Fight");
    }

    @FXML
    public void playLevel1() {
        moblist = new ArrayList<>();
        moblist.add(new AmeD());
        moblist.add(new AmeD());
        moblist.add(new Charon());
        moblist.add(new Kamikaze());
        moblist.add(new Abomination());
        moblist.add(new Hades());
        dungeonSize = moblist.size();
        ((FightController) controllers.get("Fight")).start(moblist.remove(0));
        ((FightController) controllers.get("Fight")).setId(1);
        changeScene("Fight");
    }

    @FXML
    public void playLevel2() {
        moblist = new ArrayList<>();
        List<Integer> tab = Arrays.asList(0, 1, 2, 3);
        Collections.shuffle(tab);
        Ennemie[] ennemies = new Ennemie[]
                {new Mort(tab.get(0)), new Famine(tab.get(1)), new Guerre(tab.get(2)), new Conquete(tab.get(3))};
        moblist.add(ennemies[tab.get(0)]);
        moblist.add(ennemies[tab.get(1)]);
        moblist.add(ennemies[tab.get(2)]);
        moblist.add(ennemies[tab.get(3)]);
        dungeonSize = moblist.size();
        ((FightController) controllers.get("Fight")).start(moblist.remove(0));
        ((FightController) controllers.get("Fight")).setId(2);
        changeScene("Fight");
    }

    @FXML
    public void playLevel3() {
        moblist = new ArrayList<>();
        moblist.add(new ChevalierS());
        moblist.add(new BarbareS());
        moblist.add(new ArcherS());
        moblist.add(new MageS());
        moblist.add(new RoiS());
        for (Ennemie e : moblist) {
            e.adjust(joueur);
        }
        dungeonSize = moblist.size();
        ((FightController) controllers.get("Fight")).start(moblist.remove(0));
        ((FightController) controllers.get("Fight")).setId(3);
        changeScene("Fight");
    }

    @FXML
    public void playLevel4() {
        moblist = new ArrayList<>();
        moblist.add(new AmeD());
        moblist.add(new AmeD());
        moblist.add(new Charon());
        moblist.add(new Kamikaze());
        moblist.add(new Abomination());
        moblist.add(new Hades());
        for (Ennemie e : moblist) {
            e.adjust(joueur);
        }
        dungeonSize = moblist.size();
        ((FightController) controllers.get("Fight")).start(moblist.remove(0));
        ((FightController) controllers.get("Fight")).setId(4);
        changeScene("Fight");
    }

    @FXML
    public void playLevel5() {
        moblist = new ArrayList<>();

        List<Integer> tab = Arrays.asList(0, 1, 2, 3);
        Collections.shuffle(tab);
        Ennemie[] ennemies = new Ennemie[]
                {new Mort(tab.get(0)), new Famine(tab.get(1)), new Guerre(tab.get(2)), new Conquete(tab.get(3))};
        moblist.add(ennemies[tab.get(0)]);
        moblist.add(ennemies[tab.get(1)]);
        moblist.add(ennemies[tab.get(2)]);
        moblist.add(ennemies[tab.get(3)]);
        for (Ennemie e : moblist) {
            e.adjust(joueur);
        }
        dungeonSize = moblist.size();
        ((FightController) controllers.get("Fight")).start(moblist.remove(0));
        ((FightController) controllers.get("Fight")).setId(5);
        changeScene("Fight");
    }

    @FXML
    @Override
    public void handleKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ESCAPE)) {
            previousScene();
        }
    }
}
