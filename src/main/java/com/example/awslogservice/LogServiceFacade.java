package com.example.awslogservice;

import static spark.Spark.*;


public class LogServiceFacade {


    public static void main() {
        String[] logServices = getLogServicesURLS(System.getenv("LOG_SERVICES").split(";"));
        ServiceInvoker invoker = new ServiceInvoker(logServices);
        staticFiles.location("/public");
        port(getPort());
        get("/logs", (req, res) -> {
            res.type("application/json");
            System.out.println(req.queryParams("msg"));
            return invoker.invoke(req.queryParams("msg").replace(" ", "%20"));
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) return Integer.parseInt(System.getenv("PORT"));
        return 4567;
    }

    public static String[] getLogServicesURLS(String[] logServices) {
        String[] logServicesURLS = new String[logServices.length];
        for (int i = 0; i < logServicesURLS.length; i++) {
            logServicesURLS[i] = "http://" + logServices[i] + ":35000/service?msg=";
        }
        return logServicesURLS;
    }
}