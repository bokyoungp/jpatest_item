package jpatest;

import domain.Item;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class ItemJPQLTest {
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

//      List<Item> itemList = em.createQuery("select item from Item item",Item.class)
//          .getResultList();
//      System.out.println("JPQL은 항상 DB에서 가져옴");
      Item item = em.createQuery("select i from Item i where i.id = 1", Item.class).getSingleResult();
      System.out.println(item);
      System.out.println("JPQL은 항상 DB에서 가져옴, 연관관계에 있는 객체 내용까지 같이 가져옴");
      item.setItemName("하나만 변경됨");
      System.out.println(item.getCategory() + ": 카테고리 객체 확인");
      System.out.println(item.getCategory().getCategoryName() + ": 카테고리 객체의 내용 확인");
//      for (int i = 0; i < itemList.size(); i++) {
//        itemList.get(i).setItemName("test - " + i);
//      }
      System.out.println("커밋 전");
      tx.commit();
      System.out.println("커밋 후");
    } catch (Exception e) {
      tx.rollback();
    }
  }
}
