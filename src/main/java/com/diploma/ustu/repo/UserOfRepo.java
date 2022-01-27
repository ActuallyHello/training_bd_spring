package com.diploma.ustu.repo;

import com.diploma.ustu.models.Entities.UserOf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface UserOfRepo extends JpaRepository<UserOf, Long> {


    List<UserOf> findAllByEmail(String email);

    @Query(
            value = "select email from userof where id_user = ?1",
            nativeQuery = true
    )
    String findEmailByIdUser(Long Id);

    @Transactional
    void deleteByIdUserAndEmail(Long Id, String email);

    @Transactional
    void deleteByEmail(String email);
}