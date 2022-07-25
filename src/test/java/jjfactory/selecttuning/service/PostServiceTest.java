package jjfactory.selecttuning.service;

import jjfactory.selecttuning.domain.Member;
import jjfactory.selecttuning.domain.post.Post;
import jjfactory.selecttuning.dto.PostCreate;
import jjfactory.selecttuning.repository.post.MemberJpaRepository;
import jjfactory.selecttuning.repository.post.PostRepository;
import jjfactory.selecttuning.service.post.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;


@Transactional
@SpringBootTest
class PostServiceTest {

    @Autowired
    PostRepository postRepository;
    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Autowired
    PostService postService;

    @Test
    @DisplayName("글 작성 성공")
    void create() {
        //given
        Member lee = Member.builder().name("lee").build();
        memberJpaRepository.save(lee);

        PostCreate post1 = PostCreate.builder().content("content1").build();
        Post post = Post.create(post1, lee);
        postRepository.save(post);

        //when
        List<Post> posts = postRepository.findAll();
        List<String> contents = posts.stream().map(p -> p.getContent()).collect(Collectors.toList());

        //expected
        assertThat(postRepository.count()).isEqualTo(1);
        assertThat(contents).contains("content1");
    }
}