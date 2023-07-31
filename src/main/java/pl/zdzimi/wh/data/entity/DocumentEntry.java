package pl.zdzimi.wh.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@IdClass(DocumentEntryId.class)
@Table(name = "pozdok")
public class DocumentEntry {

    @Id
    @Column(name = "dokid")
    private long documentId;
    @Id
    @Column(name = "kolejnosc")
    private int order;
    @Column(name = "nrpozycji")
    private int entryOrder;
    @Column(name = "towid")
    private int commodityId;
    @Column(name = "iloscplus")
    private int increased;
    @Column(name = "iloscminus")
    private int decreased;
    @ManyToOne()
    @JoinColumn(name = "dokid")
    private Document document;
}
