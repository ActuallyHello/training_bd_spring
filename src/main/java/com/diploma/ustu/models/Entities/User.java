package com.diploma.ustu.models.Entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_db")
@NoArgsConstructor
@Setter
@Getter
public class User {

    @Id
    @SequenceGenerator(
            name = "userdb_id_sequence",
            sequenceName = "userdb_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "userdb_id_sequence"
    )
    @Column(name = "id_userdb")
    private Long idUser;

    @Column(name = "password", nullable = false, length = 20)
    private String password;
    @Column(name = "user_role", nullable = false)
    private String user_role;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<Student> studentList;

    public User(String password, String user_role) {
        this.password = password;
        this.user_role = user_role;
    }
}
