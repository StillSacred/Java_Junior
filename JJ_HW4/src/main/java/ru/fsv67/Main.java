package ru.fsv67;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        String searchAuthor = "Роберт Мартин";
//        searchAuthor = "Эрих Гамма";
        // JDBC
        System.out.println("JDBC");
        DatabaseJDBC databaseJDBC = new DatabaseJDBC();
        Connection connectionDatabase = databaseJDBC.dbConnection();
        databaseJDBC.prepareTables(connectionDatabase);
        databaseJDBC.insertData(connectionDatabase);
        databaseJDBC.getData(connectionDatabase, searchAuthor);
        databaseJDBC.dbClose(connectionDatabase);

        // HIBERNATE
        System.out.println("\nHIBERNATE");
        DatabaseHibernate databaseHibernate = new DatabaseHibernate();
        databaseHibernate.createTableBook();
        databaseHibernate.getBooksByAuthor(searchAuthor);
        databaseHibernate.closedSession();
    }
}