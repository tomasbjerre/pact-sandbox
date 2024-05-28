package se.bjurr.pactsandbox.provider.presentation;

import java.util.List;
import org.springframework.stereotype.Service;
import se.bjurr.pactsandbox.provider.api.model.AnimalIntegrationVO;
import se.bjurr.pactsandbox.provider.api.model.AnimalsIntegrationVO;
import se.bjurr.pactsandbox.provider.logic.model.Animal;

@Service
public class PresentationMapper {

  public AnimalsIntegrationVO toAnimalsDTO(final List<Animal> animals) {
    final List<AnimalIntegrationVO> animalList =
        animals.stream().map(it -> new AnimalIntegrationVO(it.id().toString(), it.name())).toList();
    return new AnimalsIntegrationVO(animalList);
  }
}
