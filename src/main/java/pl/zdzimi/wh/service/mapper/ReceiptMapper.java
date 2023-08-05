package pl.zdzimi.wh.service.mapper;

import java.util.List;
import org.springframework.stereotype.Service;
import pl.zdzimi.wh.data.dto.state.Receipt;

@Service
public class ReceiptMapper {

  public List<Receipt> receipts(ReceiptsUtils receiptsUtils) {
    return receiptsUtils.getReceipts();
  }

}
