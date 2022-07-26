package jjfactory.selecttuning.service.orders;

import jjfactory.selecttuning.domain.Member;
import jjfactory.selecttuning.domain.orders.*;
import jjfactory.selecttuning.dto.req.OrderCreate;
import jjfactory.selecttuning.dto.req.OrderItemCreate;
import jjfactory.selecttuning.dto.req.OrderSearch;
import jjfactory.selecttuning.dto.res.OrderRes;
import jjfactory.selecttuning.repository.orders.ItemRepository;
import jjfactory.selecttuning.repository.orders.OrderQueryRepository;
import jjfactory.selecttuning.repository.orders.OrderRepository;
import jjfactory.selecttuning.repository.post.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Transactional
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final OrderQueryRepository orderQueryRepository;

    public Long order(OrderCreate dto){
        Member member = memberRepository.find(dto.getMemberId());
        Item item = itemRepository.findById(dto.getItemId()).orElseThrow(NoSuchElementException::new);
        Delivery delivery = Delivery.create(member);

        OrderItem orderItem = OrderItem.create(new OrderItemCreate(item.getPrice(), dto.getCount()), item);

        Order order = Order.create(member, delivery, orderItem);
        orderRepository.save(order);    // order엔티티의 orderItem,delivery에 cascadeAll 걸어줘야함. 난 아직 안검
        return order.getId();
    }

    public void orderCancel(Long orderId){
        Order order = orderRepository.findById(orderId).orElseThrow(NoSuchElementException::new);
        order.orderCancel();
    }

    public List<OrderRes> findOrders(OrderSearch orderSearch){
        return orderQueryRepository.findOrders(orderSearch);
    }

    public List<OrderRes> findOrders2(OrderSearch orderSearch){
        return orderQueryRepository.findOrders2(orderSearch);
    }

}
