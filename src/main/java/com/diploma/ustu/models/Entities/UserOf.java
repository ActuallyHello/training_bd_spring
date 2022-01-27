package com.diploma.ustu.models.Entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(
        name = "userof",
        uniqueConstraints = {
                @UniqueConstraint(name = "user_email_unique", columnNames = "email")
        }
)
@Data
//@AllArgsConstructor
@NoArgsConstructor
@ToString
//@Builder
public class UserOf {

    @Id
    @SequenceGenerator(
            name = "userof_id_sequence",
            sequenceName = "userof_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "userof_id_sequence"
    )
    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false, length = 20)
    private String password;
    @Column(name = "user_role", nullable = false)
    private String user_role;

    public UserOf(String email, String password, String user_role) {
        this.email = email;
        this.password = password;
        this.user_role = user_role;
    }
}
