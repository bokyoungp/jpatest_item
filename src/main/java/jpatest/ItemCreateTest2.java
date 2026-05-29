package jpatest;

import domain.Category;
import domain.Item;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ItemCreateTest2 {
  public static void main(String[] args) {
    // EntityManagerFactory
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpatest");
    // EntityManager
    EntityManager em = emf.createEntityManager();
    // Transaction
    EntityTransaction tx = em.getTransaction();

    // Persistence
    tx.begin();
    System.out.println("트랜잭션이 시작됨");
    try {
      Category cat1 = em.find(Category.class, 3);
      Category cat2 = em.find(Category.class, 4);
      Category cat3 = em.find(Category.class, 5);
      Category cat4 = em.find(Category.class, 6);
      System.out.println("category 정보를 DB 에서 가져옴.");

      Item item1 = new Item();
      item1.setItemName("선풍기 -1");
      item1.setCategory(cat1);

      Item item2 = new Item();
      item2.setItemName("에어컨 - 1");
      item2.setCategory(cat2);

      Item item3 = new Item();
      item3.setItemName("냉장고 - 1");
      item3.setCategory(cat3);

      Item item4 = new Item();
      item4.setItemName("냉장고 - 1");
      item4.setCategory(cat4);

      System.out.println("비영속상태의 item 객체가 생성됨");
      em.persist(item1);
      System.out.println("엔티티1의 상태가 영속상태로 변경됨");
      em.persist(item2);
      System.out.println("엔티티2의 상태가 영속상태로 변경됨");
      em.persist(item3);
      System.out.println("엔티티3의 상태가 영속상태로 변경됨");
      em.persist(item4);
      System.out.println("엔티티4의 상태가 영속상태로 변경됨");

      System.out.println("커밋 전");
      tx.commit();
      System.out.println("커밋 후");
    } catch (Exception e) {
      tx.rollback();
    }
  }
}
