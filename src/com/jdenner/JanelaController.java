package com.jdenner;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class JanelaController implements Initializable {

    private final int TIME_MS = 500;
    private int currentFrame = 0;

    @FXML
    private Pane root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> setInitialPosition());
        Platform.runLater(() -> addEventFilter());
    }

    private void setInitialPosition() {
        root.getChildren().stream().map((node) -> (Pane) node).forEach((pane) -> {
            pane.setTranslateX(pane.getWidth() * -1);
        });
        Pane pane = (Pane) root.getChildren().get(0);
        pane.setTranslateX(0);
    }

    private void addEventFilter() {

        root.getScene().addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.RIGHT) {
                    proximo();
                } else if (event.getCode() == KeyCode.LEFT) {
                    anterior();
                }
            }
        });
    }

    private void anterior() {
        if (currentFrame == 0) {
            return;
        }

        Pane from = (Pane) root.getChildren().get(currentFrame);
        Pane to = (Pane) root.getChildren().get(--currentFrame);

        to.setTranslateX(to.getWidth() * -1);

        Timeline timeline = new Timeline();
        KeyValue kvf = new KeyValue(from.translateXProperty(), from.getWidth(), Interpolator.EASE_BOTH);
        KeyValue kvt = new KeyValue(to.translateXProperty(), 0, Interpolator.EASE_BOTH);
        KeyFrame kff = new KeyFrame(Duration.millis(TIME_MS), kvf);
        KeyFrame kft = new KeyFrame(Duration.millis(TIME_MS), kvt);
        timeline.getKeyFrames().add(kft);
        timeline.getKeyFrames().add(kff);
        timeline.play();
    }

    private void proximo() {

        if (currentFrame == root.getChildren().size() - 1) {
            return;
        }

        Pane from = (Pane) root.getChildren().get(currentFrame);
        Pane to = (Pane) root.getChildren().get(++currentFrame);

        to.setTranslateX(to.getWidth());

        Timeline timeline = new Timeline();
        KeyValue kvf = new KeyValue(from.translateXProperty(), from.getWidth() * -1, Interpolator.EASE_BOTH);
        KeyValue kvt = new KeyValue(to.translateXProperty(), 0, Interpolator.EASE_BOTH);
        KeyFrame kff = new KeyFrame(Duration.millis(TIME_MS), kvf);
        KeyFrame kft = new KeyFrame(Duration.millis(TIME_MS), kvt);
        timeline.getKeyFrames().add(kff);
        timeline.getKeyFrames().add(kft);
        timeline.play();
    }

}
