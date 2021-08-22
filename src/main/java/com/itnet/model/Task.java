package com.itnet.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Task {
    private int id;
    private String title;
    private String description;
    private Date admissionDate;
    private TaskType taskType;
    private int personId;
    private Person person;
}
