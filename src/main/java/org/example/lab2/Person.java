package org.example.lab2;

import java.util.ArrayList;

public class Person {
    private String PIP;
    private String Phone;

    public String getPIP() {
        return PIP;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPIP(String PIP) {
        this.PIP = PIP;
    }

    public void setPhone(String phone) {
        this.Phone = phone;
    }

    public Person(String PIP, String phone) {
        this.PIP = PIP;
        Phone = phone;
    }


}
