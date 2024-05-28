package se.bjurr.pactsandbox.provider.logic;

import java.util.List;
import org.springframework.stereotype.Service;
import se.bjurr.pactsandbox.provider.logic.model.Animal;

@Service
public class AnimalsLogicDelegatingService {

  public List<Animal> getAnimals() {
    throw new RuntimeException("Not implemented");
  }
}
