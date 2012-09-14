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
    //@FXML private SplitFlap flip2;
    //@FXML private SplitFlap flip3;
    //@FXML private SplitFlap flip4;
    //@FXML private SplitFlap flip5;
    //@FXML private SplitFlap flip6;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        flip1.setText("0");

    }
}
