package jjfactory.selecttuning.service;

import jjfactory.selecttuning.domain.Member;
import jjfactory.selecttuning.domain.post.Comment;
import jjfactory.selecttuning.domain.post.Post;
import jjfactory.selecttuning.dto.req.CommentCreate;
import jjfactory.selecttuning.dto.res.CommentRes;
import jjfactory.selecttuning.repository.post.CommentRepository;
import jjfactory.selecttuning.repository.post.MemberJpaRepository;
import jjfactory.selecttuning.repository.post.PostRepository;
import jjfactory.selecttuning.service.post.CommentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class CommentServiceTest {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Autowired
    CommentService commentService;

    @Test
    @DisplayName("댓글 조회 페이징")
    void findComments(){
        //given
        Member lee = Member.builder().name("lee").build();
        Post post = Post.builder().content("content").build();
        memberJpaRepository.save(lee);
        postRepository.save(post);

        for(int i =0; i<30; i++){
            Comment com = Comment.builder().content("content" + i).post(post).member(lee).build();
            commentRepository.save(com);
        }

        Pageable pageable = PageRequest.of(1,10);
        Page<CommentRes> comments = commentService.findComments(pageable, post.getId());
        assertThat(commentRepository.count()).isEqualTo(30);
        assertThat(comments.getSize()).isEqualTo(10);
    }

    @Test
    void create() {
        //given
        Member lee = Member.builder().name("lee").build();
        Post post = Post.builder().content("content").build();
        memberJpaRepository.save(lee);
        postRepository.save(post);

        CommentCreate comment = CommentCreate.builder().content("hihihihi").build();
        commentService.create(comment,post,lee);

        //when
        List<Comment> comments = commentRepository.findAll();
        List<String> postContents = comments.stream().map(c -> c.getContent())
                .collect(Collectors.toList());

        //then
        assertThat(commentRepository.count()).isEqualTo(1);
        assertThat(postContents).contains("hihihihi");
    }
}