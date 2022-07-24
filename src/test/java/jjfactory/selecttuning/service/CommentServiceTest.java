package jjfactory.selecttuning.service;

import jjfactory.selecttuning.domain.Member;
import jjfactory.selecttuning.domain.post.Comment;
import jjfactory.selecttuning.domain.post.Post;
import jjfactory.selecttuning.dto.CommentCreate;
import jjfactory.selecttuning.repository.CommentRepository;
import jjfactory.selecttuning.repository.MemberJpaRepository;
import jjfactory.selecttuning.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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