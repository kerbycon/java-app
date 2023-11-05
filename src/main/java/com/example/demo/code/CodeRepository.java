package com.example.demo.code;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CodeRepository extends JpaRepository<Code, Integer> {

    @Query("SELECT c FROM Code c WHERE c.user.id = ?1")
    Optional<Code> findCodeByUserId(Integer userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Code c WHERE c.id = :codeId")
    void deleteCodeById(@Param("codeId") Integer codeId);
}
