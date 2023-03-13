package com.example.springtdd.natural;


import java.util.List;

public class ExampleService {
    private final ExampleRepository exampleRepository;

    public ExampleService(ExampleRepository exampleRepository) {
        this.exampleRepository = exampleRepository;
    }

    public List<Member> getMemberList() {
        List<Member> memberList = exampleRepository.findAll();
        if(memberList.size() < 5) {
            throw new RuntimeException("가입한 멤버수가 5인 이하네요.. 분발하세요");
        }
        return memberList;
    }
}
