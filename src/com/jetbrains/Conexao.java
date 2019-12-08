package com.jetbrains;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


//Começar a conexao à base de dados

public class Conexao {

        private static Connection con;

        // JDBC driver name, database URL and database credentials (user e password)


        public static void iniciarConexao(String driver, String url, String user, String password) {
            try {
                Class.forName(driver);
                con = DriverManager.getConnection(url + "?user=" + user + "&password=" + password);
            }
            catch (ClassNotFoundException | SQLException e) { }
        }

        public static Connection getConexao() {
            return con;
        }
}

