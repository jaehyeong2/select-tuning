package jjfactory.selecttuning.service.orders;

import jjfactory.selecttuning.domain.Member;
import jjfactory.selecttuning.domain.orders.Item;
import jjfactory.selecttuning.domain.orders.Order;
import jjfactory.selecttuning.domain.orders.OrderStatus;
import jjfactory.selecttuning.dto.OrderCreate;
import jjfactory.selecttuning.exception.NoEnoughStockException;
import jjfactory.selecttuning.repository.orders.OrderItemRepository;
import jjfactory.selecttuning.repository.orders.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class OrderServiceTest {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    EntityManager em;

    @Test
    @DisplayName("주문 생성 성공")
    void order() {
        //given
        Member lee = createMember();
        Item book = createBook(10);
        int orderCount = 2;

        OrderCreate build = OrderCreate.builder().count(orderCount).itemId(book.getId()).memberId(lee.getId()).build();

        //when
        orderService.order(build);
        Order findOrder = orderRepository.findAll().get(0);
        String findMemberName = findOrder.getMember().getName();
        int totalPrice = findOrder.getTotalPrice();

        //then
        assertThat(orderRepository.count()).isEqualTo(1);
        assertThat(findMemberName).isEqualTo("lee");
        assertThat(totalPrice).isEqualTo(20000);
        assertThat(book.getStockQuantity()).isEqualTo(8);
    }

    @Test
    @DisplayName("주문 생성 실패 : 재고 초과")
    void orderFailed() {
        //given
        Member lee = createMember();

        Item book = createBook(5);
        int orderCount = 6;

        OrderCreate build = OrderCreate.builder().count(orderCount).itemId(book.getId()).memberId(lee.getId()).build();

        //expected
        assertThatThrownBy( () -> {
            orderService.order(build);
        }).isInstanceOf(NoEnoughStockException.class);
    }

    @Test
    @DisplayName("주문취소 성공")
    void orderCancel() {
        //given
        Member lee = createMember();
        Item book = createBook(10);
        int orderCount = 2;
        OrderCreate build = OrderCreate.builder().count(orderCount).itemId(book.getId()).memberId(lee.getId()).build();
        Long orderId = orderService.order(build);
        Order order = orderRepository.findById(orderId).orElseThrow(NoSuchElementException::new);

        //when
        orderService.orderCancel(orderId);

        //then
        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.CANCEL);
        assertThat(book.getStockQuantity()).isEqualTo(10);
    }

    @Test
    @DisplayName("주문취소 실패")
    void orderCancelException() {
        //given
        //when
        //then
    }

    private Item createBook(int stockQuantity) {
        Item book = Item.builder().name("book").price(10000).stockQuantity(stockQuantity).build();
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member lee = Member.builder().name("lee").location("광진구 군자로 11").build();
        em.persist(lee);
        return lee;
    }
}