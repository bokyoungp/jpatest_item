package jpatest;

import domain.Item;
import domain.Order;
import domain.OrderItem;
import domain.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderCreateTest {
  public static void main(String[] args) {
    // EntityManagerFactory
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpatest");
    // EntityManager
    EntityManager em = emf.createEntityManager();
    // Transaction
    EntityTransaction tx = em.getTransaction();

    tx.begin();
    System.out.println("트랜잭션 시작");
    try {
      bizLogic(em);
      System.out.println("커밋 전");
      tx.commit();
      System.out.println("커밋 후");
    } catch (Exception e) {
      tx.rollback();
    }
  }

  public static void bizLogic(EntityManager em) {
    // 1. user, item entity 가져오기
    User user = em.find(User.class, 1);
    Item item1 = em.find(Item.class, 8L);
    Item item2 = em.find(Item.class, 9L);
    System.out.println("캐시에 없으면 DB에서 가져옴");

    // 2. orderItem 객체 생성
    OrderItem orderItem1 = new OrderItem();
    OrderItem orderItem2 = new OrderItem();

    // 3. 연관관계의 주인 orderItem 에 내용 입력
    orderItem1.setItem(item1);
    orderItem1.setQty(2);
    orderItem1.setPrice(50000);
    System.out.println("주문문서 - 상세 item1 생성");
    em.persist(orderItem1);

    orderItem2.setItem(item2);
    orderItem2.setQty(1);
    orderItem2.setPrice(70000);
    System.out.println("주문문서 - 상세 item2 생성");
    em.persist(orderItem2);

    // 4. order 관련내용 및 양방향 검색을 위한 내용 추가
    Order order = Order.createOrder(user, orderItem1, orderItem2);
    System.out.println("주문문서 생성");
    em.persist(order);

  }
}
