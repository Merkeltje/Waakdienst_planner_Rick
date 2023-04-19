package com.company.Person;


import com.company.Planning.Dienst;

import java.util.ArrayList;

public class Person {
    private String UserName;
    private String Password;
    private ArrayList<Dienst> diensten = new ArrayList<>();

    public Person(String userName, String password) {
        UserName = userName;
        Password = password;
    }

    public String getFunction() {
        return ";";
    }

    public String getUserName() {
        return UserName;
    }

    public ArrayList<Dienst> getDiensten() {
        return diensten;
    }

    public String getPassword() {
        return Password;
    }

    public String toString() {
        return UserName + " functie: " + getFunction();
    }

    public void voegDiensttoe(Dienst dienst) {
        diensten.add(dienst);
    }

    public void removeDienst(Dienst dienst) {
        diensten.remove(dienst);
    }

    public static void DienstenBekijken(Person person) {

        for (Dienst dienst : person.getDiensten()) {
            System.out.println(dienst.getWeek() + dienst.getDag() + dienst.getTijd());
        }
    }
}
