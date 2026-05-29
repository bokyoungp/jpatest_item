package domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class OrderItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long orderItemId;
  // 필드 추가 예정
  @ManyToOne(fetch =FetchType.LAZY)
  @JoinColumn(name="item_id")
  private Item item;
  private int qty;
  private int price;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id")
  private Order order;
}
