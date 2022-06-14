package kg.dispatcher.info.centre.prices.planning.respositories;

import kg.dispatcher.info.centre.prices.planning.DTO.UnloadPointDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface UnloadPointRepository extends JpaRepository<UnloadPointDTO, Long>, JpaSpecificationExecutor<UnloadPointDTO> {
    @Query(value = "select * from unloadpoint where unloading_point_name = ?1", nativeQuery = true)
    UnloadPointDTO getByName(String name);
}
