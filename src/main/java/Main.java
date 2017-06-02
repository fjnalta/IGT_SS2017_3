/**
 * Created by ajo on 02.06.17.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import services.DBService;
import services.MultiDBService;

/**
 * Basic Client for CRUD Operations on different noSQL Database Types
 * Hibernate supports ORM for the following noSQL types
 * - document: mongoDB
 * - graph: neo4j
 * - column: cassandra
 * - key-value: infinispan, ehcache
 */

public class Main {

    private static DBService currentService;

    public static void main(String[] args) {

        boolean loop = true;

        while (loop) {

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Please select the Database");
            System.out.println("[mysql; mongodb; neo4j; cassandra; infinispan]");
            System.out.println("q: Quit");
            System.out.print("Selection: ");
            String selection = "asd";

            try {
                selection = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            switch (selection) {
                case "mysql":
                    currentService = new MultiDBService(selection);
                    break;

                case "mongodb":
                    currentService = new MultiDBService(selection);
                    break;

                case "neo4j":
                    // TODO - Connect Neo4J
                    break;

                case "cassandra":
                    // TODO - Connect Cassandra
                    break;

                case "infinispan":
                    // TODO - Connect Infinispan/EhCache
                    break;

                case "q":
                    loop = false;
                    break;

                default:
                    break;

            }

            // TODO - select operation from Interface

        }
    }
}
