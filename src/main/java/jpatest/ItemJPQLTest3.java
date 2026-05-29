package jpatest;

import domain.Item;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class ItemJPQLTest3 {
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
    try { // N + 1 쿼리 발생을 해결하기 위한 fetch join

//      List<Item> itemList = em.createQuery("select item from Item item join fetch item.category",Item.class)
//          .getResultList();

            List<Item> itemList = em.createQuery("select item from Item item left join fetch item.category",Item.class)
          .getResultList();

      System.out.println("JPQL은 항상 DB에서 가져옴, 연관관계에 있는 객체 내용까지 같이 가져옴");

      for (int i = 0; i < itemList.size(); i++) {
        Item item = itemList.get(i);
        System.out.print(item.getItemId() + ":" + item.getItemName() + ":");
        if(item.getCategory() != null) System.out.println(item.getCategory().getCategoryName());
      }


      tx.commit();

    } catch (Exception e) {
      tx.rollback();
    }
  }
}
