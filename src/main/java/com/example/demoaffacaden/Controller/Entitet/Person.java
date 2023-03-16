package com.example.demoaffacaden.Controller.Entitet;

import java.util.*;

public class Person
{



    private String navn;
    private String kode;
    List<String> stringList;

    public Person()
    {
    }

    public Person(String navn, String kode, String s)
    {
        this.navn = navn;
        this.kode = kode;

        String [] strings = s.split(",");

        this.stringList = new ArrayList<>(Arrays.asList(strings));
    }

    public Person(String navn, String kode, List<String> stringArrayList)
    {
        this.navn = navn;
        this.kode = kode;
        this.stringList = new ArrayList<>(stringArrayList);
    }

    public List<String> getStringSet()
    {
        return stringList;
    }

    public void setStringSet(List<String> stringList)
    {
        this.stringList = stringList;
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



}
