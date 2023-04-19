package com.company.Person;

import java.util.ArrayList;

public class PersonDatabase {
    ArrayList<Person> Allpersons = new ArrayList<Person>();





    public Person getPerson(String username) {
        for (Person person : Allpersons) {
            if (person.getUserName().equals(username)) {
                return person;
            }
        }
        return null;
    }

    public void removePerson(Person person) {
        Allpersons.remove(person);
    }
    public void addPerson(Person person) {
        Allpersons.add(person);
    }
    public ArrayList<Person> getAllpersons() {
        return Allpersons;
    }
}
