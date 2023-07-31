package pl.zdzimi.wh.service.mapper;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Service;
import pl.zdzimi.wh.data.dto.state.Receipt;
import pl.zdzimi.wh.data.dto.state.Ware;
import pl.zdzimi.wh.data.entity.Commodity;
import pl.zdzimi.wh.data.entity.DocumentEntry;

@Service
public class WareMapper {

  private static int amountAfterReceipt = 0;
  private final static List<Integer> DOCUMENTS = Arrays.asList(2, 3, 9, 4, 8, 10, 21, 16, 88);

  public Ware map(Commodity commodity) {
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

    ware.setReceipts(setReceipts(commodity.getDocumentEntries()));

    return ware;
  }

  private double calculateGrossPrice(Commodity commodity) {
    return commodity.getNetPrice() + (commodity.getNetPrice() * commodity.getTax() / 10000.);
  }

  private List<Receipt> setReceipts(List<DocumentEntry> entries) {
    List<Receipt> result = new LinkedList<>();

    for (DocumentEntry entry : entries) {
      if (DOCUMENTS.contains(entry.getDocument().getType())) {
        Receipt receipt = Receipt.receipt(entry, amountAfterReceipt);
        result.add(receipt);
        amountAfterReceipt = receipt.getAmountBeforeReceipt();
      }
    }

    return result;
  }

}
