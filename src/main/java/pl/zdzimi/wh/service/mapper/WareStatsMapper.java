package pl.zdzimi.wh.service.mapper;

import org.springframework.stereotype.Service;
import pl.zdzimi.wh.data.dto.stats.WareStats;
import pl.zdzimi.wh.data.entity.Commodity;

@Service
public class WareStatsMapper {

  private final SalesCalculator salesCalculator;

  public WareStatsMapper() {
    this.salesCalculator = new SalesCalculator();
  }

  public WareStats map(Commodity commodity) {
    WareStats wareStats = new WareStats();

    wareStats.setId(commodity.getId());
    wareStats.setCode(commodity.getCode());
    wareStats.setName(commodity.getName());
    wareStats.setNetPrice(commodity.getNetPrice());
    wareStats.setGrossPrice(calculateGrossPrice(commodity));
    wareStats.setSales(salesCalculator.calculateSales(commodity.getDocumentEntries()));
    wareStats.setAmount((int) commodity.getAmount().getAmount());

    return wareStats;
  }

  private double calculateGrossPrice(Commodity commodity) {
    return commodity.getNetPrice() + (commodity.getNetPrice() * commodity.getTax() / 10000.);
  }

}
