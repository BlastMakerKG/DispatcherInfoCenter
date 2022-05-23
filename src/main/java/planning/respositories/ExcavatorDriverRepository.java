package planning.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import planning.DTO.ExcavatorDTO;
import planning.DTO.ExcavatorsDriverDTO;

public interface ExcavatorDriverRepository extends JpaRepository<ExcavatorsDriverDTO, Long>, JpaSpecificationExecutor<ExcavatorsDriverDTO> {
    @Query(value = "select * from excavatordriver where name = ?1", nativeQuery = true)
    ExcavatorsDriverDTO getByName(String name);
}
