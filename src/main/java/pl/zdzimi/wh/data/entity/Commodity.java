package pl.zdzimi.wh.data.entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "towar")
public class Commodity {

  @Id
  @Column(name = "towid")
  private Long id;
  @Column(name = "nazwa")
  private String name;
  @Column(name = "skrot")
  private String shortName;
  @Column(name = "kod")
  private String code;
  @Column(name = "cenadet")
  private double netPrice;
  @Column(name = "stawka")
  private int tax;
  @OneToMany
  @JoinColumn(name = "towid")
  private List<DocumentEntry> documentEntries;
  @OneToOne
  @JoinColumn(name = "towid")
  private CommodityAmount amount;

}
