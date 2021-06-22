import com.arangodb.ArangoCollection;
import com.arangodb.ArangoCursor;
import com.arangodb.ArangoDB;
import com.arangodb.ArangoDBException;
import com.arangodb.entity.BaseDocument;
import com.arangodb.entity.CollectionEntity;
import com.arangodb.entity.IndexEntity;
import com.arangodb.internal.util.DefaultArangoSerialization;
import com.arangodb.util.MapBuilder;
import com.arangodb.velocypack.VPackSlice;
import org.apache.log4j.BasicConfigurator;

import java.util.Collection;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class car_service{
    private static Scanner in = new Scanner( System.in );
    final static String dbName = "car_service";
    final static String samochody = "samochody";
    final static String diagnosci = "diagnosci";

    public static void main(String[] args) {
        BasicConfigurator.configure();
        final ArangoDB arangoDB = new ArangoDB.Builder().user("root").password("root").build();

/*
        // create database
        try {
            arangoDB.createDatabase(dbName);
            System.out.println("Utworzono baze danych: " + dbName);
        } catch (final ArangoDBException e) {
            System.err.println("Nie udalo sie utworzyc bazy danych: " + dbName + "; " + e.getMessage());
        }

        try {
            final CollectionEntity myArangoCollection = arangoDB.db(dbName).createCollection(samochody);
            System.out.println("Utworzono kolekcje: " + myArangoCollection.getName());
        } catch (final ArangoDBException e) {
            System.err.println("Nie udalo sie utworzyc kolekcji: " + samochody + "; " + e.getMessage());
        }

        try {
            final CollectionEntity myArangoCollection = arangoDB.db(dbName).createCollection(diagnosci);
            System.out.println("Utworzono kolekcje: " + myArangoCollection.getName());
        } catch (final ArangoDBException e) {
            System.err.println("Nie udalo sie utworzyc kolekcji: " + diagnosci + "; " + e.getMessage());
        }
        */


        int choice = -2;

        while (choice != 0){
            System.out.println("Wybierz:");
            System.out.println("0. ZAKONCZ");
            System.out.println("1. Dodaj...");
            System.out.println("2. Kasuj...");
            System.out.println("3. Aktualizacja...");
            System.out.println("4. Pobierz...");
            choice = Integer.parseInt(in.nextLine());
            if (choice == 0){
                break;
            }
            else if (choice == 1){
                add(arangoDB);
            }
            else if (choice == 2){
                delete(arangoDB);
            }
            else if (choice == 3){
                update(arangoDB);
            }
            else if (choice == 4){
                get_ar(arangoDB);
            }
            else {
                break;
            }
        }
    }

    private static void get_ar(ArangoDB arangoDB) {
        int choice = -2;
        while (choice != 0){
            System.out.println("Wybierz:");
            System.out.println("0. COFNIJ");
            System.out.println("1. Znajdź samochod po id...");
            System.out.println("2. Znajdź diagnoste po id...");
            choice = Integer.parseInt(in.nextLine());
            if (choice == 0){
                return;
            }
            else if (choice == 1){
                System.out.println("Podaj ID: ");
                String id = in.nextLine();
                try {
                    final BaseDocument samochod = arangoDB.db(dbName).collection(samochody).getDocument(id,
                            BaseDocument.class);
                    System.out.println("Key: " + samochod.getKey());
                    System.out.println("Marka: " + samochod.getAttribute("marka"));
                    System.out.println("model: " + samochod.getAttribute("model"));
                    System.out.println("vin: " + samochod.getAttribute("vin"));
                } catch (final ArangoDBException e) {
                    System.err.println("Failed to get document: myKey; " + e.getMessage());
                }
            }
            else if (choice == 2){
                System.out.println("Podaj ID: ");
                String id = in.nextLine();
                try {
                    final BaseDocument diagnosta = arangoDB.db(dbName).collection(diagnosci).getDocument(id,
                            BaseDocument.class);
                    System.out.println("Key: " + diagnosta.getKey());
                    System.out.println("Imie: " + diagnosta.getAttribute("imie"));
                    System.out.println("Nazwisko: " + diagnosta.getAttribute("nazwisko"));
                    System.out.println("Numer indeksu: " + diagnosta.getAttribute("pensja"));
                } catch (final ArangoDBException e) {
                    System.err.println("Failed to get document: myKey; " + e.getMessage());
                }
            }
            else {
                continue;
            }
        }
    }

    private static void update(ArangoDB arangoDB) {
        int choice = -2;
        while (choice != 0){
            System.out.println("Wybierz:");
            System.out.println("0. COFNIJ");
            System.out.println("1. Samochod...");
            System.out.println("2. Diagnosta...");
            choice = Integer.parseInt(in.nextLine());
            if (choice == 0){
                break;
            }
            else if (choice == 1){
                System.out.println("Podaj ID: ");
                String id = in.nextLine();
                System.out.println("Podaj marke: ");
                String marka = in.nextLine();
                System.out.println("Podaj model: ");
                String model = in.nextLine();
                System.out.println("Podaj vin: ");
                int vin = Integer.parseInt(in.nextLine());
                try {
                    final BaseDocument student = arangoDB.db(dbName).collection(samochody).getDocument(id,
                            BaseDocument.class);
                    final BaseDocument myObject = new BaseDocument();
                    myObject.addAttribute("marka", marka);
                    myObject.addAttribute("model", model);
                    myObject.addAttribute("vin", vin);
                    arangoDB.db(dbName).collection(samochody).updateDocument(id, myObject);
                } catch (final ArangoDBException e) {
                    System.err.println("Failed to get document: myKey; " + e.getMessage());
                }
            }
            else if (choice == 2){
                System.out.println("Podaj ID: ");
                String id = in.nextLine();
                System.out.println("Podaj imie: ");
                String imie = in.nextLine();
                System.out.println("Podaj nazwisko: ");
                String nazwisko = in.nextLine();
                System.out.println("Podaj pensje: ");
                int pensja = Integer.parseInt(in.nextLine());
                try {
                    final BaseDocument student = arangoDB.db(dbName).collection(samochody).getDocument(id,
                            BaseDocument.class);
                    final BaseDocument myObject = new BaseDocument();
                    myObject.addAttribute("imie", imie);
                    myObject.addAttribute("nazwisko", nazwisko);
                    myObject.addAttribute("pensja", pensja);
                    arangoDB.db(dbName).collection(diagnosci).updateDocument(id, myObject);
                } catch (final ArangoDBException e) {
                    System.err.println("Failed to get document: myKey; " + e.getMessage());
                }
            }
            else {
                continue;
            }
        }
    }

    private static void delete(ArangoDB arangoDB) {
        int choice = -2;
        while (choice != 0){
            System.out.println("Wybierz:");
            System.out.println("0. COFNIJ");
            System.out.println("1. Samochod...");
            System.out.println("2. Diagnosta...");
            choice = Integer.parseInt(in.nextLine());
            if (choice == 0){
                return;
            }
            else if (choice == 1){
                System.out.println("Podaj ID: ");
                String id = in.nextLine();
                try {
                    arangoDB.db(dbName).collection(samochody).deleteDocument(id);
                } catch (final ArangoDBException e) {
                    System.err.println("Failed to delete document. " + e.getMessage());
                }
            }
            else if (choice == 2){
                System.out.println("Podaj ID: ");
                String id = in.nextLine();
                try {
                    arangoDB.db(dbName).collection(diagnosci).deleteDocument(id);
                } catch (final ArangoDBException e) {
                    System.err.println("Failed to delete document. " + e.getMessage());
                }
            }
            else {
                continue;
            }
        }
    }

    private static void add(ArangoDB arangoDB) {
        int samochodid = 1;
        int diagnostaid = 1;
        int choice = -2;
        while (choice != 0){
            System.out.println("Wybierz:");
            System.out.println("0. COFNIJ");
            System.out.println("1. Samochod...");
            System.out.println("2. Diagnosta...");
            choice = Integer.parseInt(in.nextLine());
            if (choice == 0){
                return;
            }
            else if (choice == 1){
                System.out.println("Podaj marke: ");
                String marka = in.nextLine();
                System.out.println("Podaj model: ");
                String model = in.nextLine();
                System.out.println("Podaj vin: ");
                int vin = Integer.parseInt(in.nextLine());
                System.out.println("Dodawany samochod: "+marka+" "+model+" "+vin);

                String uuid_s = UUID.randomUUID().toString();
                //System.out.println("Samochod id:" + uuid_s);
                System.out.println("Samochod id:" + samochodid);
                final BaseDocument myObject = new BaseDocument();
                //myObject.setKey(uuid_s);
                myObject.setKey(String.valueOf(samochodid));
                samochodid+=1;
                myObject.addAttribute("marka", marka);
                myObject.addAttribute("model", model);
                myObject.addAttribute("vin", vin);
                try {
                    arangoDB.db(dbName).collection(samochody).insertDocument(myObject);
                    System.out.println("Samochod dodany");
                } catch (final ArangoDBException e) {
                    System.err.println("Nie udalo sie. " + e.getMessage());
                }
            }
            else if (choice == 2){
                System.out.println("Podaj imie: ");
                String imie = in.nextLine();
                System.out.println("Podaj nazwisko: ");
                String nazwisko = in.nextLine();
                System.out.println("Podaj pensje: ");
                int pensja = Integer.parseInt(in.nextLine());
                System.out.println("Nowy diagnosta: "+imie+" "+nazwisko+" "+pensja);

                //String uuid_s = UUID.randomUUID().toString();
                //System.out.println("Diagnosta id: " + uuid_s);
                final BaseDocument myObject = new BaseDocument();
                //myObject.setKey(uuid_s);
                System.out.println("Diagnosta id: " +diagnostaid);

                myObject.setKey(String.valueOf(diagnostaid));
                diagnostaid += 1;
                myObject.addAttribute("imie", imie);
                myObject.addAttribute("nazwisko", nazwisko);
                myObject.addAttribute("pensja", pensja);
                try {
                    arangoDB.db(dbName).collection(diagnosci).insertDocument(myObject);
                    System.out.println("Diagnosta dodany");
                } catch (final ArangoDBException e) {
                    System.err.println("Nie udalo sie. " + e.getMessage());
                }
            }
            else {
                continue;
            }
        }
    }
}