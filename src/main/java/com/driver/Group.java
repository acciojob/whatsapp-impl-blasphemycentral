package com.driver;

public class Group {
    private String name;
    private int numberOfParticipants;
    private String admin;

    public Group(String name, int numberOfParticipants, String admin)
    {
        this.name = name;
        this.numberOfParticipants = numberOfParticipants;
        this.admin = admin;
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
