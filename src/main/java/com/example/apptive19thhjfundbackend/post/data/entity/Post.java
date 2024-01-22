package com.example.apptive19thhjfundbackend.post.data.entity;

import com.example.apptive19thhjfundbackend.BaseTimeEntity;
//import com.example.apptive19thhjfundbackend.user.data.entity.User;
import com.example.apptive19thhjfundbackend.file.entity.ContentFile;
import com.example.apptive19thhjfundbackend.post.data.dto.PostUpdateRequestDto;
import com.example.apptive19thhjfundbackend.user.data.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "post")
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(length = 500, nullable = false)
    private String ticker;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(length = 500, nullable = false)
    private String thumb;

    @Column(nullable = false)
    private int views;

    @Column(nullable = false)
    private int likes;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @OneToMany(fetch = LAZY, mappedBy = "post", cascade = REMOVE)
    private List<ContentFile> contentFiles;

    @Builder
    public Post(String title, String ticker, String content, String thumb, User author) {
        this.title = title;
        this.ticker = ticker;
        this.content = content;
        this.thumb = thumb;
        this.author = author;
    }

    public void update(PostUpdateRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.ticker = requestDto.getTicker();
        this.content = requestDto.getContent();
        this.thumb = requestDto.getThumb();
    }

    public void updateViews() {
        this.views++;
    }
    public void updateLikes(boolean state) {
        if (state) {
            this.likes++;
        }else {
            this.likes--;
        }
    }

}
