package uk.co.maclon.claimantService.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.maclon.claimantService.model.Claimant;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClaimantRepositoryTest {

    @Autowired
    private ClaimantRepository repository;

    @Test
    public void getByNino() {
        Claimant claimant = repository.findByNino("NB888679A");
        assertEquals("phil", claimant.getFirstName());
    }
}