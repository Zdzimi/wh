package pl.zdzimi.wh.data.dto.stats;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WareStats {

  private long id;
  private String code;
  private String name;
  private double netPrice;
  private double grossPrice;
  private int sales;
  private int amount;

}
