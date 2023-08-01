package pl.zdzimi.wh.data.dto.state;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.Getter;
import lombok.Setter;
import pl.zdzimi.wh.data.entity.DocumentEntry;

@Getter
@Setter
public class PzReceipt extends Receipt {
  
  private int lowerPartOfSales;
  private int higherPartOfSales;
  private int sales;
  private double partOfSales;

  public PzReceipt(DocumentEntry entry, int amountAfterReceipt) {
    super(entry, amountAfterReceipt);
    this.increased = entry.getIncreased();
    this.amountBeforeReceipt = amountAfterReceipt - entry.getIncreased();
  }

  public void setSalesStats(List<Receipt> receipts, int days, int lessThan, int moreThan) {
    AtomicInteger sum = new AtomicInteger(0);
    receipts.stream()
        .filter(receipt -> isBefore(days, receipt))
        .filter(receipt -> receipt.getType() == 10 || receipt.getType() == 21)
        .map(Receipt::getDecreased)
        .forEach(sum::addAndGet);
    this.sales = sum.get();
    this.lowerPartOfSales = this.sales * lessThan / 100;
    double x = (this.sales * moreThan) / 100.;
    this.higherPartOfSales = x > (int) x ? (int) (x + 1) : (int) x;
    this.partOfSales = this.getAmountBeforeReceipt() * 100. / this.getSales();
  }

  private boolean isBefore(int days, Receipt receipt) {
    return (receipt.getDate().isBefore(this.getDate())
        || (receipt.getDate().isEqual(this.getDate()) && receipt.getReceiptDayOrder() < this.getReceiptDayOrder()))
        && (receipt.getDate().isAfter(this.getDate().minusDays(days)) || receipt.getDate().isEqual(this.getDate().minusDays(days)));
  }
}
