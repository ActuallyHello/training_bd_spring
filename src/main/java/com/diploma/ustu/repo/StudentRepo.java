package com.diploma.ustu.repo;

import com.diploma.ustu.models.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface StudentRepo extends JpaRepository<Student, String> {

    List<Student> findByMajor(String major);

    Student findByStudentBook(String student_book);

    List<Student> findByFirstName(String first_name);

    List<Student> findByFirstNameAndLastName(String first_name, String last_name);

    //JPQL
    @Query("select s from Student s where s.studentBook = ?1")
    Student getStudentByStudentBook(String student_book);

    @Query("select s from Student s")
    List<Student> getStudentsByJPQL();

    @Modifying
    @Transactional
    @Query(
            value = "update student set first_name = ?1 where student_book = ?2",
            nativeQuery = true
    )
    int updateStudentByStudentBook(String firstName, String student_book);
}
