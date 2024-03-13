package com.example.awslogservice;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBconnection {
    private static final String CONNECTION_STRING = "mongodb://MongoDB:27017";
    private static final String DATABASE_NAME = "Taller6";

    public static MongoDatabase getDatabase() {
        MongoClient client = MongoClients.create(CONNECTION_STRING);
        return client.getDatabase(DATABASE_NAME);
    }
}
