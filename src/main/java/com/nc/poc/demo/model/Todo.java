package com.nc.poc.demo.model;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Todo {
    private Integer id;
    private String name;
    private Date date;
    private Boolean isDone;
}
