package com.example.springtdd;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ExampleServiceTest {

    @Mock
    ExampleRepository exampleRepository;

    @InjectMocks
    ExampleService exampleService;

    @DisplayName("전체멤버 조회시 5인 이상이면 통과")
    @Test
    void getMemberList() {
        //given
        List<Member> memberList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Member member = new Member();
            member.setName("테스터" + i);
            member.setAge(4 * i);
            member.setBirthday(LocalDate.now().minusYears((long)i));
            member.setAdult(true);
            member.setGender(Member.Gender.MALE);
            memberList.add(member);
        }
        given(exampleRepository.findAll()).willReturn(memberList);

        //when
        List<Member> result = exampleService.getMemberList();

        //then
        assertThat(result.size()).isEqualTo(5);
        verify(exampleRepository, times(1)).findAll();
        result.forEach(System.out::println);
    }

    @DisplayName("Easy Random으로 구성된 멤버조회")
    @Test
    void getMemberListByEasyRandom() {
        //given
        EasyRandom easyRandom = new EasyRandom();
        List<Member> memberList = easyRandom.objects(Member.class, 5)
                .collect(Collectors.toList());
        given(exampleRepository.findAll()).willReturn(memberList);

        //when
        List<Member> result = exampleService.getMemberList();

        //then
        assertThat(result.size()).isEqualTo(5);
        verify(exampleRepository, times(1)).findAll();
        result.forEach(System.out::println);
    }
}