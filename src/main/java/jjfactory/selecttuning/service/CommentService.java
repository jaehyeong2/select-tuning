package jjfactory.selecttuning.service;

import jjfactory.selecttuning.domain.Member;
import jjfactory.selecttuning.domain.post.Comment;
import jjfactory.selecttuning.domain.post.Post;
import jjfactory.selecttuning.dto.CommentCreate;
import jjfactory.selecttuning.dto.CommentRes;
import jjfactory.selecttuning.repository.CommentQueryRepository;
import jjfactory.selecttuning.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentQueryRepository commentQueryRepository;

    @Transactional(readOnly = true)
    public Page<CommentRes> findComments(Pageable pageable,Long postId){
        Page<Comment> comments = commentQueryRepository.findComments(pageable, postId);
        return comments.map(CommentRes::new);
    }

    public void create(CommentCreate dto, Post post, Member member){
        Comment comment = Comment.create(dto, member, post);
        if(dto.getParentId() != null && !dto.getParentId().equals(0L)){
            Comment parent = commentRepository.findById(dto.getParentId()).orElseThrow(NoSuchElementException::new);
            comment.addParent(parent);
        }

        commentRepository.save(comment);
    }


}
