package lightning.gathergo.controller;

import lightning.gathergo.dto.LoginDto;
import lightning.gathergo.dto.SignupDto;
import lightning.gathergo.model.Session;
import lightning.gathergo.service.SessionService;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

@RestController
public class AuthController {
    private final SessionService sessionService;

    @Autowired
    public AuthController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping("/api/login")
    public ResponseEntity<Session> login(@RequestBody LoginDto loginDto) {
        String userId = loginDto.getUserId();
        String userName = "";

        // TODO: User DB 참조해 userName 가져오기

        Session session = sessionService.createSession(userId, userName);
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(session, headers, HttpStatus.OK);
    }
}
