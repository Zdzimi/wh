package pl.zdzimi.wh.data.dto.state;

import pl.zdzimi.wh.data.entity.DocumentEntry;

public class DecreasedReceipt extends Receipt {

  public DecreasedReceipt(DocumentEntry entry, int amountAfterReceipt) {
    super(entry, amountAfterReceipt);
    this.decreased = entry.getIncreased();
    this.amountBeforeReceipt = amountAfterReceipt + entry.getIncreased();
  }
}
