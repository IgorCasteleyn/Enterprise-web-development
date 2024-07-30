package service;

import org.springframework.stereotype.Service;

import domein.User;

@Service
public interface UserService {

  public User findByUsername(String username);

}
