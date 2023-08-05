package pl.zdzimi.wh.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.zdzimi.wh.data.entity.Commodity;

@Repository
public interface CommodityRepository extends JpaRepository<Commodity, Long> {

  @Query("""
select c from Commodity c
left join fetch c.amount
left join fetch c.documentEntries de
left join fetch de.document d
where c.id = ?1
order by d.date desc, d.dayOrder desc, de.order desc
""")
  Optional<Commodity> findById(Long id);

  @Query("""
select c from Commodity c
left join fetch c.amount
left join fetch c.documentEntries de
left join fetch de.document d
where d.date >= ?1
order by c.id, d.date desc, d.dayOrder desc, de.order desc
""")
  List<Commodity> findAllAfter(Date date);

}
