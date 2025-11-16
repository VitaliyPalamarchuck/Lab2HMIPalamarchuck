package org.example.lab2;

import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

public class Person {
    private SimpleStringProperty PIP = new SimpleStringProperty("");
    private SimpleStringProperty Phone = new SimpleStringProperty("");

    public Person(String PIP, String phone) {
        this.PIP = new SimpleStringProperty(PIP);
        this.Phone = new SimpleStringProperty(phone);
    }

    public Person() {

    }

    public String getPIP() {
        return PIP.get();
    }

    public SimpleStringProperty PIPProperty() {
        return PIP;
    }

    public String getPhone() {
        return Phone.get();
    }

    public SimpleStringProperty phoneProperty() {
        return Phone;
    }

    public void setPIP(String PIP) {
        this.PIP.set(PIP);
    }

    public void setPhone(String phone) {
        this.Phone.set(phone);
    }

    @Override
    public String toString(){
        return "Person{" +
                "pip=" + PIP +
                ", phone=" + Phone +
                '}';
    }
}
