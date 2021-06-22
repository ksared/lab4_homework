package pl.kielce.tu.neo4j.ogm;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label = "Book")
public class Diagnosta {

    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "imie")
    private String imie;

    @Property(name = "nazwisko")
    private String nazwisko;

    public Diagnosta() {
    }

    public Diagnosta(String imie, String nazwisko) {

        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    public Long getId() {
        return id;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {return nazwisko;}

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    @Override
    public String toString() {
        return "Diagnosta [id=" + id + ", imie = " + imie + " nazwisko = " + nazwisko + "]";
    }
}
