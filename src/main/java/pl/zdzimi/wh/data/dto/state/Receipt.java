package pl.zdzimi.wh.data.dto.state;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import pl.zdzimi.wh.data.entity.DocumentEntry;

@Getter
@Setter
public abstract class Receipt {

  private long documentId;
  private int receiptDayOrder;
  private LocalDate date;
  private String code;
  private int type;
  protected int increased;
  protected int decreased;
  private int amountAfterReceipt;
  protected int amountBeforeReceipt;

  private int lowerPartOfSales;
  private int higherPartOfSales;
  private int sales;
  private double partOfSales;

  public Receipt(DocumentEntry entry, int amountAfterReceipt) {
    this.documentId = entry.getDocumentId();
    this.receiptDayOrder = entry.getDocument().getDayOrder();
    this.date = entry.getDocument().getDate().toLocalDate();
    this.code = entry.getDocument().getCode();
    this.type = entry.getDocument().getType();
    this.amountAfterReceipt = amountAfterReceipt;
  }

  public static Receipt create(DocumentEntry entry, int amountAfterReceipt) {
    return switch (entry.getDocument().getType()) {
      case 2, 3, 4, 8, 9 -> new IncreasedReceipt(entry, amountAfterReceipt);
      case 10, 21 -> new DecreasedReceipt(entry, amountAfterReceipt);
      case 16, 88 -> new MixedReceipt(entry, amountAfterReceipt);
      default -> throw new IllegalArgumentException();
    };
  }

  public void setSalesStats(int sales, int lessThan, int moreThan) {
    this.sales = sales;
    this.lowerPartOfSales = this.sales * lessThan / 100;
    double x = (this.sales * moreThan) / 100.;
    this.higherPartOfSales = x > (int) x ? (int) (x + 1) : (int) x;
    this.partOfSales = this.amountBeforeReceipt * 100. / this.getSales();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Receipt)) {
      return false;
    }
    Receipt receipt = (Receipt) o;
    return getDocumentId() == receipt.getDocumentId();
  }

}
