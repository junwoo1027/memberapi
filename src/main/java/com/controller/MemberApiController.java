package com.controller;

import com.domain.Member;
import com.dto.CreateMemberDto;
import com.dto.UpdateMemberDto;
import com.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @GetMapping("/api/members")
    public Result memberList() {
        List<Member> members = memberService.findMembers();
        List<MemberDto> collect = members.stream().map(m -> new MemberDto(m.getUsername()))
                .collect(Collectors.toList());

        return new Result(collect);
    }

    @PostMapping("/api/members")
    public ResponseEntity saveMember(@RequestBody @Valid CreateMemberDto createMemberDto) {

        Member member = new Member();
        member.setUsername(createMemberDto.getUsername());

        memberService.join(member);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/members/{id}")
    public ResponseEntity updateMember(@PathVariable("id") Long id, @RequestBody @Valid UpdateMemberDto updateMemberDto) {

        memberService.update(id, updateMemberDto.getUsername());
        return ResponseEntity.ok().build();
    }


    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String username;
    }
}
