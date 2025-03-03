package com.plane.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.plane.utils.Env;

public class FabricaConexoes {

    private static int MAX_CONNECTIONS = 5;
    private final String URL_DB;
    private final String DB_NAME;
    private final String USER;
    private final String PASSWORD;
    private final String CON_STRING;

    private Connection[] conexoes;

    private static FabricaConexoes instance;

    private FabricaConexoes() {
        conexoes = new Connection[MAX_CONNECTIONS];
        // Modificar para fazer a conexão corretamente no phpMyAdmin
        URL_DB = Env.get("URL_DB");
        DB_NAME = Env.get("URL_NAME");
        CON_STRING = URL_DB + DB_NAME;
        USER = Env.get("DB_USER");
        PASSWORD = Env.get("DB_PASSWORD");

        System.err.println(URL_DB + DB_NAME);
        System.out.println(USER + " & " + PASSWORD);
    }

    public static FabricaConexoes getInstance() {
        if (instance != null) {
            return instance;
        }
        instance = new FabricaConexoes();
        return instance;
    }

    public Connection getConnection() throws SQLException {

        for (int i = 0; i < MAX_CONNECTIONS; i++) {
            if (instance.conexoes[i] == null || instance.conexoes[i].isClosed()) {
                instance.conexoes[i] = DriverManager.getConnection(CON_STRING, USER, PASSWORD);
                return instance.conexoes[i];
            }
        }
        throw new SQLException("Máximo de conexões");
    }
}