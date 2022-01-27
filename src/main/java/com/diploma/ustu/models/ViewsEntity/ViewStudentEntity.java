package com.diploma.ustu.models.ViewsEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder
@ToString
public class ViewStudentEntity {

    private Long id_model;
    private String last_name;
    private String name_model;
}
