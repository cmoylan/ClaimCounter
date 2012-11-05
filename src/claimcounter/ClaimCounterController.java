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
import jfxtras.labs.scene.control.gauge.SplitFlap;

/**
 * FXML Controller class
 *
 * @author chrismoylan
 */
public class ClaimCounterController implements Initializable {
    @FXML private Text actiontarget;

    @FXML protected void handleSubmitButtonAction(ActionEvent event) {
        actiontarget.setText("Sign in button pressed");
    }

    @FXML private SplitFlap flip1;
    @FXML private SplitFlap flip2;
    @FXML private SplitFlap flip3;
    @FXML private SplitFlap flip4;
    @FXML private SplitFlap flip5;
    @FXML private SplitFlap flip6;
    @FXML private SplitFlap flip7;


    private Integer SPLIT_FLAP_LENGTH = 7;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //flip1.setText("0");

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

    /**
     * Convenience method so you can pass an integer to setCounter
     *
     * @param val
     */
    public void setCounter(Integer val) {
        setCounter(val.toString());
    }

}
