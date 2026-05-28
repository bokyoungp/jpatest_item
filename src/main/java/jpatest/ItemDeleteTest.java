package jpatest;

import domain.Item;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ItemDeleteTest {
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
      Item item2 = em.find(Item.class, 2L);
      Item item3 = em.find(Item.class, 3L);
      System.out.println("캐시에 없으면 DB에서 가져옴 == 영속상태");
      System.out.println(item1);
      em.remove(item1);
      em.remove(item2);
      em.remove(item3);
      System.out.println("영속상태에서 삭제 상태로 변경됨");
      System.out.println("커밋 전");
      tx.commit();
      System.out.println("커밋 후");
    } catch (Exception e) {
      tx.rollback();
    }
  }
}
