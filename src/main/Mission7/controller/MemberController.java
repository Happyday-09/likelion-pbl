package Mission7.controller;

import Mission7.dto.*;
import Mission7.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // POST /members/lions
    @PostMapping("/lions")
    public ResponseEntity<LionResponse> createLion(@RequestBody LionCreateRequest request) {
        LionResponse response = memberService.createLion(request);
        if (response == null) {
            return ResponseEntity.status(409).build();
        }
        return ResponseEntity.status(201).body(response);
    }

    // POST /members/staffs
    @PostMapping("/staffs")
    public ResponseEntity<StaffResponse> createStaff(@RequestBody StaffCreateRequest request) {
        StaffResponse response = memberService.createStaff(request);
        if (response == null) {
            return ResponseEntity.status(409).build();
        }
        return ResponseEntity.status(201).body(response);
    }

    // GET /members/{name}
    @GetMapping("/{name}")
    public ResponseEntity<Object> getMember(@PathVariable String name) {
        Object response = memberService.findMember(name);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    // PUT /members/lions/{name}
    @PutMapping("/lions/{name}")
    public ResponseEntity<LionResponse> updateLion(
            @PathVariable String name,
            @RequestBody LionUpdateRequest request) {
        LionResponse response = memberService.updateLion(name, request);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    // PUT /members/staffs/{name}
    @PutMapping("/staffs/{name}")
    public ResponseEntity<StaffResponse> updateStaff(
            @PathVariable String name,
            @RequestBody StaffUpdateRequest request) {
        StaffResponse response = memberService.updateStaff(name, request);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    // DELETE /members/{name}
    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteMember(@PathVariable String name) {
        boolean deleted = memberService.deleteMember(name);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}