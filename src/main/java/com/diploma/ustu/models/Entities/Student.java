package com.diploma.ustu.models.Entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Student {

    @Id
    @Column(nullable = false, unique = true, length = 6)
    private String studentBook;

    @Column(nullable = false, length = 20)
    private String lastName;

    @Column(nullable = false, length = 20)
    private String firstName;

    @Column(nullable = false, length = 40)
    private String major;

    public Student(String studentBook, String lastName, String firstName, String major) {
        this.studentBook = studentBook;
        this.lastName = lastName;
        this.firstName = firstName;
        this.major = major;
    }

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "student_book",
            referencedColumnName = "studentBook"
    )
    private List<Model> models;

}
