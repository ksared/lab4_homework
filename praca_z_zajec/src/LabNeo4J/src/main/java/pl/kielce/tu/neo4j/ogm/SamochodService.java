package pl.kielce.tu.neo4j.ogm;

import java.util.HashMap;
import java.util.Map;

import org.neo4j.ogm.session.Session;

class SamochodService extends GenericService<Samochod> {

    public SamochodService(Session session) {
        super(session);
    }

    @Override
    Class<Samochod> getEntityType() {
        return Samochod.class;
    }

    Iterable<Map<String, Object>> getSamochodRelationships() {
        String query =
                "MATCH (b:Samochod)-[r]-() " +
                        "WITH type(r) AS t, COUNT(r) AS c " +
                        "WHERE c >= 1 " +
                        "RETURN t, c";
        //System.out.println("Executing ");
        HashMap<String, Object> params = new HashMap<String, Object>();
        return session.query(query, params).queryResults();
    }

}