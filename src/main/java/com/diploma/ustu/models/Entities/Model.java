package com.diploma.ustu.models.Entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "model")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idModel;

    @Column(unique = true, nullable = false)
    private String nameModel;

}
