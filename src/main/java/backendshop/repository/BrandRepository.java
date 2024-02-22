package backendshop.repository;

import backendshop.model.entity.Brands;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brands, Long> {
    @Query(value = "SELECT b FROM Brands b WHERE  lower(b.brandName) like %:brandName% AND lower(b.brandUrl) like %:url% " +
            "AND (((:startId is not null AND :endId is not null) AND (b.id between :startId and :endId)) OR ((:startId is not null AND :endId is null) AND b.id = :startId) OR ((:startId is null  AND :endId is not null) AND b.id = :endId) OR (:startId is null AND :endId is null)) " +
            "AND b.deleteFlag = false")
    Page<Brands> findAllBySearch(@Param("brandName") String brandName,
                                 @Param("url") String url,
                                 @Param(("startId")) Integer startId, @Param("endId") Integer endId,
                                 Pageable pageable);
}
