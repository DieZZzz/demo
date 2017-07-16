package com.epam.demo.model;

import lombok.Data;

@Data
public class CommitStats {

    private Integer additions;
    private Integer deletions;
    private Integer total;
}
