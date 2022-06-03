package planning.respositories;

import planning.DTO.TypeOfWotkDTO;

import java.util.List;

public interface TypeOfWorkRepository{
//    @Query(value = "select * from typeofwork where work_name = ?1", nativeQuery = true)
    TypeOfWotkDTO getByWorkName(String work_name);
//
//    @Query(value = "select work_name from typeofwork", nativeQuery = true)
    List<String> getAllTypeOfWork();

    void save(TypeOfWotkDTO sd);
}
