package jpatest;

import domain.Item;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ItemFindTest2 {
  public static void main(String[] args) {
    // EntityManagerFactory
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpatest");
    // EntityManager
    EntityManager em = emf.createEntityManager();
    // Transaction
    EntityTransaction tx = em.getTransaction();

    // Persistence
    // 즉시로딩, 지연로딩 테스트를 위함.
    tx.begin();
    System.out.println("트랜잭션이 시작됨");
    try {
      Item item1 = em.find(Item.class, 1L);
      System.out.println("캐시에 없으면 DB에서 가져올때, 즉시로딩의 경우 item 과 category 를 같이 가져옴");
      System.out.println(item1.getItemName() + " 카테고리의 내용은 참조하지 않음");
      System.out.println(item1.getCategory().getCategoryName() + ": 카테고리의 내용을 참조함");

      tx.commit();
    } catch (Exception e) {
      tx.rollback();
    }
  }
}
