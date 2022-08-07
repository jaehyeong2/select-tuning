package jjfactory.selecttuning.repository.orders;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jjfactory.selecttuning.domain.orders.OrderStatus;
import jjfactory.selecttuning.domain.orders.QOrder;
import jjfactory.selecttuning.dto.req.OrderSearch;
import jjfactory.selecttuning.dto.res.OrderRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static jjfactory.selecttuning.domain.orders.QOrder.*;

@RequiredArgsConstructor
@Repository
public class OrderQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<OrderRes> findOrders(OrderSearch orderSearch){
        return queryFactory.select(Projections.constructor(OrderRes.class, order))
                .from(order)
                .where(eqOrderStatus(orderSearch.getOrderStatus()),
                        eqMemberName(orderSearch.getMemberName()))
                .fetch();
    }

    private BooleanExpression eqOrderStatus(OrderStatus orderStatus){
        if(StringUtils.hasText(String.valueOf(orderStatus))){
            return order.orderStatus.eq(orderStatus);
        }
        else return null;
    }

    private BooleanExpression eqMemberName(String memberName){
        if(StringUtils.hasText(memberName)){
            return order.member.name.eq(memberName);
        }
        else return null;
    }
}
