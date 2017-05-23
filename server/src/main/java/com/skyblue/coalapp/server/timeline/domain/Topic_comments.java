package com.skyblue.coalapp.server.timeline.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by yao on 2017/5/21.
 */
@Entity
@Table(name = "topic_comments")
@Setter
@Getter
public class Topic_comments {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private Integer user_id;

    @Column
    private Integer topic_id;

    @Column
    private Integer parent_id;

    @Column(length = 10000)
    private String content;

}
