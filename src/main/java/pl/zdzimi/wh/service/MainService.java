package pl.zdzimi.wh.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zdzimi.wh.data.dto.state.Ware;
import pl.zdzimi.wh.data.dto.stats.OrderAndDiscount;
import pl.zdzimi.wh.repository.CommodityRepository;
import pl.zdzimi.wh.service.mapper.WareMapper;
import pl.zdzimi.wh.service.mapper.WareStatsMapper;

@Service
@RequiredArgsConstructor
public class MainService {

  private final CommodityRepository commodityRepository;
  private final WareStatsMapper wareStatsMapper;
  private final WareMapper wareMapper;
  private final DateProvider dateProvider;

  public OrderAndDiscount getCandidates(int days, int lessThan, int moreThan) {
    OrderAndDiscount orderAndDiscount = new OrderAndDiscount(lessThan, moreThan);
    commodityRepository.findAllAfter(dateProvider.beforeNow(days)).stream()
        .map(wareStatsMapper::map)
        .forEach(orderAndDiscount::receiveIfMatch);
    orderAndDiscount.sort();
    return orderAndDiscount;
  }

  public Ware getHistory(long id, int days, int lessThan, int moreThan) {
    return wareMapper.map(
        commodityRepository.findById(id, dateProvider.beforeNow(days))
            .orElseThrow(() -> new CommodityNotFound("Commodity no: " + id + " does not exists.")),
        lessThan, moreThan
    );
  }

}
