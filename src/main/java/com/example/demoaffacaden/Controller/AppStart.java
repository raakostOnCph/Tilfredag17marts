package com.example.demoaffacaden.Controller;

import com.example.demoaffacaden.Controller.Entitet.Person;
import com.example.demoaffacaden.Model.ConnectionPool;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppStart implements ServletContextListener
{
    private static Map<String, Person> personMap = new HashMap<>();

    private static ConnectionPool connectionPool;

    @Override
    public void contextInitialized(ServletContextEvent sce)
    {
      ServletContextListener.super.contextInitialized(sce);


        try {
            Class.forName("org.slf4j.impl.StaticLoggerBinder");
            connectionPool = new ConnectionPool();
        } catch (ClassNotFoundException e) {
            Logger.getLogger("web").log(Level.SEVERE, e.getMessage(), e);
        }


        try {

            List<Person> personList = new ArrayList<>();

            Person person = new Person("nik", "1", "bamse, is, pude");
            Person person1 = new Person("Daniel", "1", "bamse, is, pude");
            Person person2 = new Person("Tobias", "1", "øl, pløkker, pude");
            Person person3 = new Person("signe", "1", "vand, is, gril");

            personList.add(person);
            personList.add(person1);
            personList.add(person2);
            personList.add(person3);


            for (Person element : personList) {

                personMap.put(element.getNavn(), element);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Map<String, Person> getPersonMap()
    {

        return personMap;

    }


//    @Override
//    public void contextDestroyed(ServletContextEvent sce)
//    {
//        ServletContextListener.super.contextDestroyed(sce);
//    }


////////

    public static ConnectionPool getConnectionPool()
    {
        return connectionPool;
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce)
    {
        Logger.getLogger("web").log(Level.INFO, "Shutting down application and connection pool");
        unregisterJDBCdrivers();
        connectionPool.close();
    }

    private void unregisterJDBCdrivers()
    {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        // Loop through all drivers
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            if (driver.getClass().getClassLoader() == cl) {
                // This driver was registered by the webapp's ClassLoader, so deregister it:
                try {
                    Logger.getLogger("web").log(Level.INFO, "Deregistering JDBC driver");
                    DriverManager.deregisterDriver(driver);
                } catch (SQLException ex) {
                    Logger.getLogger("web").log(Level.SEVERE, "Error deregistering JDBC driver");
                }
            } else {
                // driver was not registered by the webapp's ClassLoader and may be in use elsewhere
                Logger.getLogger("web").log(Level.WARNING, "Error deregistering JDBC driver");
            }
        }
    }



}
