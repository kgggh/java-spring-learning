package com.example.springdatakeyvalue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @AfterEach
    void clear() {
        memberRepository.deleteAll();
    }

    @DisplayName("map repository에 사용자 정보 저장")
    @Test
    void save() {
        //given
        String name = "테스터";
        Member member = Member.newInstance(name, Member.Gender.MALE);

        //when
        memberRepository.save(member);

        //then
        Optional<Member> findMember = memberRepository.findByName(name);
        assertTrue(findMember.isPresent());
    }

    @DisplayName("map repository에서 저장된 사용자 정보를 조회")
    @Test
    void findAll() {
        //given
        flush();
        String name = "테스터1";

        //when
        Optional<Member> member = memberRepository.findByName(name);
        List<Member> memberList = memberRepository.findAll();

        //then
        assertThat(member.isPresent()).isTrue();
        assertThat(memberList.size()).isEqualTo(5);
    }


    @DisplayName("map repository에서 저장된 사용자 정보 삭제")
    @Test
    public void delete() {
        //given
        List<Member> savedMemberList = flush();
        Member removedMember = savedMemberList.get(0);
        String removedMemberId = savedMemberList.get(1).getId();

        //when
        memberRepository.delete(removedMember);
        memberRepository.deleteById(removedMemberId);

        //then
        List<Member> afterDeleteMemberList = memberRepository.findAll();
        assertThat(savedMemberList.size()).isNotEqualTo(afterDeleteMemberList.size());
    }

    private List<Member> flush() {
        List<Member> memberList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Member member = Member.newInstance("테스터" + i, Member.Gender.MALE);
            if(i % 2 == 0) {
                member.setGender(Member.Gender.MALE);
            } else {
                member.setGender(Member.Gender.FEMALE);
            }
            memberList.add(member);
        }
        memberRepository.saveAll(memberList);
        System.out.println(memberList);
        return memberList;
    }
}