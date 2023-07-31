package pl.zdzimi.wh.data.entity;

import jakarta.persistence.*;
import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "dok")
public class Document {

  @Id
  @Column(name = "dokid")
  private long id;
  @Column(name = "data")
  private Date date;
  @Column(name = "kolejnywdniu")
  private int dayOrder;
  @Column(name = "nrdok")
  private String code;
  @Column(name = "typdok")
  private int type;

}
