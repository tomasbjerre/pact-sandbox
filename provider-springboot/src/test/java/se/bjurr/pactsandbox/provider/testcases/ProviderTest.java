package se.bjurr.pactsandbox.provider.testcases;

import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.loader.PactBroker;
import au.com.dius.pact.provider.spring.spring6.PactVerificationSpring6Provider;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import se.bjurr.pactsandbox.provider.logic.AnimalsLogicDelegatingService;
import se.bjurr.pactsandbox.provider.logic.model.Animal;

/** https://docs.pact.io/implementation_guides/jvm/provider/spring6 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(
    properties = {
      "spring.jackson.serialization.INDENT_OUTPUT=true",
      "pactbroker.auth.username=dXfltyFMgNOFZAxr8io9wJ37iUpY42M",
      "pactbroker.auth.password=O5AIZWxelWbLvqMd8PkAVycBJh2Psyg1"
    })
@Provider("PactSandboxProvider")
@PactBroker(url = "https://test.pactflow.io/")
public class ProviderTest {
  @LocalServerPort int serverPort;

  @MockBean AnimalsLogicDelegatingService logic;

  @BeforeEach
  public void before(final PactVerificationContext context) {
    context.setTarget(new HttpTestTarget("localhost", this.serverPort));

    Mockito.when(this.logic.getAnimals())
        .thenReturn(List.of(new Animal("Zack", 1), new Animal("Alfons", 2)));
  }

  @TestTemplate
  @ExtendWith(PactVerificationSpring6Provider.class)
  void pactVerificationTestTemplate(final PactVerificationContext context) {
    context.verifyInteraction();
  }
}
