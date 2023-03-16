package com.example.demoaffacaden.Model;

import com.example.demoaffacaden.Controller.AppStart;
import com.example.demoaffacaden.Controller.Entitet.Person;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class PersonMapper
{

    private static Map<String , Person> personMap = AppStart.getPersonMap();

    protected static Map<String, Person> getAllPerson() {

    return personMap;


    }

    public static Person copyPerson(Person person ) {

        if (person == null) {
            return null;
        }

        Set<String> stringList = new TreeSet<>(person.getStringSet());

        return new Person(person.getNavn(), person.getKode(), stringList);

    }


    public static Person getPerson(String navn)
    {

        return copyPerson(personMap.getOrDefault(navn, null));


    }

    public static String savePerson(Person person)
    {

        if (personMap.containsKey(person.getNavn())) {

            personMap.put(person.getNavn(),person);
            return "updatere " + person.getNavn();
        }

        return "gemmer " + person.getNavn();
    }
}
