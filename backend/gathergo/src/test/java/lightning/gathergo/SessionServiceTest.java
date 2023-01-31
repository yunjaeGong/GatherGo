package lightning.gathergo;

import lightning.gathergo.model.Session;
import lightning.gathergo.repository.SessionRepository;
import lightning.gathergo.service.SessionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class SessionServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(SessionServiceTest.class);

    private SessionService sessionService;
    @MockBean
    private SessionRepository sessionRepository;

    @BeforeEach
    void init() {

    }
    @Test
    @DisplayName("Session의 createDate를 DB에 저장 후 읽어온 값이 쓴 값과 일치하는지 테스트")
    public void createDateTest() {
        // given

        // when
        Session expected = sessionService.createSession("gildong", "Hong gildong");
        logger.debug("expected uid: {}", expected.getSessionId());
        logger.debug("getall: {}", sessionRepository.getSessions());

        // then
        Session found = sessionService.findSessionBySID(expected.getSessionId());
        logger.debug("found uid: {}", found.getSessionId());
        assertThat(found.getCreateDate()).isEqualTo(found.getCreateDate());
    }
}
