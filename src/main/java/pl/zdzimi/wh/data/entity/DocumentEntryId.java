package pl.zdzimi.wh.data.entity;

import java.io.Serializable;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class DocumentEntryId implements Serializable {

  private long documentId;
  private int order;

}
