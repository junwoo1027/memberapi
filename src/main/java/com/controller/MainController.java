package com.controller;

import com.domain.Member;
import com.dto.UpdateMemberDto;
import com.repository.MemberRepository;
import com.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final ModelMapper modelMapper;

    @RequestMapping("/")
    public String index(Model model) {
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "index";
    }

    @GetMapping("/members/save")
    public String membersSave() {
        return "members-save";
    }

    @GetMapping("/members/update/{id}")
    public String membersUpdate(@PathVariable("id") Long id, Model model) {
        Member member = memberService.findOne(id);
        model.addAttribute(modelMapper.map(member, UpdateMemberDto.class));

        return "member-update";
    }
}
