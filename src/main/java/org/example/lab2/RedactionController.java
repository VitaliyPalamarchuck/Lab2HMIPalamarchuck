package org.example.lab2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class RedactionController {
    @FXML
    private Button closeBtn;
    @FXML
    private Button saveBtn;
    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtPip;

    private Person person;

    public Person getPerson(){
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
        txtPip.setText(person.getPIP());
        txtPhone.setText(person.getPhone());

    }

    @FXML
    void actionSave(ActionEvent event){
        person.setPIP(txtPip.getText());
        person.setPhone(txtPhone.getText());
        actionClose(event);
    }
    @FXML
    void actionClose(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }

}
