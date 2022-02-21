package com.diploma.ustu.models.Entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "model")
@NoArgsConstructor
@Setter
@Getter
public class Model {

    @Id
    @SequenceGenerator(
            name = "model_id_sequence",
            sequenceName = "model_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "model_id_sequence"
    )
    @Column(name = "id_model")
    private Long idModel;

    @Column(name = "name_model", length = 120, unique = true, nullable = false)
    private String nameModel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_book")
    private Student student;

    @OneToMany(
            mappedBy = "model",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<Relation> relations;

    public Model(String nameModel) {
        this.nameModel = nameModel;
    }

    @Override
    public String toString() {
        return "Model{" +
                "idModel=" + idModel +
                ", nameModel='" + nameModel + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Model)) return false;
        Model model = (Model) o;
        return Objects.equals(getIdModel(), model.getIdModel()) && Objects.equals(getNameModel(), model.getNameModel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdModel(), getNameModel());
    }
}
