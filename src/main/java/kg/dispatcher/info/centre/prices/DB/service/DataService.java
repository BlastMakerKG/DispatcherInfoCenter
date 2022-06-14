package kg.dispatcher.info.centre.prices.DB.service;

import kg.dispatcher.info.centre.prices.DB.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DataService extends JpaRepository<Data, Long> {
}
