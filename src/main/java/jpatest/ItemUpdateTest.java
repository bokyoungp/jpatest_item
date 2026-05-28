package jpatest;

import domain.Item;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ItemUpdateTest {
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
      Item item1 = em.find(Item.class, 1L);
      System.out.println("캐시에 없으면 DB에서 가져옴");
      item1.setItemName("선풍기 - 수정");
      Item item2 = em.find(Item.class, 2L);
      System.out.println("캐시에 없으면 DB에서 가져옴");
      item2.setItemName("에어컨 - 수정");
      Item item3 = em.find(Item.class, 3L);
      System.out.println("캐시에 없으면 DB에서 가져옴");
      item3.setItemName("냉장고 - 수정");
      em.persist(item1);
      em.persist(item2);
      em.persist(item3);
      System.out.println("수정된 내용을 영속성 컨텍스트에 반영함");
      System.out.println("커밋 전");
      tx.commit();
      System.out.println("커밋 후");
    } catch (Exception e) {
      tx.rollback();
    }
  }
}
