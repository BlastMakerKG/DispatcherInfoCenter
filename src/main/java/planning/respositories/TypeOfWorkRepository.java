package planning.respositories;

import planning.DTO.TypeOfWotkDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TypeOfWorkRepository extends JpaRepository<TypeOfWotkDTO, Long>, JpaSpecificationExecutor<TypeOfWotkDTO> {
    @Query(value = "select * from typeofwork where work_name = ?1", nativeQuery = true)
    TypeOfWotkDTO getByWorkName(String work_name);

    @Query(value = "select work_name from typeofwork", nativeQuery = true)
    List<String> getAllTypeOfWork();
}
