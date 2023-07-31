package pl.zdzimi.wh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zdzimi.wh.data.entity.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

}
