package pl.zdzimi.wh.service;

import java.sql.Date;
import java.time.LocalDate;
import org.springframework.stereotype.Service;

@Service
class DateProvider {

  public Date beforeNow(int days) {
    return Date.valueOf(LocalDate.now().minusDays(days));
  }

}
