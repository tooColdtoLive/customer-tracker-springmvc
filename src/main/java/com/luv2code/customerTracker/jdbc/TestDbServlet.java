package com.luv2code.customerTracker.jdbc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

@WebServlet(urlPatterns={"/TestDbServlet"})
public class TestDbServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String jdbcUrl = "jdbc:mysql://localhost:3306/customer_tracker?useSSL=false&serverTimezone=UTC";
        String userId = "customerTracker";
        String password = "customerTracker";

        try{
            PrintWriter out = resp.getWriter();

            out.println("Connecting to database: " + jdbcUrl);

            Class.forName("com.mysql.jdbc.Driver"); // provide the category of the drive

            Connection conn = DriverManager.getConnection(jdbcUrl, userId, password);
            out.println("Successfully connected to " + jdbcUrl);
        }catch(Exception e){
            e.printStackTrace();
            throw new ServletException();
        }

    }
}
