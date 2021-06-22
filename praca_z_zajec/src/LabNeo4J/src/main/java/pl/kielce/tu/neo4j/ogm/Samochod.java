package pl.kielce.tu.neo4j.ogm;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label = "Samochod")
public class Samochod {

    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "marka")
    private String marka;

    @Property(name = "rocznik")
    private int rocznik;

    public Samochod() {
    }

    public Samochod(String marka, int rocznik) {
        this.marka = marka;
        this.rocznik = rocznik;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public void setRocznik(int rocznik) {
        this.rocznik = rocznik;
    }

    public void setDiagnosci(Set<Diagnosta> diagnosci) {
        this.diagnosci = diagnosci;
    }

    public Long getId() {
        return id;
    }

    public String getMarka() {
        return marka;
    }

    public int getRocznik(){return rocznik;}

    @Relationship(type = "DIAGNOSCI")
    private Set<Diagnosta> diagnosci = new HashSet<>();

    public void addDiagnosta(Diagnosta diagnosta) {
        diagnosci.add(diagnosta);
    }

    @Override
    public String toString() {
        return "Samochod [id=" + id + ", marka = " + marka + ", Rocznik = " + rocznik + "]";
    }
}