package org.example.lab2;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class CollectionAddressBook implements AddressBook {

    @Override
    public void add(Person person){

    }
    @Override
    public void update(Person person){

    }
    @Override
    public void delete (Person person){

    }

    public ObservableList<Person> getPersonList(){
        return personList;
    }

    private ObservableList<Person> personList = FXCollections.observableArrayList();

    public void fillTestData(){
        getPersonList().add(new Person("Vitaliy","12341241212"));
        getPersonList().add(new Person("Yulia","523523222"));
        getPersonList().add(new Person("Stepan","86445455454"));
    }
}
