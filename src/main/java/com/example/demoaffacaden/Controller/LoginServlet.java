package com.example.demoaffacaden.Controller;

import com.example.demoaffacaden.Controller.Entitet.Person;
import com.example.demoaffacaden.Model.Facade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Map<String , Person> personMap = Facade.getAllperson();

        getServletContext().setAttribute("personMap", personMap);

        System.out.println("størrelsen er " + personMap.size());



        request.getRequestDispatcher("index.jsp").forward(request,response);



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String navn = request.getParameter("navn");
        String kode = request.getParameter("kode");
        System.out.println("input fra indexside er : " + navn + " " + kode);

        Map<String,Person> personMap = (Map<String, Person>) getServletContext().getAttribute("personMap");

        Person person = personMap.getOrDefault(navn, null);

        if (person == null) {

            request.setAttribute("msg", "brugeren findes ikke");
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }

        if (!person.getKode().equalsIgnoreCase(kode)) {

            System.out.println("koden var forkert " + kode + " " + person.getKode()) ;
            request.setAttribute("msg", "kode er forkert , prøv igen");
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }


        HttpSession session = request.getSession();

        session.setAttribute("person", person);

        request.getRequestDispatcher("WEB-INF/brugerside.jsp").forward(request,response);




    }
}
