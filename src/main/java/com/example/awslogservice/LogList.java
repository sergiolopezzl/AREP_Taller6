package com.example.awslogservice;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class LogList {

    private final MongoCollection<Document> logsCollection;

    public LogList(MongoDatabase database) {
        this.logsCollection = database.getCollection("logs");
    }

    public void addLog(String date, String description) {
        Document newLog = new Document("date", date)
                .append("description", description);
        logsCollection.insertOne(newLog);
    }

    public String listLogs() {
        FindIterable<Document> logs = logsCollection.find()
                .sort(new BasicDBObject("date", -1))
                .limit(10);
        return logsToJSON(logs);
    }

    public static String logsToJSON(FindIterable<Document> logs) {
        StringBuilder logsStringBuilder = new StringBuilder();
        for (Document document : logs) {
            logsStringBuilder.append(document.toJson());
            logsStringBuilder.append(",\n");
        }
        return "{\"logs\": [" + logsStringBuilder.substring(0, logsStringBuilder.length() - 2) + "]}";
    }
}
