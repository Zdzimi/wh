package pl.zdzimi.wh.service.mapper;

import java.util.LinkedList;
import java.util.List;
import pl.zdzimi.wh.data.dto.state.Receipt;
import pl.zdzimi.wh.data.entity.Commodity;
import pl.zdzimi.wh.data.entity.DocumentEntry;

class ReceiptsUtils {

  private final Commodity commodity;
  private final int lessThan;
  private final int moreThan;
  private final List<Receipt> receipts;
  private final SalesCalculator salesCalculator;

  public ReceiptsUtils(Commodity commodity, int lessThan, int moreThan) {
    this.commodity = commodity;
    this.lessThan = lessThan;
    this.moreThan = moreThan;
    this.receipts = new LinkedList<>();
    this.salesCalculator = new SalesCalculator();
  }

  public List<Receipt> getReceipts() {
    int amountAfterReceipt = (int) commodity.getAmount().getAmount();
    List<DocumentEntry> entries = commodity.getDocumentEntries();

    for (int i = 0; i < entries.size(); i++) {
      if (shouldConsider(entries.get(i))) {
        Receipt receipt = Receipt.create(entries.get(i), amountAfterReceipt);
        mergeAndAppend(
            receipt,
            salesCalculator.calculateSales(entries.subList(i, entries.size()), receipt.getDocumentId())
        );
        amountAfterReceipt = receipt.getAmountBeforeReceipt();
      }
    }

    return this.receipts;
  }

  private boolean shouldConsider(DocumentEntry entry) {
    return DocumentProperties.CONSIDERED_TYPES.contains(entry.getDocument().getType());
  }

  private void mergeAndAppend(Receipt receipt, int sales) {
    if (this.receipts.contains(receipt)) {
      int indexOf = this.receipts.indexOf(receipt);
      Receipt oldReceipt = this.receipts.get(indexOf);
      oldReceipt.setIncreased(oldReceipt.getIncreased() + receipt.getIncreased());
      oldReceipt.setDecreased(oldReceipt.getDecreased() + receipt.getDecreased());
      oldReceipt.setAmountBeforeReceipt(receipt.getAmountBeforeReceipt());
    } else {
      receipt.setSalesStats(sales, this.lessThan, this.moreThan);
      this.receipts.add(receipt);
    }
  }

}
