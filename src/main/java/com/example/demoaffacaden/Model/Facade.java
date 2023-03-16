package com.example.demoaffacaden.Model;

import datmatikkerene22.webhuskeapp.Controller.Entitet.Person;

import java.sql.SQLException;
import java.util.Map;

public class Facade
{
    public static Map<String, Person> getAllperson()
    {
       // return PersonMapper.getAllPerson();   // når vi bruger mappet
        try {
            return DBPersonMapper.fetchData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Person getPerson(String navn)
    {
        return  PersonMapper.getPerson(navn);

    }

    public static String savePerson(Person person) throws SQLException
    {
       // return PersonMapper.savePerson(person);

        return  DBPersonMapper.savePerson(person);
    }
}
