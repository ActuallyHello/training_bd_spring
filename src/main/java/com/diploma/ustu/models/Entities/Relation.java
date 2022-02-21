package com.diploma.ustu.models.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "relation")
@NoArgsConstructor
@Setter
@Getter
public class Relation {

    @Id
    @SequenceGenerator(
            name = "relation_id_sequence",
            sequenceName = "relation_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "relation_id_sequence"
    )
    @Column(name = "id_relation")
    private Long idRelation;

//    @OneToMany(
//        mappedBy = "relation",
//        cascade = CascadeType.ALL,
//        fetch = FetchType.LAZY,
//        orphanRemoval = true
//    )
//    private List<EntityDB> entitiesFrom;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    @JoinColumn(
            name = "id_left_entity"
    )
    private EntityDB entitiesFrom;


    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    @JoinColumn(
            name = "id_right_entity"
    )
    private EntityDB entitiesTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_model")
    private Model model;

    @Column(name = "power", nullable = false, length = 3)
    private String power;

    @Override
    public String toString() {
        return "Relation{" +
                "idRelation=" + idRelation +
                ", entitiesFrom=" + entitiesFrom.getNameEntity() +
                ", entitiesTo=" + entitiesTo.getNameEntity() +
                ", model=" + model +
                ", power='" + power + '\'' +
                '}';
    }
}
