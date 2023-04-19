package com.company.Menu;

import com.company.Person.Engineer;
import com.company.Person.Person;
import com.company.Person.PersonDatabase;
import com.company.Planning.Dienst;
import com.company.Planning.DienstManager;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class PlannerMenu implements Menu {
    public static Scanner scanner = new Scanner(System.in);


    @Override
    public void Menu(PersonDatabase PersonDB, Person person) {

        boolean Done = false;

        while (!Done) {
            System.out.println("        Planners Menu       ");
            System.out.println("============================");
            System.out.println("1: Planning maken/aanpassen");
            System.out.println("2: Planning inzien");
            System.out.println("3: Planning aanpassen");
            System.out.println("4: Medewerkers Beheren");
            System.out.println("5: Log uit");
            System.out.println("============================");
            System.out.println("Maak uw Keuze: ");

            int Keuze = scanner.nextInt();
            switch (Keuze) {
                case 1 -> {
                    System.out.println("Voor welke week wilt u een planning maken? ");
                    int Planning = scanner.nextInt();
                    DienstManager.Maakweekdiensten(Planning);
                    DienstManager.printDienstenPerWeek(Planning);
                    DienstManager.VoegPersoonToeAanDienst(Planning, PersonDB);

                }
                case 2 -> {
                    System.out.println("Welke week wil je uitprinten? ");
                    int weeknummer = scanner.nextInt();
                    DienstManager.printDienstenPerWeek(weeknummer);
                }
                case 3 -> {
                    System.out.println("Welke planning wil je aanpassen? ");
                    int weeknummer = scanner.nextInt();
                    DienstManager.printDienstenPerWeek(weeknummer);
                    DienstManager.PasPlanningAan(weeknummer, PersonDB);
                }
                case 4 -> MedewerkersBeheren(PersonDB);
                case 5 -> Done = true;
            }
        }
    }


    public static void MedewerkersBeheren(PersonDatabase PersonDB) {
        System.out.println("1: Medewerker Toevoegen");
        System.out.println("2: Medewerker verwijderen");
        System.out.println("3: Lijst van medewerkers");
        System.out.println("============================");
        System.out.println("Maak uw Keuze: ");

        int Keuze = scanner.nextInt();
        switch (Keuze) {
            case 1 -> {
                MedewerkerToevoegen(PersonDB);
            }

            case 2 -> {
                int counter = 1;
                for (Person person : PersonDB.getAllpersons()) {
                    System.out.println(counter + ": " + person.toString());
                    counter++;
                }
                System.out.println("Welke medewerker wilt u verwijderen: ");
                System.out.println(("Type medewerker nummer of 0 om terug te gaan."));
                int keuze2 = scanner.nextInt();
                if (keuze2 == 0) {
                    System.out.println("U gaat terug naar het Planner Menu.");
                } else {
                    Person person = PersonDB.getAllpersons().get(keuze2 - 1);
                    PersonDB.removePerson(person);
                    System.out.println("U heeft " + person.getUserName() + " verwijderd!");
                }
            }

            case 3 -> {
                int counter = 1;
                for (Person person : PersonDB.getAllpersons()) {
                    System.out.println(counter + ": " + person.toString());
                    counter++;
                }
            }
        }
    }

    public static void MedewerkerToevoegen(PersonDatabase PersonDB) {
        scanner.nextLine();
        System.out.println("UserName: ");
        String UserName = scanner.nextLine();
        System.out.println();
        System.out.println("Password: ");
        String Password = scanner.nextLine();
        System.out.println();

        Person person = new Engineer(UserName, Password, 1);
        PersonDB.addPerson(person);
        System.out.println(person + ". Is toegevoegd!");
    }
}
