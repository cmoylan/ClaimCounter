/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package claimcounter;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import jfxtras.labs.scene.control.gauge.ColorDef;
import jfxtras.labs.scene.control.gauge.Gauge;
import jfxtras.labs.scene.control.gauge.LcdDesign;
import jfxtras.labs.scene.control.gauge.LedColor;
import jfxtras.labs.scene.control.gauge.SplitFlap;
import jfxtras.labs.scene.control.gauge.Radial;
import jfxtras.labs.scene.control.gauge.RadialHalfN;
import jfxtras.labs.scene.control.gauge.StyleModel;
import jfxtras.labs.scene.control.gauge.StyleModelBuilder;


/**
 * FXML Controller class
 *
 * @author chrismoylan
 */
public class ClaimCounterController implements Initializable {
    @FXML private Text actiontarget;

    //@FXML protected void handleSubmitButtonAction(ActionEvent event) {
    //    actiontarget.setText("Sign in button pressed");
    //}

    @FXML private SplitFlap flip1;
    @FXML private SplitFlap flip2;
    @FXML private SplitFlap flip3;
    @FXML private SplitFlap flip4;
    @FXML private SplitFlap flip5;
    @FXML private SplitFlap flip6;
    @FXML private SplitFlap flip7;

    @FXML private Radial radial1;
    @FXML private RadialHalfN radial2;
    @FXML private Radial radial3;


    private Integer SPLIT_FLAP_LENGTH = 7;

    private ClaimNotifier notifier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        StyleModel STYLE_MODEL_1 = StyleModelBuilder.create()
                                                    .frameDesign(Gauge.FrameDesign.ANTHRACITE)
                                                    .backgroundDesign(Gauge.BackgroundDesign.WHITE)
                                                    .tickLabelOrientation(Gauge.TicklabelOrientation.HORIZONTAL)
                                                    .pointerType(Gauge.PointerType.TYPE14)
                                                    .thresholdVisible(true)
                                                    .lcdDesign(LcdDesign.STANDARD_GREEN)
                                                    .build();

        StyleModel STYLE_MODEL_2 = StyleModelBuilder.create()
                                                    .frameDesign(Gauge.FrameDesign.ANTHRACITE)
                                                    .backgroundDesign(Gauge.BackgroundDesign.BLACK)
                                                    .tickLabelOrientation(Gauge.TicklabelOrientation.TANGENT)
                                                    .bargraph(true)
                                                    .thresholdColor(Gauge.ThresholdColor.RED)
                                                    .thresholdVisible(true)
                                                    .valueColor(ColorDef.BLUE)
                                                    .ledColor(LedColor.CYAN)
                                                    .build();

        radial1.setStyleModel(STYLE_MODEL_1);
        radial1.setThreshold(15);
        radial1.setPrefSize(350, 350);

        radial2.setStyleModel(STYLE_MODEL_2);
        radial2.setThreshold(70);
        radial2.setTickLabelOrientation(Gauge.TicklabelOrientation.NORMAL);
        radial2.setPrefSize(350, 350);

        radial3.setStyleModel(STYLE_MODEL_1);
        radial3.setThreshold(10);
        radial3.setPrefSize(350, 350);

        // TODO: should be doing this in css or make it fluid
        flip1.setPrefSize(200, 344);
        flip2.setPrefSize(200, 344);
        flip3.setPrefSize(200, 344);
        flip4.setPrefSize(200, 344);
        flip5.setPrefSize(200, 344);
        flip6.setPrefSize(200, 344);
        flip7.setPrefSize(200, 344);


    }

    /**
     * Set a value for the counter
     *
     * @param val
     */
    public void setCounter(String val) {
        // TODO: do something if length greater than zero
        // TODO: use refletion and this code:
        //
        //for (int i = 0; i < val.length(); i++){
        //    char c = val.charAt(i);
        //    //Process char
        //}

        // Fill the front of the string with spaces so that it's the correct
        // length. Then set each split flap to the correct value from the
        // string.
        Integer fillLength = SPLIT_FLAP_LENGTH - val.length();
        StringBuilder outputBuffer = new StringBuilder(SPLIT_FLAP_LENGTH);
        for (int i = 0; i < fillLength; i++){
            outputBuffer.append(" ");
        }
        outputBuffer.append(val);

        flip1.setText(outputBuffer.substring(0,1));
        flip2.setText(outputBuffer.substring(1,2));
        flip3.setText(outputBuffer.substring(2,3));
        flip4.setText(outputBuffer.substring(3,4));
        flip5.setText(outputBuffer.substring(4,5));
        flip6.setText(outputBuffer.substring(5,6));
        flip7.setText(outputBuffer.substring(6,7));

    }

    public void setNotifier(ClaimNotifier claimNotifier) {
        notifier = claimNotifier;
    }

    /**
     * Convenience method so you can pass an integer to setCounter.
     *
     * Who doesn't love method overloading?
     *
     * @param val
     */
    public void setCounter(Integer val) {
        setCounter(val.toString());
    }

    // TODO: --- this kind of sucks, fix it ---
    public void setRadial1(Integer val) {
        if (val > radial1.getValue()) {
            // claim horn
            notifier.play("boathorn.wav");
        }

        radial1.setValue(val);
    }

    public void setRadial2(Integer val) {
        radial2.setValue(val);
    }

    public void setRadial3(Integer val) {
        radial3.setValue(val);
    }
    // --- END TODO ---

}
