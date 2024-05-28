package se.bjurr.pactsandbox.provider.presentation;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import se.bjurr.pactsandbox.provider.api.model.AnimalIntegrationVO;
import se.bjurr.pactsandbox.provider.api.model.AnimalsIntegrationVO;
import se.bjurr.pactsandbox.provider.logic.AnimalsLogicDelegatingService;

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
  public AnimalsIntegrationVO getAnimals(final HttpServletResponse response) {
    response.setContentType("application/json");
    response.setStatus(202);
    return this.mapper.toAnimalsDTO(this.logic.getAnimals());
  }

  public AnimalIntegrationVO getAnimal(final String id) {
    throw new RuntimeException("Not implemented");
  }

  public void postAnimals(final AnimalsIntegrationVO animals) {
    throw new RuntimeException("Not implemented");
  }

  public void postAnimal(final String id, final AnimalIntegrationVO animal) {
    throw new RuntimeException("Not implemented");
  }
}
