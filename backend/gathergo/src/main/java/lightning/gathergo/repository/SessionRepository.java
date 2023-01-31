package lightning.gathergo.repository;

import lightning.gathergo.model.Session;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;

@Component
public class SessionRepository {
    /*
     * key: Session id
     * value: Session
     */
    protected Map<String, Session> sessions = new HashMap<>();  // 테스트 위해 protected

    public Optional<Session> findSessionBySid(String sid) {
        return Optional.ofNullable(this.sessions.get(sid));
    }

    public boolean createSession(Session s) {
        return this.sessions.put(s.getSessionId(), s) == null;
    }

    public void deleteSessionBySid(String sid) {
        this.sessions.remove(sid);
    }


    public Optional<Collection<Session>> getSessions() {
        return Optional.of(this.sessions.values());
    }
}
