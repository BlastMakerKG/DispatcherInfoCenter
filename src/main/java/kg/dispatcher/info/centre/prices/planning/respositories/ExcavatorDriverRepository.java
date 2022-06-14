package kg.dispatcher.info.centre.prices.planning.respositories;

import kg.dispatcher.info.centre.prices.planning.DTO.ExcavatorsDriverDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ExcavatorDriverRepository extends JpaRepository<ExcavatorsDriverDTO, Long>, JpaSpecificationExecutor<ExcavatorsDriverDTO> {
    @Query(value = "select * from excavatordriver where name = ?1", nativeQuery = true)
    ExcavatorsDriverDTO getByName(String name);
}
