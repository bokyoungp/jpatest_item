package jpatest;

import domain.Item;
import domain.Order;
import domain.OrderItem;

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

      tx.commit();
    } catch (Exception e) {
      tx.rollback();
    }
  }

  public static void bizLogic(EntityManager em) {
    Item item = em.find(Item.class, 1L);
    System.out.println("캐시에 없으면 DB에서 가져옴");


    OrderItem orderItem = new OrderItem();
    orderItem.setItem(item);
    orderItem.setQty(1);
    orderItem.setPrice(1000);

    em.persist(orderItem);

    Order order = new Order();
    order.setOrderDateTime(LocalDateTime.now());
    order.setTotalQty(1);
    order.setTotalPrice(1000);
    List<OrderItem> itemList = new ArrayList<>();
    itemList.add(orderItem);
    order.setOrderItemList(itemList);

    em.persist(order);

    //
    orderItem.setOrder(order);

  }
}
