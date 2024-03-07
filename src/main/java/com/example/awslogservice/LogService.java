package com.example.awslogservice;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;

public class LogService {

    public static void main(String... args) {
        MongoDatabase database = ConexionMongoDB.conectar();
        MongoCollection<Document> collection = database.getCollection("registro");

        port(35000);
        // get("/guardar", (req,res) -> webClient());
        get("/logservice", (req, res) -> {
            res.type("application/json");
            return "{\"logid1\":\"20-2-2024-Log Inicial\"}";
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

    public static String getLogs() {

        return null;
    }

}
