package pl.zdzimi.wh.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "istw")
public class CommodityAmount {

  @Id
  @Column(name = "towid")
  private long id;
  @Column(name = "stanmag")
  private double amount;

}
