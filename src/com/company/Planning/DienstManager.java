package com.company.Planning;

import com.company.Person.Person;
import com.company.Person.PersonDatabase;

import java.util.*;
import java.util.stream.Collectors;

public class DienstManager {
    private static ArrayList<Dienst> weekDiensten = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void Maakweekdiensten(int weekNummer) {

        // maandag
        weekDiensten.add(new Dienst(weekNummer, "1 maandag", "ochtend"));
        weekDiensten.add(new Dienst(weekNummer, "1 maandag", "middag"));
        weekDiensten.add(new Dienst(weekNummer, "1 maandag", "avond"));

        // dinsdag
        weekDiensten.add(new Dienst(weekNummer, "2 dinsdag", "ochtend"));
        weekDiensten.add(new Dienst(weekNummer, "2 dinsdag", "middag"));
        weekDiensten.add(new Dienst(weekNummer, "2 dinsdag", "avond"));

        // woensdag
        weekDiensten.add(new Dienst(weekNummer, "3 woensdag", "ochtend"));
        weekDiensten.add(new Dienst(weekNummer, "3 woensdag", "middag"));
        weekDiensten.add(new Dienst(weekNummer, "3 woensdag", "avond"));

        // donderdag
        weekDiensten.add(new Dienst(weekNummer, "4 donderdag", "ochtend"));
        weekDiensten.add(new Dienst(weekNummer, "4 donderdag", "middag"));
        weekDiensten.add(new Dienst(weekNummer, "4 donderdag", "avond"));

        // vrijdag
        weekDiensten.add(new Dienst(weekNummer, "5 vrijdag", "ochtend"));
        weekDiensten.add(new Dienst(weekNummer, "5 vrijdag", "middag"));
        weekDiensten.add(new Dienst(weekNummer, "5 vrijdag", "avond"));

        // zaterdag
        weekDiensten.add(new Dienst(weekNummer, "6 zaterdag", "ochtend"));
        weekDiensten.add(new Dienst(weekNummer, "6 zaterdag", "middag"));
        weekDiensten.add(new Dienst(weekNummer, "6 zaterdag", "avond"));

        // zondag
        weekDiensten.add(new Dienst(weekNummer, "7 zondag", "ochtend"));
        weekDiensten.add(new Dienst(weekNummer, "7 zondag", "middag"));
        weekDiensten.add(new Dienst(weekNummer, "7 zondag", "avond"));


    }

    public static void printDienstenPerWeek(int weeknummer) {
        //filtert op het weeknummer wat hij heeft meegekregen
        var diensten = weekDiensten.stream()
                .filter(dienst -> dienst.getWeek() == weeknummer)
                .collect(Collectors.toList());


        //groepeerd hem op dag
        Map<String, List<Dienst>> DienstenPerdag;
        DienstenPerdag = diensten.stream().collect(Collectors.groupingBy(w -> w.getDag()));

        Map<String, List<Dienst>> sortedDienstenPerdag = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        sortedDienstenPerdag.putAll(DienstenPerdag);

        int teller = 1;
        for (var entry : sortedDienstenPerdag.entrySet()) {
            System.out.println(entry.getKey().substring(2));
            for (var dienst : entry.getValue()) {
                System.out.println("    " + teller + ": " + dienst.getTijd());
                teller++;
                if (dienst.getPerson() != null) {
                    System.out.println(" " + dienst.getPerson());
                }
            }
        }
    }

    public static void VoegPersoonToeAanDienst(int weeknummer, PersonDatabase PersonDB) {
        //filtert op het weeknummer wat hij heeft meegekregen
        var diensten = weekDiensten.stream()
                .filter(dienst -> dienst.getWeek() == weeknummer)
                .collect(Collectors.toList());


        for (Dienst dienst : diensten) {
            System.out.print(dienst.getDag().substring(2) + " " + dienst.getTijd() + ": ");
            String UserName = scanner.nextLine();

            //IgnorCase zorgt er voor dat het niet uit maakt of je een hoofd letter vergeet case sensitivity
            Person person = PersonDB.getAllpersons().stream()
                    .filter(p -> p.getUserName().equalsIgnoreCase(UserName))
                    .findFirst()
                    .orElse(null);

            if (person != null) {
                dienst.voegPersoonToe(person);
                person.voegDiensttoe(dienst);
            }
        }
    }

    public static void PasPlanningAan(int weeknummer, PersonDatabase PersonDB) {
        System.out.println("Welke dienst wil je aanpassen?");
        int keuze = scanner.nextInt() - 1;
        scanner.nextLine();

        var diensten = weekDiensten.stream()
                .filter(dienst -> dienst.getWeek() == weeknummer)
                .collect(Collectors.toList());

        var dienst = diensten.get(keuze);

        System.out.println("Welke werknemer wil je er aan koppelen: ");
        String UserName = scanner.nextLine();

        //IgnorCase zorgt er voor dat het niet uit maakt of je een hoofd letter vergeet case sensitivity
        Person person = PersonDB.getAllpersons().stream()
                .filter(p -> p.getUserName().equalsIgnoreCase(UserName))
                .findFirst()
                .orElse(null);

        if (person != null) {
            weekDiensten.stream()
                    .filter(d -> d.getWeek() == weeknummer && d.getDag() == dienst.getDag() && d.getTijd() == dienst.getTijd()).forEach(d -> {
                        if (d.getPerson() != null) {
                            d.getPerson().removeDienst(d);
                            d.verwijderPersoon();
                        }
                        d.voegPersoonToe(person);
                        person.voegDiensttoe(d);
                    });
        } else {
            System.out.println("No such person found.");
        }
    }
}