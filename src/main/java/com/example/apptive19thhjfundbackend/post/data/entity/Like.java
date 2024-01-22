package com.example.apptive19thhjfundbackend.post.data.entity;

import com.example.apptive19thhjfundbackend.BaseTimeEntity;
import com.example.apptive19thhjfundbackend.user.data.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "likes")
public class Like extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(nullable = false)
    private boolean state;

    @Builder
    public Like(Post post, User user) {
        this.post = post;
        this.user = user;
        this.state = false;
    }

    public boolean update() {
        this.state = !this.state;
        return this.state;
    }
}
