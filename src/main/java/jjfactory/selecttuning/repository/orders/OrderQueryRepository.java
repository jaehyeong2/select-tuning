package jjfactory.selecttuning.repository.orders;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jjfactory.selecttuning.domain.QMember;
import jjfactory.selecttuning.domain.orders.OrderStatus;
import jjfactory.selecttuning.domain.orders.QOrder;
import jjfactory.selecttuning.dto.req.OrderSearch;
import jjfactory.selecttuning.dto.res.OrderRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static jjfactory.selecttuning.domain.QMember.*;
import static jjfactory.selecttuning.domain.orders.QOrder.*;

@RequiredArgsConstructor
@Repository
public class OrderQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<OrderRes> findOrders(OrderSearch orderSearch){
        return queryFactory.select(Projections.constructor(OrderRes.class, order))
                .from(order)
                .where(eqMemberName(orderSearch.getMemberName()),
                        eqOrderStatus(orderSearch.getOrderStatus()))
                .fetch();
    }

    public List<OrderRes> findOrders2(OrderSearch orderSearch){
        return queryFactory.select(Projections.constructor(OrderRes.class, order, member))
                .from(order)
                .innerJoin(member)
                .on(order.member.id.eq(member.id)).fetchJoin()
                .where(eqMemberName(orderSearch.getMemberName()),
                        eqOrderStatus(orderSearch.getOrderStatus()))
                .fetch();
    }

    private BooleanExpression eqOrderStatus(OrderStatus orderStatus){
        if(orderStatus == null) return null;
        return order.orderStatus.eq(orderStatus);
    }

    private BooleanExpression eqMemberName(String memberName){
        if(!StringUtils.hasText(memberName)){
            return null;
        }
        return order.member.name.eq(memberName);
    }
}
