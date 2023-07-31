package pl.zdzimi.wh.data.dto.state;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ware {

  private Long id;
  private String name;
  private String shortName;
  private String code;
  private double netPrice;
  private int tax;
  private double grossPrice;
  private int amount;
  List<Receipt> receipts;

}
