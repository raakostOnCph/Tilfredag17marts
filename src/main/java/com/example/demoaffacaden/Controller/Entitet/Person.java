package com.example.demoaffacaden.Controller.Entitet;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class Person
{


    private int id;
    private String navn;
    private String kode;
    Set<String> stringSet;

    public Person()
    {
    }

    public Person(String navn, String kode, String s)
    {
        this.navn = navn;
        this.kode = kode;

        String [] strings = s.split(",");

        this.stringSet = new TreeSet<>(Arrays.asList(strings));
    }

    public Person(String navn, String kode, Set<String> stringArrayList)
    {
        this.navn = navn;
        this.kode = kode;
        this.stringSet = new TreeSet<>(stringArrayList);
    }

    public Set<String> getStringSet()
    {
        return stringSet;
    }

    public void setStringSet(Set<String> stringSet)
    {
        this.stringSet = stringSet;
    }

    public String getNavn()
    {
        return navn;
    }

    public void setNavn(String navn)
    {
        this.navn = navn;
    }

    public String getKode()
    {
        return kode;
    }

    public void setKode(String kode)
    {
        this.kode = kode;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

}
