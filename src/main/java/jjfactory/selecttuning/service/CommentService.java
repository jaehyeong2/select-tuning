package jjfactory.selecttuning.service;

import jjfactory.selecttuning.domain.Member;
import jjfactory.selecttuning.domain.post.Comment;
import jjfactory.selecttuning.domain.post.Post;
import jjfactory.selecttuning.dtio.CommentCreate;
import jjfactory.selecttuning.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public void create(CommentCreate dto, Post post, Member member){
        Comment comment = Comment.create(dto, member, post);
        commentRepository.save(comment);
    }
}
