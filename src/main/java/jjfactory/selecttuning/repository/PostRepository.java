package jjfactory.selecttuning.repository;

import jjfactory.selecttuning.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
