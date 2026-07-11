package Mission10.assignment.controller;

import Mission10.assignment.dto.AssignmentCreateRequest;
import Mission10.assignment.dto.AssignmentResponse;
import Mission10.assignment.dto.AssignmentUpdateRequest;
import Mission10.assignment.service.AssignmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PostMapping("/members/{memberId}/assignments")
    public ResponseEntity<AssignmentResponse> create(@PathVariable Long memberId,
                                                       @RequestBody AssignmentCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(AssignmentResponse.from(assignmentService.create(memberId, request)));
    }

    @GetMapping("/assignments")
    public ResponseEntity<List<AssignmentResponse>> findAll() {
        List<AssignmentResponse> responses = assignmentService.findAll().stream()
            .map(AssignmentResponse::from)
            .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/members/{memberId}/assignments")
    public ResponseEntity<List<AssignmentResponse>> findByMember(@PathVariable Long memberId) {
        List<AssignmentResponse> responses = assignmentService.findByMemberId(memberId).stream()
            .map(AssignmentResponse::from)
            .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/assignments/search")
    public ResponseEntity<List<AssignmentResponse>> search(@RequestParam String keyword) {
        List<AssignmentResponse> responses = assignmentService.searchByTitle(keyword).stream()
            .map(AssignmentResponse::from)
            .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/assignments/{id}")
    public ResponseEntity<AssignmentResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(AssignmentResponse.from(assignmentService.findById(id)));
    }

    @PutMapping("/assignments/{id}")
    public ResponseEntity<AssignmentResponse> update(@PathVariable Long id,
                                                       @RequestBody AssignmentUpdateRequest request) {
        return ResponseEntity.ok(AssignmentResponse.from(assignmentService.update(id, request)));
    }

    @DeleteMapping("/assignments/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        assignmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
