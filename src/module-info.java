/**
 * Created by Minology on 03:16 CH
 */
module TowerDefense {
     requires javafx.fxml;
     requires javafx.controls;
     requires javafx.media;
     opens thegame;
     opens thegame.engine;
     exports thegame;
}