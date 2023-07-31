package pl.zdzimi.wh.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.zdzimi.wh.data.dto.state.Ware;
import pl.zdzimi.wh.data.dto.stats.OrderAndDiscount;
import pl.zdzimi.wh.service.MainService;

@RestController
@RequestMapping("/app")
@RequiredArgsConstructor
@CrossOrigin
public class MainController {

  private final MainService mainService;

  @GetMapping("/commodity/{id}")
  public Ware getCommodity(@PathVariable long id) {
    return mainService.getHistory(id);
  }

  @GetMapping("/commodity")
  public OrderAndDiscount getStats(
      @RequestParam(required = false, defaultValue = "30") int days,
      @RequestParam(required = false, defaultValue = "30") int lessThan,
      @RequestParam(required = false, defaultValue = "70") int moreThan
  ) {
    return mainService.getCandidates(days, lessThan, moreThan);
  }

}
