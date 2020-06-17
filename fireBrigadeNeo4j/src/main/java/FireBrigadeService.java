import model.FireBrigade;
import model.Fireman;
import org.neo4j.ogm.session.Session;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FireBrigadeService extends GenericService<FireBrigade> {

    private Session session;
    private final FiremanService firemanService;

    public FireBrigadeService(Session session) {
        super(session);
        this.session = session;
        this.firemanService =new FiremanService(session);
    }

    @Override
    Class<FireBrigade> getEntityType() {
        return FireBrigade.class;
    }

    @Override
    public void deleteAll() {
        this.firemanService.deleteAll();
        super.deleteAll();
    }

    public void updateMotto(String newMotto, Long id) {
        String query = "MATCH (brigade:FireBrigade) " +
                "WHERE ID(brigade) = $brigadeId " +
                "SET brigade.motto = $motto";
        Map<String, Object> map = new HashMap<>();
        map.put("brigadeId", id);
        map.put("motto", newMotto);
        session.queryForObject(FireBrigade.class, query, map);
    }

    public Iterable<FireBrigade> getFirebrigadesByCreatedDate(Date from, Date to) {
        String query = "MATCH (brigade:FireBrigade) " +
                "WHERE brigade.created > $from AND brigade.created < $to " +
                "RETURN brigade";
        Map<String, Object> map = new HashMap<>();
        map.put("from", from.getTime());
        map.put("to", to.getTime());
        return session.query(FireBrigade.class, query, map);
    }

    public static class FiremanService extends GenericService<Fireman> {

        public FiremanService(Session session) {
            super(session);
        }

        @Override
        Class<Fireman> getEntityType() {
            return Fireman.class;
        }

        public void incrementFiremansAge() {
            String query = "MATCH (fireman:Fireman) " +
                    "SET fireman.age = (fireman.age + 1) ";
            session.query(query, new HashMap<>());
        }

    }
}
