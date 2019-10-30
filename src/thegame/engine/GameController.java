package thegame.engine;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameController {
    @FXML
    private Label currentLives;

    @FXML
    private Label currentResources;

    @FXML
    private Label currentWave;

    @FXML
    private Label waveDelay;

    @FXML
    private Label waveDelayInSecond;

    public void updateLabels(String currentWave, String currentLives, String currentResources, boolean isDelayed, String delay){
        this.currentLives.setText(currentLives);
        this.currentResources.setText(currentResources);
        this.currentWave.setText(currentWave);
        if (isDelayed) this.waveDelayInSecond.setText(delay);
        this.waveDelay.setVisible(isDelayed);
        this.waveDelayInSecond.setVisible(isDelayed);
    }
}
