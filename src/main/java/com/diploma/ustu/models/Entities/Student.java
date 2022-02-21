package com.diploma.ustu.models.Entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(
        name = "student",
        uniqueConstraints = {
                @UniqueConstraint(name = "student_email_unique", columnNames = "email")
        }
)
@NoArgsConstructor
@Getter
@Setter
public class Student {

    @Id
    @Column(name = "student_book", nullable = false, unique = true, length = 6)
    private String studentBook;

    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Column(name = "major", nullable = false, length = 40)
    private String major;

    @Column(name = "email", nullable = false, length = 60, unique = true)
    private String email;

    @OneToMany(
            mappedBy = "student",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<Model> models;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_userdb")
    private User user;

    public Student(String studentBook, String lastName, String firstName, String major, String email) {
        this.studentBook = studentBook;
        this.lastName = lastName;
        this.firstName = firstName;
        this.major = major;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(getStudentBook(), student.getStudentBook())
                && Objects.equals(getLastName(), student.getLastName())
                && Objects.equals(getFirstName(), student.getFirstName())
                && Objects.equals(getMajor(), student.getMajor())
                && Objects.equals(getEmail(), student.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudentBook(), getLastName(), getFirstName());
    }
}
