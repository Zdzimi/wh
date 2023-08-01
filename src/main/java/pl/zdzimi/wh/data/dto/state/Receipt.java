package pl.zdzimi.wh.data.dto.state;

import java.time.LocalDate;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import pl.zdzimi.wh.data.entity.DocumentEntry;

@Getter
@Setter
public abstract class Receipt {

  protected long documentId;
  protected int receiptDayOrder;
  protected LocalDate date;
  protected String code;
  protected int type;
  protected int increased;
  protected int decreased;
  protected int amountAfterReceipt;
  protected int amountBeforeReceipt;

  public Receipt(DocumentEntry entry, int amountAfterReceipt) {
    this.documentId = entry.getDocumentId();
    this.receiptDayOrder = entry.getDocument().getDayOrder();
    this.date = entry.getDocument().getDate().toLocalDate();
    this.code = entry.getDocument().getCode();
    this.type = entry.getDocument().getType();
    this.amountAfterReceipt = amountAfterReceipt;
  }

  public static Receipt receipt(DocumentEntry entry, int amountAfterReceipt) {
    switch (entry.getDocument().getType()) {
      case 2: return new PzReceipt(entry, amountAfterReceipt);
      case 3, 4, 8, 9: return new IncreasedReceipt(entry, amountAfterReceipt);
      case 10, 21: return new DecreasedReceipt(entry, amountAfterReceipt);
      case 16, 88: return new MixedReceipt(entry, amountAfterReceipt);
      default: throw new IllegalArgumentException();
    }
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

  @Override
  public int hashCode() {
    return Objects.hash(getDocumentId());
  }
}
