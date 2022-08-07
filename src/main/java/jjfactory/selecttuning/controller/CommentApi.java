package jjfactory.selecttuning.controller;

import jjfactory.selecttuning.dto.res.CommentRes;
import jjfactory.selecttuning.service.post.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/comments")
@RestController
public class CommentApi {
    private final CommentService commentService;

    public Page<CommentRes> findComments(@PageableDefault Pageable pageable,Long postId){
        return commentService.findComments(pageable,postId);
    }
}
