package kg.dispatcher.info.centre.prices.planning.respositories;

import kg.dispatcher.info.centre.prices.planning.DTO.TruckDriverDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface TruckDriverRepository extends JpaRepository<TruckDriverDTO, Long>, JpaSpecificationExecutor<TruckDriverDTO> {
    @Query(value = "select * from truckdriver where name = ?1", nativeQuery = true)
    TruckDriverDTO getByName(String name);
}
