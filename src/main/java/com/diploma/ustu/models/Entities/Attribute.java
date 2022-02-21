package com.diploma.ustu.models.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "attribute")
@NoArgsConstructor
@Getter
@Setter
public class Attribute {

    @Id
    @SequenceGenerator(
            name = "attribute_id_sequence",
            sequenceName = "attribute_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "attribute_id_sequence"
    )
    @Column(name = "id_attribute")
    private Long idAttribute;

    @Column(name = "name_attribute", length = 60, nullable = false)
    private String nameAttribute;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_entity")
    private EntityDB entityDB;

    public Attribute(String nameAttribute) {
        this.nameAttribute = nameAttribute;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "idAttribute=" + idAttribute +
                ", nameAttribute='" + nameAttribute + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Attribute)) return false;
        Attribute attribute = (Attribute) o;
        return Objects.equals(getIdAttribute(), attribute.getIdAttribute()) && Objects.equals(getNameAttribute(), attribute.getNameAttribute());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdAttribute(), getNameAttribute());
    }
}
