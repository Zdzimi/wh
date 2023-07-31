package pl.zdzimi.wh.service;

public class CommodityNotFound extends RuntimeException {

  public CommodityNotFound(String message) {
    super(message);
  }
}
