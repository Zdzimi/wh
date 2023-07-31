package pl.zdzimi.wh.data.dto.state;

import pl.zdzimi.wh.data.entity.DocumentEntry;

public class IncreasedReceipt extends Receipt {

  public IncreasedReceipt(DocumentEntry entry, int amountAfterReceipt) {
    super(entry, amountAfterReceipt);
    this.increased = entry.getIncreased();
    this.amountBeforeReceipt = amountAfterReceipt - entry.getIncreased();
  }
}
