package lightning.gathergo;

import lightning.gathergo.model.Session;
import lightning.gathergo.repository.SessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class SessionRepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(SessionRepositoryTest.class);

    private SessionRepository sessionRepository;

    private static class SessionTestRepository extends SessionRepository {
        private List<String> userIds = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e"));

        private SessionTestRepository() {
            super.sessions = new HashMap<>();

            for(int i=0;i<5;++i) {
                String sid = String.valueOf(UUID.randomUUID());
                Session s = new Session(userIds.get(i), userIds.get(i));
            }
        }
    }

    @BeforeEach
    public void init() {
        this.sessionRepository = new SessionTestRepository();
    }

    @Test
    @DisplayName("정상 생성 테스트")
    public void create() {
        // given
        Session s = new Session("a", "username");

        // when
        boolean result = sessionRepository.createSession(s);

        // then
        assertThat(result).isEqualTo(true);
    }
}
