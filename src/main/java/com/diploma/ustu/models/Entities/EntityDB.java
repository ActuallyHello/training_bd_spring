package com.diploma.ustu.models.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "entity")
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
            mappedBy = "entityDB",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<Attribute> attributes;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_relation")
//    private Relation relation;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntityDB)) return false;
        EntityDB entityDB = (EntityDB) o;
        return Objects.equals(getIdEntity(), entityDB.getIdEntity()) && Objects.equals(getNameEntity(), entityDB.getNameEntity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdEntity(), getNameEntity());
    }
}
