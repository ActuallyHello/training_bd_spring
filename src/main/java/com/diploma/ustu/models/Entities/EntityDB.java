package com.diploma.ustu.models.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "entityof")
@NoArgsConstructor
@Getter
@Setter
public class EntityDB {

    @Id
    @SequenceGenerator(
            name = "entity_id_sequence",
            sequenceName = "entity_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "entity_id_sequence"
    )
    @Column(name = "id_entity")
    private Long idEntity;

    @Column(name = "name_entity", length = 60, nullable = false)
    private String nameEntity;


    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    @JoinColumn(
            name = "id_entity"
    )
    private List<Attribute> attributes;

    public EntityDB(String nameEntity) {
        this.nameEntity = nameEntity;
    }

    @Override
    public String toString() {
        return "EntityOf{" +
                "idEntity=" + idEntity +
                ", nameEntity='" + nameEntity + '\'' +
                '}';
    }
}
