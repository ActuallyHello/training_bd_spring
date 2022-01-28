package com.diploma.ustu.models.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    @Column(name = "name_attribute", length = 50, nullable = false)
    private String nameAttribute;

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
}
