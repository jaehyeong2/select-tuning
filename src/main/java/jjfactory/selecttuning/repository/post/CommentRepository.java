package jjfactory.selecttuning.repository.post;

import jjfactory.selecttuning.domain.post.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
