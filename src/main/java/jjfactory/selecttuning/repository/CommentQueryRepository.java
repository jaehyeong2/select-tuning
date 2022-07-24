package jjfactory.selecttuning.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jjfactory.selecttuning.domain.post.QComment;
import jjfactory.selecttuning.dto.CommentRes;
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

    public Page<CommentRes> findComments(Pageable pageable,Long postId){
        List<CommentRes> comments = queryFactory.select(Projections.constructor(CommentRes.class, comment))
                .from(comment)
                .where(comment.post.id.eq(postId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        int total = queryFactory.select(Projections.constructor(CommentRes.class, comment))
                .from(comment)
                .where(comment.post.id.eq(postId)).fetch().size();

        return new PageImpl<>(comments,pageable,total);
    }
}
