package pl.zdzimi.wh.service.mapper;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;
import pl.zdzimi.wh.data.dto.stats.WareStats;
import pl.zdzimi.wh.data.entity.Commodity;
import pl.zdzimi.wh.data.entity.DocumentEntry;

@Service
public class WareStatsMapper {

  private static final List<Integer> SALE_TYPES = Arrays.asList(10, 21);
  private static final int PZ_TYPE = 2;

  public WareStats map(Commodity commodity) {
    WareStats wareStats = new WareStats();

    wareStats.setId(commodity.getId());
    wareStats.setCode(commodity.getCode());
    wareStats.setName(commodity.getName());
    wareStats.setNetPrice(commodity.getNetPrice());
    wareStats.setGrossPrice(calculateGrossPrice(commodity));
    wareStats.setSales(calculateSales(commodity));
    wareStats.setAmount((int) commodity.getAmount().getAmount());

    return wareStats;
  }

  private double calculateGrossPrice(Commodity commodity) {
    return commodity.getNetPrice() + (commodity.getNetPrice() * commodity.getTax() / 10000.);
  }

  private int calculateSales(Commodity commodity) {
    int sales = 0;
    for (DocumentEntry entry : commodity.getDocumentEntries()) {
      if (isSalesBill(entry)) {
        sales += entry.getIncreased();
      }
      if (isPzDocument(entry)) {
        return sales;
      }
    }
    return sales;
  }

  private boolean isPzDocument(DocumentEntry entry) {
    return entry.getDocument().getType() == PZ_TYPE;
  }

  private boolean isSalesBill(DocumentEntry entry) {
    return SALE_TYPES.contains(entry.getDocument().getType());
  }

}
