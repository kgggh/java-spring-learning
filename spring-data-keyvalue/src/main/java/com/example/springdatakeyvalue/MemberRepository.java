package com.example.springdatakeyvalue;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends CrudRepository<Member, String> {
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
