package domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
  private long itemId;
  private String itemName;

  // LAZY : 지연로딩, 연관관계에 있는 객체의 필드를 참조할 경우에만 SQL 이 실행됨.
  @ManyToOne(fetch = FetchType.LAZY) // 즉시로딩, 연관관계에 있는 객체를 항상 같이 SQL 로 가져옴
  @JoinColumn(name = "category_id") // FK
  private Category category;

}
