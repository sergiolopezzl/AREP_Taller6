package com.example.awslogservice;

import com.mongodb.client.MongoDatabase;
import java.util.Date;
import static spark.Spark.*;

public class LogService {

    public static void main(String... args) {
        MongoDatabase database = MongoDBconnection.getDatabase();
        LogList logList = new LogList(database);
        port(getPort());
        get("/service", (req, res) -> {
            Date currentDate = new Date();
            String dateString = currentDate.toString();
            logList.addLog(dateString, req.queryParams("msg"));
            System.out.println(req.queryParams("msg"));
            res.type("application/json");
            return logList.listLogs();
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
