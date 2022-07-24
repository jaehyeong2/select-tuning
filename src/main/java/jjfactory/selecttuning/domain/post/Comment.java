package jjfactory.selecttuning.domain.post;


import jjfactory.selecttuning.domain.BaseTimeEntity;
import jjfactory.selecttuning.domain.DeleteStatus;
import jjfactory.selecttuning.domain.Member;
import jjfactory.selecttuning.dto.CommentCreate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    private Comment parent;

    @OneToMany(mappedBy = "parent",orphanRemoval = true)
    private List<Comment> child = new ArrayList<>();

    private String content;

    @Enumerated(EnumType.STRING)
    private DeleteStatus deleteStatus;

    @Builder
    public Comment(Member member, Post post, Comment parent, List<Comment> child, String content, DeleteStatus deleteStatus) {
        this.member = member;
        this.post = post;
        this.parent = parent;
        this.child = child;
        this.content = content;
        this.deleteStatus = deleteStatus;
    }


    public static Comment create(CommentCreate dto, Member member, Post post) {
        return Comment.builder()
                .member(member)
                .post(post)
                .content(dto.getContent())
                .deleteStatus(DeleteStatus.NON_DELETED)
                .build();
    }

    public void addParent(Comment comment){
        this.parent = comment;
        parent.addChild(comment);
    }

    public void addChild(Comment comment){
        this.child.add(comment);
    }
}
