package jjfactory.selecttuning.dto;

import jjfactory.selecttuning.domain.post.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class CommentRes {
    private Long commentId;
    private Long parentId;
    private String content;
    private LocalDateTime createDate;

    public CommentRes(Long commentId, Long parentId, String content, LocalDateTime createDate) {
        this.commentId = commentId;
        this.parentId = parentId;
        this.content = content;
        this.createDate = createDate;
    }

    @Builder
    public CommentRes(Comment comment) {
//        this.parentId = comment.getParent().getId();
        this.commentId = comment.getId();
        this.content = comment.getContent();
        this.createDate = comment.getCreateDate();
    }
}
