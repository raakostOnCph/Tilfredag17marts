package com.example.demoaffacaden.Model;

import com.example.demoaffacaden.Controller.AppStart;
import com.example.demoaffacaden.Controller.Entitet.Person;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DBPersonMapper
{

    public static Map<String, Person> fetchData() throws SQLException
    {
        String SQL_QUERY = "select * from Person";
     List<Person> personList = null;

        try (Connection con = AppStart.getConnectionPool().getConnection();
             PreparedStatement pst = con.prepareStatement( SQL_QUERY );
             ResultSet rs = pst.executeQuery();)

        {
            personList = new LinkedList<>();

            Person person;

            while ( rs.next() ) {
                person = new Person();
                person.setId( rs.getInt( "idPerson" ) );
                person.setNavn( rs.getString( "navn" ) );
                person.setKode( rs.getString( "kode" ) );
                person.setStringSet(hentEmner(person.getId()));

                personList.add(person);

            }
        }

        Map<String,Person> personMap = new HashMap<>();

        for (Person person : personList) {
            personMap.put(person.getNavn(), person);
        }

        return personMap;
    }


    public static Set<String> hentEmner(int i) throws SQLException
    {
        String SQL_QUERY = "select title from Emner where EjerId =?";
        Set<String> emneListe = null;

        try (Connection con = AppStart.getConnectionPool().getConnection();
             PreparedStatement pst = con.prepareStatement( SQL_QUERY );)


        {
            pst.setInt(1,i);
            ResultSet rs = pst.executeQuery();
            emneListe = new TreeSet<>();

            Person person;

            while ( rs.next() ) {



                emneListe.add( rs.getString("title") );
            }
        }






        return emneListe;
    }


    public static String savePerson(Person person) throws SQLException
    {
            String SQL_QUERY = "select title from Emner where EjerId =?";
            Set<String> gammeltSet = null;
            int indsat=0;
            int slettet =0;

            try (Connection con = AppStart.getConnectionPool().getConnection();
                 PreparedStatement pst = con.prepareStatement( SQL_QUERY );)

            {
                pst.setInt(1,person.getId());
                ResultSet rs = pst.executeQuery();
                gammeltSet = new TreeSet<>(hentEmner(person.getId()));


                Set<String > nytSet = new TreeSet<>(person.getStringSet());

                Set<String> added = new TreeSet<>(nytSet);

                added.removeAll(gammeltSet);
                indsat = added.size();
                insertEmner(person.getId(), added);


                Set<String> deleted = new TreeSet<>(gammeltSet);
                deleted.removeAll(nytSet);
                slettet = deleted.size();
                deleteEmne(person.getId(), deleted);




            }

        return "indsat " + indsat + " emner, " +"slettet " + slettet ;



        }



    private static void deleteEmne(int id, Set<String> deleted)
    {
        String SQL_QUERY = "delete  from Emner where title = ? and EjerId = ? ";

        try (Connection con = AppStart.getConnectionPool().getConnection();
             PreparedStatement pst = con.prepareStatement( SQL_QUERY );)

        {
            for (String s : deleted) {
                pst.setString(1,s);
                pst.setInt(2, id);
                pst.addBatch();
            }

            pst.executeBatch();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }







    private static void insertEmner(int i ,Set<String> added) throws SQLException
    {
        String SQL_QUERY = "INSERT  into Emner (EjerId, title) values (? , ?) ";

        try (Connection con = AppStart.getConnectionPool().getConnection();
             PreparedStatement pst = con.prepareStatement( SQL_QUERY );)

        {
            for (String s : added) {
                pst.setInt(1,i);
                pst.setString(2, s);
                pst.addBatch();
            }

            pst.executeBatch();

        }

    }




}
