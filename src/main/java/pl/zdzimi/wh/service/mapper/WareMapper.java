package pl.zdzimi.wh.service.mapper;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Service;
import pl.zdzimi.wh.data.dto.state.PzReceipt;
import pl.zdzimi.wh.data.dto.state.Receipt;
import pl.zdzimi.wh.data.dto.state.Ware;
import pl.zdzimi.wh.data.entity.Commodity;
import pl.zdzimi.wh.data.entity.DocumentEntry;

@Service
public class WareMapper {

  private static int amountAfterReceipt = 0;
  private final static List<Integer> CONSIDERED_DOCUMENTS = Arrays.asList(2, 3, 9, 4, 8, 10, 21, 16, 88);

  public Ware map(Commodity commodity, int days, int lessThan, int moreThan) {
    Ware ware = new Ware();

    ware.setId(commodity.getId());
    ware.setName(commodity.getName());
    ware.setShortName(commodity.getShortName());
    ware.setCode(commodity.getCode());
    ware.setNetPrice(commodity.getNetPrice());
    ware.setTax(commodity.getTax() / 100);
    ware.setGrossPrice(calculateGrossPrice(commodity));
    ware.setAmount((int) commodity.getAmount().getAmount());

    amountAfterReceipt = (int) commodity.getAmount().getAmount();

    ware.setReceipts(createReceipts(commodity.getDocumentEntries()));

    ware.getReceipts().stream()
        .filter(x -> x instanceof PzReceipt)
        .map(x -> (PzReceipt) x)
        .forEach(x -> x.setSalesStats(ware.getReceipts(), days, lessThan, moreThan));

    return ware;
  }

  private double calculateGrossPrice(Commodity commodity) {
    return commodity.getNetPrice() + (commodity.getNetPrice() * commodity.getTax() / 10000.);
  }

  private List<Receipt> createReceipts(List<DocumentEntry> entries) {
    List<Receipt> result = new LinkedList<>();

    for (DocumentEntry entry : entries) {
      if (shouldConsider(entry)) {
        Receipt receipt = Receipt.receipt(entry, amountAfterReceipt);
        append(receipt, result);
        amountAfterReceipt = receipt.getAmountBeforeReceipt();
      }
    }
    return result;
  }

  private boolean shouldConsider(DocumentEntry entry) {
    return CONSIDERED_DOCUMENTS.contains(entry.getDocument().getType());
  }

  private void append(Receipt receipt, List<Receipt> result) {
    if (result.contains(receipt)) {
      int indexOf = result.indexOf(receipt);
      Receipt oldReceipt = result.get(indexOf);
      oldReceipt.setIncreased(oldReceipt.getIncreased() + receipt.getIncreased());
      oldReceipt.setDecreased(oldReceipt.getDecreased() + receipt.getDecreased());
      oldReceipt.setAmountBeforeReceipt(receipt.getAmountBeforeReceipt());
    } else {
      result.add(receipt);
    }
  }

}
