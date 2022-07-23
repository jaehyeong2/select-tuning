package jjfactory.selecttuning.dtio;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CommentCreate {
    private String content;


    @Builder
    public CommentCreate(String content) {
        this.content = content;
    }
}
