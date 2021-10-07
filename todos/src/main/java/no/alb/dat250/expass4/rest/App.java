package no.alb.dat250.expass4.rest;

import static spark.Spark.after;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.put;
import static spark.Spark.post;
import static spark.Spark.delete;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.mongodb.*;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;

import java.util.ArrayList;
import java.util.function.Consumer;
import org.bson.Document;
import org.bson.types.ObjectId;

public class App {

    static String uri = "mongodb://localhost:27017";

    public static void main(String[] args) {

        if (args.length > 0) {
            port(Integer.parseInt(args[0]));
        } else {
            port(8080);
        }

        after((req, res) -> {
            res.type("application/json");
        });

        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("todo");
        MongoCollection<Document> collection = database.getCollection("todos");

        post("/todo", (req, res) -> {

            try {
                Gson gson = new Gson();

                // creating a todo instance to avoid storing documents that are not todos
                Todo todo = gson.fromJson(req.body(), Todo.class);

                if (todo.getSummary() == null) {
                    throw new Exception("Todo must contain a summary");
                } else if (todo.getSummary().isEmpty()) {
                    throw new Exception("Summary is empty");
                }

                Document doc = Document.parse(gson.toJson(todo));

                InsertOneResult result = collection.insertOne(doc);

                return "Success! Inserted document id: " + result.getInsertedId();

            } catch (Exception e) {
                return "Unable to insert due to an error: " + e;
            }
        });

        get("/todos", (req, res) -> {

            try {

                FindIterable<Document> docs = collection.find();

                String todos = "";

                for (Document doc : docs) {
                    todos += doc.toJson();
                }

                return todos;

            } catch (Exception e) {
                return "Something went wrong" + e;
            }
        });

        get("/todo/:id", (req, res) -> {

            try {

                String id = req.params("id");

                Document doc = collection.find(eq("_id", new ObjectId(id))).first();

                String result = doc.toJson();

                return result;

            } catch (Exception e) {
                return "Something went wrong" + e;
            }
        });

        put("/todo/:id", (req, res) -> {
            try {

                Gson gson = new Gson();

                // creating a todo instance to avoid storing documents that are not todos
                Todo todo = gson.fromJson(req.body(), Todo.class);

                if (todo.getSummary() == null) {
                    throw new Exception("Todo must contain a summary");
                } else if (todo.getSummary().isEmpty()) {
                    throw new Exception("Summary is empty");
                }

                Document doc = Document.parse(gson.toJson(todo));

                String id = req.params("id");

                UpdateResult updateResult = collection.replaceOne(eq("_id", new ObjectId(id)), doc);

                String result = updateResult.toString();

                return result;

            } catch (Exception e) {
                return "Something went wrong" + e;
            }
        });

        delete("/todo/:id", (req, res) -> {
            try {

                String id = req.params("id");

                DeleteResult deleteResult = collection.deleteOne(eq("_id", new ObjectId(id)));

                String result = deleteResult.toString();

                return result;

            } catch (Exception e) {
                return "Something went wrong" + e;
            }
        });

    }

}
