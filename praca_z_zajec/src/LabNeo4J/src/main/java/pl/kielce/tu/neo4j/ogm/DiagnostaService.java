package pl.kielce.tu.neo4j.ogm;

import org.neo4j.ogm.session.Session;

class DiagnostaService extends GenericService<Diagnosta> {

    public DiagnostaService(Session session) {
        super(session);
    }

    @Override
    Class<Diagnosta> getEntityType() {
        return Diagnosta.class;
    }

}