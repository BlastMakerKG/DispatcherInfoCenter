package kg.dispatcher.info.centre.prices.planning.respositories;

import kg.dispatcher.info.centre.prices.planning.DTO.TruckTypeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TruckTypeRepository extends JpaRepository<TruckTypeDTO, Long>, JpaSpecificationExecutor<TruckTypeDTO> {
    @Query(value = "select * from trucktype where name = ?1", nativeQuery = true)
    List<TruckTypeDTO> getByName(String name);
}
