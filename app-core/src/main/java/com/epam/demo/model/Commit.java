package com.epam.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Collection;
import java.util.Date;

@Data
@Document(indexName = "commits", type = "commit")
public class Commit {

    @Id
    private String id;

    @JsonProperty("short_id")
    private String shortId;

    private String title;

    @JsonProperty("author_name")
    private String authorName;

    @JsonProperty("author_email")
    private String authorEmail;

    @JsonProperty("committer_name")
    private String committerName;

    @JsonProperty("committer_email")
    private String committerEmail;

    @JsonProperty("created_at")
    private Date createdAt;

    private String message;

    @JsonProperty("committed_date")
    private Date committedDate;

    @JsonProperty("authored_date")
    private Date authoredDate;

    @JsonProperty("parent_ids")
    @Field(type = FieldType.Nested)
    private Collection<String> parentIds;

    private CommitStats stats;

    private String status;
}
