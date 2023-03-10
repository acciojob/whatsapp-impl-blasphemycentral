package com.driver;

public class Group {
    private String name;
    private int numberOfParticipants;
    private String admin;

    public Group(String name, int numberOfParticipants)
    {
        this.name = name;
        this.numberOfParticipants = numberOfParticipants;
    }

    public String getName()
    {
        return name;
    }

    public int getNumberOfParticipants()
    {
        return numberOfParticipants;
    }

    public String getAdmin()
    {
        return admin;
    }

    public void setAdmin(String admin)
    {
        this.admin = admin;
    }
}
