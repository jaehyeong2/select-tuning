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
    private String content;
    private LocalDateTime createDate;
    private List<CommentRes> childComments;

    @Builder
    public CommentRes(Long commentId, String content, LocalDateTime createDate, List<CommentRes> childComments) {
        this.commentId = commentId;
        this.content = content;
        this.createDate = createDate;
        this.childComments = childComments;
    }
    @Builder
    public CommentRes(Comment comment) {
        this.commentId = comment.getId();
        this.content = comment.getContent();
        this.createDate = comment.getCreateDate();
        this.childComments = comment.getChild().stream().map(CommentRes::new).collect(Collectors.toList());
    }
}
