package pl.zdzimi.wh.service;

import java.sql.Date;
import java.time.LocalDate;

class DateProvider {

  static Date beforeNow(int days) {
    return Date.valueOf(LocalDate.now().minusDays(days));
  }

}
