package uk.co.maclon.claimantService.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.co.maclon.claimantService.model.Claimant;

@Repository
public interface ClaimantRepository extends CrudRepository<Claimant, String> {

    @Query("{ 'nino': ?0 }")
    Claimant findByNino(String nino);

}
