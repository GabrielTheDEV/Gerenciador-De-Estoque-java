package com.application.config;

import com.application.service.DataTimeFormat;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.application.service.DataTimeFormat.*;

public class DatabaseConfig {
    private static final Dotenv dotenv = Dotenv.load();

    private static final String URL = dotenv.get("DB_URL");
    private static final String USER = dotenv.get("DB_USER");
    private static final String PASS = dotenv.get("DB_PASSWORD");

    public static Connection getConnection(){
        int maxRetries = 2;
        for (int i = 0; i < maxRetries; i++){
            try{
                Connection conn = DriverManager.getConnection(URL, USER, PASS);
                System.out.println("[ INFO ]["+ DataTimeFormat.getTimeInfo() +"]Conectado ao banco de dados! ");
                return conn;
            }catch (SQLException err){
                System.out.println("Tentando conectar ao banco de dados" + err);
                try{
                    Thread.sleep(2000);
                }catch (InterruptedException ignored){}
            }
        }throw new RuntimeException("[ERROR] - Não foi possivel conectar ao banco de dados");
    }
}
