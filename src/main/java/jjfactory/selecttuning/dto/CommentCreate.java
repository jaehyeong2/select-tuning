package jjfactory.selecttuning.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CommentCreate {
    private String content;
    private Long parentId;

    @Builder
    public CommentCreate(String content, Long parentId) {
        this.content = content;
        this.parentId = parentId;
    }
}
