package com.company.Menu;

import com.company.Person.Person;
import com.company.Person.PersonDatabase;
import com.company.Planning.DienstManager;

import java.util.Scanner;


public class EngineerMenu implements Menu {
    Scanner scanner = new Scanner(System.in);

    @Override
    public void Menu(PersonDatabase PersonDB, Person person) {
        boolean done = false;
        while (done) {
            System.out.println("        Engineer Menu       ");
            System.out.println("============================");
            System.out.println("1: Planning inzien");
            System.out.println("2: Mijn Diensten bekijken");
            System.out.println("3: Log uit");
            System.out.println("============================");
            System.out.println("Maak uw Keuze: ");

            int keuze = scanner.nextInt();
            switch (keuze) {
                case 1 -> {
                    System.out.println("Welke week wilt u uitprinten");
                    int weeknummer = scanner.nextInt();
                    DienstManager.printDienstenPerWeek(weeknummer);
                }
                case 2 -> {
                    Person.DienstenBekijken(person);
                }
                case 3 -> done = true;
            }
        }


    }
}
