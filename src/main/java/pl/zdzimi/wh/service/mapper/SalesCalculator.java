package pl.zdzimi.wh.service.mapper;

import java.util.List;
import pl.zdzimi.wh.data.entity.DocumentEntry;

class SalesCalculator {

  int calculateSales(List<DocumentEntry> entries) {
    int sales = 0;
    for (DocumentEntry entry : entries) {
      if (isSalesBill(entry)) {
        sales += entry.getIncreased();
      }
      if (isPzDocument(entry)) {
        return sales;
      }
    }
    return sales;
  }

  int calculateSales(List<DocumentEntry> entries, long documentId) {
    int sales = 0;
    for (DocumentEntry entry : entries) {
      if (isSalesBill(entry)) {
        sales += entry.getIncreased();
      }
      if (isPzDocument(entry, documentId)) {
        return sales;
      }
    }
    return sales;
  }

  private boolean isSalesBill(DocumentEntry entry) {
    return DocumentProperties.SALES_TYPES.contains(entry.getDocument().getType());
  }

  private boolean isPzDocument(DocumentEntry entry) {
    return entry.getDocument().getType() == DocumentProperties.PZ_TYPE;
  }

  private boolean isPzDocument(DocumentEntry entry, long documentId) {
    return entry.getDocument().getType() == DocumentProperties.PZ_TYPE && entry.getDocument().getId() != documentId;
  }

}
