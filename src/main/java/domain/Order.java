package domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="order_table")
@Data
@NoArgsConstructor
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long orderId;
  private long totalPrice;
  private LocalDateTime orderDateTime;
  private int totalQty;

  @OneToMany(mappedBy = "order")
  private List<OrderItem> orderItemList;
}
