package service;

import org.springframework.stereotype.Service;
import java.util.List;
import domein.Regio;

@Service
public interface RegioService {

  public List<Regio> getAllRegios();

  public Regio getRegioById(Integer regioId);

}
