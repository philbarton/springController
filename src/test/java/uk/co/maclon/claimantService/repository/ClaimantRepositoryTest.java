package uk.co.maclon.claimantService.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.maclon.claimantService.model.Claimant;
import uk.co.maclon.claimantService.model.Gender;
import uk.co.maclon.claimantService.model.RelationshipStatus;
import uk.co.maclon.claimantService.model.Title;

import java.time.LocalDate;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class ClaimantRepositoryTest {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private ClaimantRepository repository;

    @Before
    public void setUp() {
        mongoTemplate.dropCollection("claimant");
        mongoTemplate.insert(createClaimant("NB888679A"));
    }

    @Test
    public void getByNino() {
        Claimant claimant = repository.findByNino("NB888679A");
        assertEquals("phil", claimant.getFirstName());
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
}