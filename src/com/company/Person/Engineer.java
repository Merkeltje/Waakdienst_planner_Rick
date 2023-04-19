package com.company.Person;

public class Engineer extends Person {
    private String Function = "Engineer";
    private static int Diensten;

    public Engineer(String userName, String password, int Diensten) {
        super(userName, password);
        this.Diensten = Diensten;
    }

    public String getFunction() {
        return Function;
    }

}
