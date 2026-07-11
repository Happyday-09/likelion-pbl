package Mission10.member.controller;

import Mission10.member.dto.*;
import Mission10.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/lions")
    public ResponseEntity<MemberResponse> createLion(@RequestBody LionCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(MemberResponse.from(memberService.createLion(request)));
    }

    @PostMapping("/staffs")
    public ResponseEntity<MemberResponse> createStaff(@RequestBody StaffCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(MemberResponse.from(memberService.createStaff(request)));
    }

    @GetMapping
    public ResponseEntity<List<MemberResponse>> findAll(@RequestParam(required = false) String part) {
        List<MemberResponse> responses = (part == null ? memberService.findAll() : memberService.findByPart(part))
            .stream()
            .map(MemberResponse::from)
            .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(MemberResponse.from(memberService.findById(id)));
    }

    @PutMapping("/lions/{id}")
    public ResponseEntity<MemberResponse> updateLion(@PathVariable Long id,
                                                      @RequestBody LionUpdateRequest request) {
        return ResponseEntity.ok(MemberResponse.from(memberService.updateLion(id, request)));
    }

    @PutMapping("/staffs/{id}")
    public ResponseEntity<MemberResponse> updateStaff(@PathVariable Long id,
                                                       @RequestBody StaffUpdateRequest request) {
        return ResponseEntity.ok(MemberResponse.from(memberService.updateStaff(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        memberService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
