/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package claimcounter;

import java.net.URL;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author chrismoylan
 */
public class ClaimCounter extends Application {
    private static final Random     RND          = new Random();
    //private static final long     TIME_PERIOD  =   5000000000l; // about 5 seconds
    private static final long       TIME_PERIOD  =   60000000000l; // about a minute
    private long                    lastTimeCall = 0;
    private ClaimCounterController  controller;
    private ClaimData               data;
    private ClaimSubscription       subscription;

    private final AnimationTimer TIMER        = new AnimationTimer() {
        @Override
        // TODO: not sure if this is needed anymore
        public void handle(long l) {
            long currentNanoTime = System.nanoTime();
                if (currentNanoTime > lastTimeCall + TIME_PERIOD) {
                    // Any logic that you want to run periodically goes here
                    lastTimeCall = System.nanoTime();
                }
            }
    };

    private void init(Stage primaryStage) throws Exception {

        // Load the custom font so that the css can access it
        Font.loadFont(getClass().getResourceAsStream("/resources/fonts/Marmellata.ttf"), 12);

        // Load the fxml and controller
        URL location = getClass().getResource("ClaimCounter.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        Parent root = (Parent) fxmlLoader.load(location.openStream());

        // Store controller on the instance so that it can be accessed
        controller = fxmlLoader.getController();

        // Instantiate ClaimSubscription with the current ClaimCounter instance.
        // TODO: move .setGuages into controller and just pass controller
        //       instance here.
        subscription = new ClaimSubscription(this);

        // Set some window properties
        primaryStage.setResizable(true);
        primaryStage.setTitle("All your claim are belong to us");

        //Parent root = (Parent) fxmlLoader.load(location.openStream());
        primaryStage.setScene(new Scene(root));
    }

    // TODO: consider moving into the controller, doesn't seem like this belongs
    //       here.
    public void setGauges(ClaimData data) {
        controller.setCounter(data.fetch("claim_count"));
        controller.setRadial1(data.fetch("estimates_per_hour"));
        controller.setRadial2(data.fetch("estimates_today"));
        controller.setRadial3(data.fetch("claims_today"));
    }

    //@Override
    public void play() {
        TIMER.start();
    }

    @Override
    public void stop() {
        TIMER.stop();
        subscription.stop();
        // Stop subscribing here
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
        play();
    }

    public static void main(String[] args) { launch(args); }
}


