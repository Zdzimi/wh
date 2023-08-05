package pl.zdzimi.wh.data.dto.stats;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class OrderAndDiscount {

  private final int lessThan;
  private List<WareStats> order = new LinkedList<>();
  private final int moreThan;
  private List<WareStats> discount = new LinkedList<>();

  public void receiveIfMatch(WareStats wareStats) {
    if (shouldOrder(wareStats)) {
      this.order.add(wareStats);
    } else if (shouldDiscount(wareStats)) {
      this.discount.add(wareStats);
    }
  }

  private boolean shouldOrder(WareStats wareStats) {
    return wareStats.getAmount() < wareStats.getSales() * this.lessThan / 100.;
  }

  private boolean shouldDiscount(WareStats wareStats) {
    return wareStats.getAmount() > wareStats.getSales() * this.moreThan / 100.;
  }

  public void sort() {
    this.order.sort(orderComparator());
    this.discount.sort(discountComparator());
  }

  private Comparator<WareStats> discountComparator() {
    return (o1, o2) -> o1.getAmount() - o2.getAmount() == 0 ? o1.getSales() - o2.getSales()
        : o2.getAmount() - o1.getAmount();
  }

  private Comparator<WareStats> orderComparator() {
    return (o1, o2) -> o1.getAmount() - o2.getAmount() == 0 ? o2.getSales() - o1.getSales()
        : o1.getAmount() - o2.getAmount();
  }
}
