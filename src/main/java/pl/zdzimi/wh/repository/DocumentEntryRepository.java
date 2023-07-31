package pl.zdzimi.wh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zdzimi.wh.data.entity.DocumentEntry;
import pl.zdzimi.wh.data.entity.DocumentEntryId;

@Repository
public interface DocumentEntryRepository extends JpaRepository<DocumentEntry, DocumentEntryId> {

}
