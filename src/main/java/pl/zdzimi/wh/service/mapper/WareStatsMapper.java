package pl.zdzimi.wh.service.mapper;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;
import pl.zdzimi.wh.data.dto.stats.WareStats;
import pl.zdzimi.wh.data.entity.Commodity;
import pl.zdzimi.wh.data.entity.DocumentEntry;

@Service
public class WareStatsMapper {

  private static final List<Integer> SALE_TYPES = Arrays.asList(4, 8, 10, 21);

  public WareStats map(Commodity commodity) {
    WareStats wareStats = new WareStats();

    wareStats.setId(commodity.getId());
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
      if (SALE_TYPES.contains(entry.getDocument().getType())) {
        sales += entry.getIncreased();
      }
    }
    return sales;
  }

}
