package com.diploma.ustu.models.ViewsEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@Builder
public class ViewModelEntity {

    private String model;
    private String student;
    private String major;
}
