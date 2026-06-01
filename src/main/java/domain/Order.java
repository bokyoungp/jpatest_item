package domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
  private List<OrderItem> orderItemList = new ArrayList<>();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id")
  private User user;

  // order 생성
  public static Order createOrder(User user, OrderItem... orderItems) {
    Order order = new Order();
//  order 관련내용 및 양방향 검색을 위한 내용 추가
    order.setOrderDateTime(LocalDateTime.now());
    order.setUser(user);
    int totPrice = 0;
    int totQuantity = 0;
    for (OrderItem orderItem : orderItems) {
      order.addOrderItem(orderItem);
      totQuantity += orderItem.getQty();
      totPrice += orderItem.getPrice() * orderItem.getQty();
    }
    order.setTotalQty(totQuantity);
    order.setTotalPrice(totPrice);

    return order;
  }

  // order Item 양방향 설정을 위한 부분
  private void addOrderItem(OrderItem orderItem) {
    this.orderItemList.add(orderItem);
    orderItem.setOrder(this);
  }

}
