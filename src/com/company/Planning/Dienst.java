package com.company.Planning;

import com.company.Person.Person;

public class Dienst {
    private int week;
    private String dag;
    private String tijd;
    private Person person;
    private String beschrijving;

    public Dienst(int week, String dag, String tijd) {
        this.week = week;
        this.dag = dag;
        this.tijd = tijd;
    }

    public void voegPersoonToe(Person persoon) {
        person = persoon;
    }

    public void verwijderPersoon() {
        person = null;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public int getWeek() {
        return week;
    }

    public String getDag() {
        return dag;
    }

    public String getTijd() {
        return tijd;
    }

    public Person getPerson() {
        return person;
    }

    public String getBeschrijving() {
        return beschrijving;
    }
}
