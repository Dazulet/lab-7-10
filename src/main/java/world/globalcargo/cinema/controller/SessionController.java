package world.globalcargo.cinema.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import world.globalcargo.cinema.dto.SessionDTO;
import world.globalcargo.cinema.service.SessionService;
import world.globalcargo.cinema.service.SessionServiceInterface;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/sessions")
@RequiredArgsConstructor
public class SessionController {

    private final SessionServiceInterface sessionService;

    @GetMapping
    public ResponseEntity<List<SessionDTO>> getAllSessions() {
        List<SessionDTO> sessions = sessionService.getAllSessions();
        if (sessions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(sessions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SessionDTO> getSession(@PathVariable Long id) {
        SessionDTO session = sessionService.getSession(id);
        if (Objects.isNull(session)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(session);
    }

    @PostMapping
    public ResponseEntity<SessionDTO> addSession(@RequestBody SessionDTO sessionDto) {
        SessionDTO createdSession = sessionService.addSession(sessionDto);
        if (Objects.isNull(createdSession)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(createdSession, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SessionDTO> updateSession(@PathVariable Long id, @RequestBody SessionDTO sessionDto) {
        SessionDTO updatedSession = sessionService.updateSession(id, sessionDto);
        if (Objects.isNull(updatedSession)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(updatedSession);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable Long id) {
        boolean isDeleted = sessionService.deleteSession(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}