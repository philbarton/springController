package uk.co.maclon.claimantService.repository;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uk.co.maclon.claimantService.model.Claimant;

import java.util.List;

@Repository
@Transactional("transactionManager")
public class ClaimantRepository {
    private final DSLContext dsl;

    @Autowired
    public ClaimantRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public Claimant findByNino(String nino) {
        return null;
    }

    public List<Claimant> findAll() {
        return null;
    }

    public void save(Claimant claimant) {

    }
}
