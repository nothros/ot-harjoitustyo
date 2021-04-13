/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curriculatorapp.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ehorrosw
 */
public class Connect {

    public static void connect() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:curriculatorapp.db";
            conn = DriverManager.getConnection(url);

            System.out.println("Connected to SQLite");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        connect();
    }

}