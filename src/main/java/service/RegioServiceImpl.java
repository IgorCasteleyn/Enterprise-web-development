package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domein.Regio;
import repository.RegioRepository;

@Service("regioService")
public class RegioServiceImpl implements RegioService {

  @Autowired
  private RegioRepository regioRepository;

  @Override
  public List<Regio> getAllRegios() {
    return regioRepository.findAll();
  }

  @Override
  public Regio getRegioById(Integer regioId) {
    return regioRepository.findById(regioId).get();
  }

}
