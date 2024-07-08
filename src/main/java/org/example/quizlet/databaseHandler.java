package org.example.quizlet;

import java.sql.*;

public class databaseHandler {

    private static Connection con;
    private boolean hasData = false;

    private void initialise() throws SQLException {
        if (!hasData) {
            hasData = true;
            Statement state = con.createStatement();
            ResultSet res = state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='cards'");
            if(!res.next()) {
                System.out.println("Building the user table with prepopulated data");
                Statement state2 = con.createStatement();
                state2.execute("CREATE TABLE IF NOT EXISTS cards (id INTEGER PRIMARY KEY, title TEXT, frontSide TEXT, backSide TEXT);");

                PreparedStatement prep = con.prepareStatement("INSERT INTO cards (title, frontSide, backSide) VALUES (?, ?, ?)");

                prep.setString(1, "The first ever card");
                prep.setString(2, "Once upon a time...");
                prep.setString(3, "...There was a child");
                prep.execute();

                prep.setString(1, "Second card");
                prep.setString(2, "Magic is...");
                prep.setString(3, "...sadly not real.");
                prep.execute();
            }
        }
    }

    private void getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:database.db");
        initialise();
    }

    public ResultSet getCards() throws SQLException, ClassNotFoundException {
        if (con == null) {
            getConnection();
        }
        Statement state = con.createStatement();
        return state.executeQuery("SELECT title, frontSide, backSide FROM cards");
    }

    public void addCard(String title, String frontSide, String backSide) throws SQLException, ClassNotFoundException {
        if (con == null) {
            getConnection();
        }
        PreparedStatement prep = con.prepareStatement("INSERT INTO cards (title, frontSide, backSide) VALUES (?, ?, ?)");
        prep.setString(1, title);
        prep.setString(2, frontSide);
        prep.setString(3, backSide);
        prep.execute();
    }
}
