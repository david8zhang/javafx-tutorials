package controllersample.controllers;

import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;

public class LoginController {

    public TextField usernameField = null;

    public void onInputChange(InputMethodEvent inputMethodEvent) {
        System.out.println(inputMethodEvent.getCommitted());
    }
}
