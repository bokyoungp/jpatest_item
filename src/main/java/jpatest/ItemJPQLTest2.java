package jpatest;

import domain.Item;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class ItemJPQLTest2 {
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
    try { // 하나의 item 만 JPQL 로 가져와서 조회하는 예제

      List<Item> itemList = em.createQuery("select item from Item item",Item.class)
          .getResultList();

      System.out.println("JPQL은 항상 DB에서 가져옴, 연관관계에 있는 객체 내용까지 같이 가져옴");

      for (int i = 0; i < itemList.size(); i++) {
        Item item = itemList.get(i);
        System.out.println(item.getCategory().getCategoryName());
      }


      tx.commit();

    } catch (Exception e) {
      tx.rollback();
    }
  }
}
