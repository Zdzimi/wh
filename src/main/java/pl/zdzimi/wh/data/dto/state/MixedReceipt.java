package pl.zdzimi.wh.data.dto.state;

import pl.zdzimi.wh.data.entity.DocumentEntry;

public class MixedReceipt extends Receipt {

  public MixedReceipt(DocumentEntry entry, int amountAfterReceipt) {
    super(entry, amountAfterReceipt);
    this.increased = entry.getIncreased();
    this.decreased = entry.getDecreased();
    this.amountBeforeReceipt = amountAfterReceipt - entry.getIncreased() + entry.getDecreased();
  }
}
