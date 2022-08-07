package jjfactory.selecttuning.repository.post;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jjfactory.selecttuning.domain.post.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static jjfactory.selecttuning.domain.post.QComment.*;

@RequiredArgsConstructor
@Repository
public class CommentQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Page<Comment> findComments(Pageable pageable,Long postId){
        List<Comment> comments = queryFactory.selectFrom(comment)
                .leftJoin(comment.parent)
                .where(comment.post.id.eq(postId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        int total = queryFactory.selectFrom(comment)
                .where(comment.post.id.eq(postId)).fetch().size();

        return new PageImpl<>(comments,pageable,total);
    }
}
