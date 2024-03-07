package com.example.myweb.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "article_content")
@Data
public class ArticleContent implements Persistable<Integer> {
    @Id
    private Integer id;

    private String content;

    public ArticleContent(int id, String content) {
        this.id=id;
        this.content=content;
    }

    @Transient
    @JsonIgnore
    private boolean isInsert;

    @Override
    @JsonIgnore
    public boolean isNew() {
        return isInsert;
    }
}
