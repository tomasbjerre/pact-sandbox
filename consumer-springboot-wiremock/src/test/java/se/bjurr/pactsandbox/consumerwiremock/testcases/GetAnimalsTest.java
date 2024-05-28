package se.bjurr.pactsandbox.consumerwiremock.testcases;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import se.bjurr.pactsandbox.consumerwiremock.testutils.WireMockPactBaseTest;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:autotest.properties")
public class GetAnimalsTest extends WireMockPactBaseTest {

	  @Autowired public MockMvc mockMvc;
	  
  @Test
  public void testGetAnimal() throws Exception {
    final MvcResult actual = this.mockMvc.perform(get("/animals")).andDo(log()).andReturn();

    assertThat(actual.getResponse().getStatus()).isEqualTo(200);

    assertThat(actual.getResponse().getContentAsString())
        .isEqualToIgnoringNewLines("""
{
  "animals" : [ {
    "id" : "1",
    "name" : "Zack"
  }, {
    "id" : "2",
    "name" : "Alfons"
  } ]
}
	""");
  }
}
