package com.company;

import com.company.Menu.EngineerMenu;
import com.company.Menu.PlannerMenu;
import com.company.Person.Engineer;
import com.company.Person.Person;
import com.company.Person.PersonDatabase;
import com.company.Person.Planner;


import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        PersonDatabase PersonDB = new PersonDatabase();
        Person Rick = new Planner("Rick Merkelbach", "Rick123");
        Person Geert = new Engineer("Geert Merkelbach", "Geert123", 1);
        PersonDB.addPerson(Rick);
        PersonDB.addPerson(Geert);

        int attemptsLeft = 3; // gebruiker heeft 3 pogingen om in te loggen

        while (attemptsLeft > 0) {
            System.out.println("Login");
            System.out.println("================");
            System.out.println("UserName: ");
            String UserName = scanner.nextLine();
            System.out.println("Password: ");
            String Password = scanner.nextLine();

            if (PersonDB.getPerson(UserName) != null && PersonDB.getPerson(UserName).getPassword().equals(Password)) {
                if (PersonDB.getPerson(UserName) instanceof Planner) {
                    PlannerMenu plannerMenu = new PlannerMenu();
                    plannerMenu.Menu(PersonDB, PersonDB.getPerson(UserName));
                } else {
                    System.out.println("U bent ingelogd als Engineer");
                    EngineerMenu engineerMenu = new EngineerMenu();
                    engineerMenu.Menu(PersonDB, PersonDB.getPerson(UserName));
                }
            } else {
                attemptsLeft--;
                if (attemptsLeft > 0) {
                    System.out.println("Onjuiste gebruikersnaam of wachtwoord. U heeft nog " + attemptsLeft + " poging(en) over.");
                } else {
                    System.out.println("U heeft geen pogingen meer over het programma wordt afgesloten.");
                }
            }
        }
    }
}
