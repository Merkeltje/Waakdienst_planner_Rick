package com.company.Person;

public class Planner extends Person {
    private String Function = "Planner";

    public Planner(String userName, String password) {
        super(userName, password);
    }

    public String getFunction() {
        return Function;
    }
}
