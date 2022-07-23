package jjfactory.selecttuning.service;


import jjfactory.selecttuning.domain.Member;
import jjfactory.selecttuning.domain.post.Post;
import jjfactory.selecttuning.dtio.PostCreate;
import jjfactory.selecttuning.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class PostService {
    private final PostRepository postRepository;

    public void create(PostCreate dto, Member member){
        Post.create(dto,member);
    }
}
