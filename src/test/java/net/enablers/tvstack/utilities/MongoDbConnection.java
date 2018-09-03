package net.enablers.tools.utilities;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class MongoDbConnection {


    private static final Logger log = LoggerFactory.getLogger(MongoDbConnection.class);

    MongoClientURI uri = null;
    MongoClient mongoClient = null;
    MongoDatabase database = null;
    MongoCollection<Document> clientsCollection, marketsCollection, projectsCollection, audiencesCollection, surveysCollection, scenariosCollection, buyingAudiencesCollection, countriesCollection;

    public void connect() {
        uri = new MongoClientURI("mongodb://admin:admin@ec2-35-176-240-97.eu-west-2.compute.amazonaws.com:27017/?authSource=tvstack");
        mongoClient = new MongoClient(uri);
        database = mongoClient.getDatabase("tvstack");
        log.info("Connected to database");
    }


    //Fetching client documents
    public MongoCollection queryClients() {
        log.info("******************************** Clients ************************************************");
        clientsCollection = database.getCollection("Clients");
        System.out.println("Clients  : " + clientsCollection.count());
        return clientsCollection;
    }

    public void fetchClients() {
        List<Document> clients = (List<Document>) clientsCollection.find().into(new ArrayList<Document>());
        for (Document client : clients) {
            System.out.println("Name : " + client.get("name") + "         Market Id : " + client.get("marketId") + "       Country : " + client.get("country"));
        }
    }


    //Fetching market documents
    public MongoCollection queryMarkets() {
        log.info("******************************** Markets ************************************************");
        marketsCollection = database.getCollection("Markets");
        System.out.println("Markets  : " + marketsCollection.count());
        return marketsCollection;
    }

    public void fetchMarkets() {
        List<Document> markets = (List<Document>) marketsCollection.find().into(new ArrayList<Document>());
        for (Document market : markets) {
            System.out.println("_id : " + market.get("_id") + " name : " + market.get("name"));

            List<Document> campaignLengths = (List<Document>) market.get("campaignLengths");
            for (Document campaignLength : campaignLengths) {
                System.out.println("CampaignLengthId : " + campaignLength.get("campaignLengthId"));
                System.out.println("Name  : " + campaignLength.get("name"));

            }
        }
    }


    //Fetching project documents
    public MongoCollection queryProjects() {
        log.info("******************************** Projects ************************************************");
        projectsCollection = database.getCollection("Projects");
        System.out.println("Projects : " + projectsCollection.count());
        return projectsCollection;
    }

    public void fetchProjects() {
        List<Document> projects = (List<Document>) projectsCollection.find().into(new ArrayList<Document>());
        for (Document project : projects) {
            System.out.println("MarketId : " + project.get("marketId") + " budget : " + project.get("budget") + "     Owner  : " + project.get("owner") + "    Client : " + project.get("client"));
        }
    }


    //Fetching Audiences documents
    public MongoCollection queryAudiences() {
        log.info("******************************** Audiences ************************************************");
        audiencesCollection = database.getCollection("Audiences");
        System.out.println("Audiences : " + audiencesCollection.count());
        return audiencesCollection;
    }


    //Fetching Surveys documents
    public MongoCollection querySurveys() {
        log.info("******************************** Surveys ************************************************");
        surveysCollection = database.getCollection("Surveys");
        System.out.println("Surveys : " + surveysCollection.count());
        return surveysCollection;
    }

    //Fetching ﻿Scenarios documents
    public MongoCollection queryScenarios() {
        log.info("******************************** Scenarios ************************************************");
        scenariosCollection = database.getCollection("Scenarios");
        System.out.println("Scenarios : " + scenariosCollection.count());
        return scenariosCollection;
    }

    //Fetching ﻿BuyingAudiences documents
    public MongoCollection queryBuyingAudiences() {
        log.info("******************************** BuyingAudiences ************************************************");
        buyingAudiencesCollection = database.getCollection("BuyingAudiences");
        System.out.println("BuyingAudiences : " + buyingAudiencesCollection.count());
        return buyingAudiencesCollection;
    }

    //Fetching ﻿Countries documents
    public MongoCollection queryCountries() {
        log.info("******************************** Countries ************************************************");
        countriesCollection = database.getCollection("Countries");
        System.out.println("Countries : " + countriesCollection.count());
        return countriesCollection;
    }

    //Fetching Formats documents
    public MongoCollection queryFormats() {
        log.info("******************************** Formats ************************************************");
        countriesCollection = database.getCollection("Formats");
        System.out.println("Countries : " + countriesCollection.count());
        return countriesCollection;
    }

    public void disconnect() {
        mongoClient.close();
        log.info("Database connection closed");
    }

}
