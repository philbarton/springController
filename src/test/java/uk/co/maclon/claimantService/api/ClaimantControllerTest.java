package uk.co.maclon.claimantService.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import uk.co.maclon.claimantService.model.Claimant;
import uk.co.maclon.claimantService.model.Gender;
import uk.co.maclon.claimantService.model.RelationshipStatus;
import uk.co.maclon.claimantService.model.Title;
import uk.co.maclon.claimantService.service.ClaimantService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ClaimantController.class)
public class ClaimantControllerTest {
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ClaimantService claimantService;

    @Test
    public void getClaimants() throws Exception {
        Claimant claimant = createClaimant("AA123456A");
        List<Claimant> claimants = Collections.singletonList(claimant);
        when(claimantService.getClaimants()).thenReturn(claimants);
        mockMvc.perform(get("/api/claimants"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$[0].nino").value(claimant.getNino()));
    }

    @Test
    public void emptyNino() throws Exception {
        Claimant claimant = createDuffClaimant();
        String content = mapper.writeValueAsString(claimant);
        System.out.println(content);
        mockMvc.perform(post("/api/claimant")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().is4xxClientError());
    }

    private static Claimant createClaimant(String nino) {
        return Claimant
                .builder()
                .nino(nino)
                .dateOfBirth(LocalDate.now())
                .title(Title.MR)
                .firstName("phil")
                .lastName("barton")
                .gender(Gender.MALE)
                .hasPartner(true)
                .relationshipStatus(RelationshipStatus.MARRIED)
                .hasSavings(false)
                .build();
    }

    private static Claimant createDuffClaimant() {
        return Claimant
                .builder()
                .dateOfBirth(LocalDate.now())
                .title(Title.MR)
                .firstName("phil")
                .lastName("barton")
                .gender(Gender.MALE)
                .hasPartner(true)
                .relationshipStatus(RelationshipStatus.MARRIED)
                .hasSavings(false)
                .build();
    }

}