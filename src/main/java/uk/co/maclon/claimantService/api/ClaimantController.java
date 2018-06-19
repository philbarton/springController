package uk.co.maclon.claimantService.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uk.co.maclon.claimantService.model.ClaimantDTO;
import uk.co.maclon.claimantService.service.ClaimantService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class ClaimantController {

    private ClaimantService claimantService;

    @Autowired
    public ClaimantController(ClaimantService claimantService) {
        this.claimantService = claimantService;
    }

    @GetMapping(value = "/api/claimants", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClaimantDTO>> getClaimants() {
        return new ResponseEntity<>(claimantService.getClaimants(), HttpStatus.OK);
    }

    @GetMapping(value = "/api/claimant/{nino}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ClaimantDTO> getClaimantByNino(@PathVariable String nino) {
        return new ResponseEntity<>(claimantService.getClaimantByNino(nino), HttpStatus.OK);
    }

    @PostMapping("/api/claimant")
    public ResponseEntity<?> createClaimant(@RequestBody @Validated ClaimantDTO claimantDTO) {
        claimantService.createClaimant(claimantDTO);
        return ResponseEntity.ok().build();
    }
}
