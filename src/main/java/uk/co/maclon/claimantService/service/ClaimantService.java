package uk.co.maclon.claimantService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.maclon.claimantService.model.Claimant;
import uk.co.maclon.claimantService.repository.ClaimantRepository;

import java.util.List;

@Service
public class ClaimantService {
    private ClaimantRepository repository;

    @Autowired
    public ClaimantService(ClaimantRepository repository) {
        this.repository = repository;
    }

    public List<Claimant> getClaimants() {
        return repository.findAll();
    }

    public Claimant getClaimantByNino(String nino) {
        return repository.findByNino(nino);
    }

    public void createClaimant(Claimant claimant) {
        repository.save(claimant);
    }
}
