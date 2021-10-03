package no.alb.dat250.expass4.rest;

import static spark.Spark.after;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.put;

import com.google.gson.Gson;
import com.mongodb.client.*;

public class App {

    public static void main(String[] args) {
        /*
         * if (args.length > 0) { port(Integer.parseInt(args[0])); } else { port(8080);
         * }
         * 
         * counters = new Counters();
         * 
         * after((req, res) -> { res.type("application/json"); });
         * 
         * get("/hello", (req, res) -> "Hello World!");
         * 
         * get("/counters", (req, res) -> counters.toJson());
         * 
         * get("/counters/red", (req, res) -> counters.getRed());
         * 
         * get("/counters/green", (req, res) -> counters.getGreen());
         * 
         * // TODO: put for green/red and in JSON // variant that returns
         * link/references to red and green counter put("/todo", (req, res) -> {
         * 
         * Gson gson = new Gson();
         * 
         * counters = gson.fromJson(req.body(), Counters.class);
         * 
         * return counters.toJson();
         * 
         * });
         */
    }

}
