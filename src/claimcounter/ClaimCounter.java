/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package claimcounter;

import java.net.URL;
import java.util.Calendar;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
//import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import jfxtras.labs.scene.control.gauge.Gauge;
import jfxtras.labs.scene.control.gauge.LcdDesign;
import jfxtras.labs.scene.control.gauge.Radial;
import jfxtras.labs.scene.control.gauge.SplitFlap;
import jfxtras.labs.scene.control.gauge.SplitFlapBuilder;
import jfxtras.labs.scene.control.gauge.StyleModel;
import jfxtras.labs.scene.control.gauge.StyleModelBuilder;

/**
 *
 * @author chrismoylan
 */
public class ClaimCounter extends Application {
    private static final Random  RND          = new Random();
    //private static final long    TIME_PERIOD  = 1000000000l; // about 1 seconds
    //private static final long    TIME_PERIOD  = 10000000000l; // about 10 seconds
    private static final long    TIME_PERIOD  = 5000000000l; // about 5 seconds
    private Radial radial1;
    private long                 lastTimeCall = 0;
    private ClaimCounterController controller;
    private final AnimationTimer TIMER        = new AnimationTimer() {

        @Override
        public void handle(long l) {
            long currentNanoTime = System.nanoTime();
                if (currentNanoTime > lastTimeCall + TIME_PERIOD) {
                    //System.out.print("doing it");
                    //radial1.setValue(RND.nextDouble() * 100);
                    //setClock();
                    controller.setCounter(4567);
                    lastTimeCall = System.nanoTime();
                }
            }
    };

    private void init(Stage primaryStage) throws Exception {
        // Load the fxml and controller
        URL location = getClass().getResource("ClaimCounter.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        Parent root = (Parent) fxmlLoader.load(location.openStream());
        controller = fxmlLoader.getController();


        // Set some window properties
        primaryStage.setResizable(true);
        primaryStage.setTitle("All your claim are belong to us");
        primaryStage.setScene(new Scene(root));


        ClaimData dater = new ClaimData();
        // Create some controls
        //StyleModel STYLE_MODEL_1 = StyleModelBuilder.create()
        //                                            .frameDesign(Gauge.FrameDesign.STEEL)
        //                                            .tickLabelOrientation(Gauge.TicklabelOrientation.HORIZONTAL)
        //                                            .pointerType(Gauge.PointerType.TYPE14)
        //                                            .thresholdVisible(true)
        //                                            .lcdDesign(LcdDesign.STANDARD_GREEN)
        //                                            .build();

        //radial1 = new Radial(STYLE_MODEL_1);
        //radial1.setThreshold(30);
        //radial1.setPrefSize(250, 250);
        //radial1.setValue(30);
    }

    //@Override
    public void play() {
        TIMER.start();
    }

    @Override
    public void stop() {
        TIMER.stop();
    }

    public double getSampleWidth() { return 600; }

    public double getSampleHeight() { return 600; }

    @Override
    public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
        play();
    }

    public static void main(String[] args) { launch(args); }
}


