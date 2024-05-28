package se.bjurr.pactsandbox.consumerpactjvm.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import se.bjurr.pactsandbox.consumerpactjvm.logic.AnimalsLogicDelegatingService;
import se.bjurr.pactsandbox.consumerpactjvm.presentation.model.AnimalsDTO;

@RestController
public class ControllerImpl {
  private final AnimalsLogicDelegatingService logic;
  private final PresentationMapper mapper;

  public ControllerImpl(
      final AnimalsLogicDelegatingService logic, final PresentationMapper mapper) {
    this.logic = logic;
    this.mapper = mapper;
  }

  @GetMapping("/animals")
  public AnimalsDTO getAnimals() {
    return this.mapper.toAnimalsDTO(this.logic.getAnimals());
  }
}
