package pl.zdzimi.wh.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.zdzimi.wh.service.CommodityNotFound;

@RestControllerAdvice
public class CommodityNotFoundAdvice {

  @ResponseBody
  @ExceptionHandler(CommodityNotFound.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String commodityNotFoundHandler(CommodityNotFound exception) {
    return exception.getMessage();
  }

}
