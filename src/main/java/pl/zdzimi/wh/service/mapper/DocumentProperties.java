package pl.zdzimi.wh.service.mapper;

import java.util.Arrays;
import java.util.List;

interface DocumentProperties {

  List<Integer> CONSIDERED_TYPES = Arrays.asList(2, 3, 9, 4, 8, 10, 21, 16, 88);
  List<Integer> SALES_TYPES = Arrays.asList(10, 21);
  int PZ_TYPE = 2;
}
