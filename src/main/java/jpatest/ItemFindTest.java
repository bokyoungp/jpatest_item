package jpatest;

import domain.Item;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ItemFindTest {
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
      Item item2 = em.find(Item.class, 1L);
      System.out.println("캐시에 있는 내용을 가져옴");
      System.out.println("DB에서 가져온 내용과 캐시에서 가져온 내용이 동일한가 ?" + (item1 == item2));
      tx.commit();
    } catch (Exception e) {
      tx.rollback();
    }
  }
}
