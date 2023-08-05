package pl.zdzimi.wh.service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zdzimi.wh.data.dto.state.Ware;
import pl.zdzimi.wh.data.entity.Commodity;

@Service
@RequiredArgsConstructor
public class WareMapper {

  private final ReceiptMapper receiptMapper;

  public Ware map(Commodity commodity, int lessThan, int moreThan) {
    Ware ware = new Ware();

    ware.setId(commodity.getId());
    ware.setName(commodity.getName());
    ware.setShortName(commodity.getShortName());
    ware.setCode(commodity.getCode());
    ware.setNetPrice(commodity.getNetPrice());
    ware.setTax(commodity.getTax() / 100);
    ware.setGrossPrice(calculateGrossPrice(commodity));
    ware.setAmount((int) commodity.getAmount().getAmount());
    ware.setReceipts(receiptMapper.receipts(new ReceiptsUtils(commodity, lessThan, moreThan)));

    return ware;
  }

  private double calculateGrossPrice(Commodity commodity) {
    return commodity.getNetPrice() + (commodity.getNetPrice() * commodity.getTax() / 10000.);
  }

}
