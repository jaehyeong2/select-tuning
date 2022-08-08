package jjfactory.selecttuning.service.orders;

import jjfactory.selecttuning.domain.Member;
import jjfactory.selecttuning.domain.orders.Item;
import jjfactory.selecttuning.domain.orders.Order;
import jjfactory.selecttuning.domain.orders.OrderStatus;
import jjfactory.selecttuning.dto.req.OrderCreate;
import jjfactory.selecttuning.dto.req.OrderSearch;
import jjfactory.selecttuning.dto.res.OrderRes;
import jjfactory.selecttuning.exception.NoEnoughStockException;
import jjfactory.selecttuning.repository.orders.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

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
    @DisplayName("주문 조회")
    void findOrders(){
        //given
        Member lee = createMember("lee");
        Member kim = createMember("kim");
        Item book = createItem(10, "book");
        Item cookie = createItem(5, "cookie");
        int orderCount = 2;

        OrderCreate order1 = OrderCreate.builder().count(orderCount).itemId(book.getId()).memberId(lee.getId()).build();
        OrderCreate order2 = OrderCreate.builder().count(orderCount).itemId(cookie.getId()).memberId(kim.getId()).build();

        orderService.order(order1);
        orderService.order(order2);

        //when
        List<OrderRes> noParams = orderService.findOrders(new OrderSearch());
        List<OrderRes> orderOfKim = orderService.findOrders(OrderSearch.builder().memberName("kim").build());
        List<OrderRes> canceledOrder = orderService.findOrders(OrderSearch.builder().orderStatus(OrderStatus.CANCEL).build());

        //then
        assertThat(noParams.size()).isEqualTo(2);
        assertThat(orderOfKim.size()).isEqualTo(1);
        assertThat(canceledOrder).isEmpty();
    }

    @Test
    @DisplayName("주문 조회2")
    void findOrders2(){
        //given
        Member lee = createMember("lee");
        Member kim = createMember("kim");
        Item book = createItem(10, "book");
        Item cookie = createItem(5, "cookie");
        int orderCount = 2;

        OrderCreate order1 = OrderCreate.builder().count(orderCount).itemId(book.getId()).memberId(lee.getId()).build();
        OrderCreate order2 = OrderCreate.builder().count(orderCount).itemId(cookie.getId()).memberId(kim.getId()).build();

        orderService.order(order1);
        orderService.order(order2);

        //when
        List<OrderRes> orderOfKim = orderService.findOrders2(OrderSearch.builder().memberName("kim").build());

        //then
        assertThat(orderOfKim.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("주문 생성 성공")
    void order() {
        //given
        Member lee = createMember("lee");
        Item book = createItem(10, "book");
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
        Member lee = createMember("lee");

        Item book = createItem(5, "book");
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
        Member lee = createMember("lee");
        Item book = createItem(10, "book");
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
    @DisplayName("주문취소 실패 : 배송완료되면 취소불가")
    void orderCancelException() {
        //given
        Member lee = createMember("lee");
        Item book = createItem(10, "book");
        int orderCount = 2;
        OrderCreate build = OrderCreate.builder().count(orderCount).itemId(book.getId()).memberId(lee.getId()).build();
        Long orderId = orderService.order(build);
        Order order = orderRepository.findById(orderId).orElseThrow(NoSuchElementException::new);

        order.getDelivery().compDelivery();

        //expected
        assertThatThrownBy(() ->{
            orderService.orderCancel(orderId);
        }).isInstanceOf(IllegalStateException.class);
    }

    private Item createItem(int stockQuantity, String itemName) {
        Item book = Item.builder().name(itemName).price(10000).stockQuantity(stockQuantity).build();
        em.persist(book);
        return book;
    }

    private Member createMember(String name) {
        Member lee = Member.builder().name(name).location("광진구 군자로 11").build();
        em.persist(lee);
        return lee;
    }
}