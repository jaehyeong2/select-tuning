package jjfactory.selecttuning.domain.post;

import jjfactory.selecttuning.domain.BaseTimeEntity;
import jjfactory.selecttuning.domain.DeleteStatus;
import jjfactory.selecttuning.domain.Member;
import jjfactory.selecttuning.dto.req.PostCreate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @JoinColumn(name="member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private String title;
    @Lob
    private String content;
    private int likeCount;
    private int viewCount;

    @Enumerated(EnumType.STRING)
    private DeleteStatus deleteStatus;

    @Builder
    public Post(Member member, String title, String content, int likeCount, int viewCount, DeleteStatus deleteStatus) {
        this.member = member;
        this.title = title;
        this.content = content;
        this.likeCount = likeCount;
        this.viewCount = viewCount;
        this.deleteStatus = deleteStatus;
    }

    public static Post create(PostCreate dto, Member member) {
        return Post.builder()
                .member(member)
                .title(dto.getTitle())
                .content(dto.getContent())
                .viewCount(0)
                .likeCount(0)
                .deleteStatus(DeleteStatus.N)
                .build();
    }
}
