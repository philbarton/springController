package uk.co.maclon.claimantService.repository;

import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uk.co.maclon.claimantService.model.ClaimantDTO;
import uk.co.maclon.claimantService.model.Gender;
import uk.co.maclon.claimantService.model.RelationshipStatus;
import uk.co.maclon.claimantService.model.Title;
import uk.co.maclon.db.tables.records.ClaimantRecord;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static uk.co.maclon.db.Tables.CLAIMANT;

@Repository
@Transactional("transactionManager")
public class ClaimantRepository {
    private final DSLContext dsl;

    @Autowired
    public ClaimantRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public ClaimantDTO findByNino(String nino) {
        ClaimantRecord claimantRecord = dsl.selectFrom(CLAIMANT)
                .where(CLAIMANT.NINO.eq(nino))
                .fetchOne();
        return buildDto(claimantRecord);
    }

    public List<ClaimantDTO> findAll() {
        Result<ClaimantRecord> records = dsl.selectFrom(CLAIMANT).fetch();
        return records
                .stream()
                .map(dbToDTO())
                .collect(Collectors.toList());
    }

    public void save(ClaimantDTO claimantDTO) {
        dsl.executeInsert(dsl.newRecord(CLAIMANT, claimantDTO));
    }

    private Function<ClaimantRecord, ClaimantDTO> dbToDTO() {
        return this::buildDto;
    }

    private ClaimantDTO buildDto(ClaimantRecord claimantRecord) {
        return ClaimantDTO.builder()
                .nino(claimantRecord.getNino())
                .title(Title.valueOf(claimantRecord.getTitle()))
                .dateOfBirth(claimantRecord.getDateOfBirth().toLocalDate())
                .firstName(claimantRecord.getFirstName())
                .middleName(claimantRecord.getMiddleName())
                .lastName(claimantRecord.getLastName())
                .gender(Gender.valueOf(claimantRecord.getGender()))
                .relationshipStatus(RelationshipStatus.valueOf(claimantRecord.getRelationshipStatus()))
                .hasPartner(claimantRecord.getHasPartner())
                .hasSavings(claimantRecord.getHasSavings())
                .build();
    }
}
