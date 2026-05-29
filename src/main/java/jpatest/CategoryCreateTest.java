package jpatest;

import domain.Category;
import domain.Item;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class CategoryCreateTest {
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
      Category category1 = new Category();
      category1.setCategoryName("의류");

      Category category2 = new Category();
      category2.setCategoryName("신발");

      Category category3 = new Category();
      category3.setCategoryName("도서");

      Category category4 = new Category();
      category4.setCategoryName("완구");
      System.out.println("비영속상태의 객체가 생성됨");

      em.persist(category1);
      System.out.println("엔티티의 상태가 영속상태로 변경됨");

      em.persist(category2);
      System.out.println("엔티티의 상태가 영속상태로 변경됨");

      em.persist(category3);
      System.out.println("엔티티의 상태가 영속상태로 변경됨");

      em.persist(category4);
      System.out.println("엔티티의 상태가 영속상태로 변경됨");

      System.out.println("커밋 전");
      tx.commit();
      System.out.println("커밋 후");
    } catch (Exception e) {
      tx.rollback();
    }
  }
}
