package com.example.apptive19thhjfundbackend.file.entity;

import com.example.apptive19thhjfundbackend.BaseTimeEntity;
import com.example.apptive19thhjfundbackend.post.data.entity.Post;
import com.fasterxml.jackson.databind.node.ContainerNode;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@Table(name = "content_file")
public class ContentFile extends BaseTimeEntity  implements Comparable<ContentFile> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false, name = "\"order\"")
    private int order;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String url;

    @Builder
    public ContentFile(String type, int order, String name, String url) {
        this.type = type;
        this.order = order;
        this.name = name;
        this.url = url;
    }

    public ContentFile setPost(Post post) {
        this.post = post;
        return this;
    }

    @Override
    public int compareTo(ContentFile c) {
        if (this.order > c.getOrder()) {
            return 1;
        } else if (this.order < c.getOrder()) {
            return -1;
        }
        return 0;
    }
}
