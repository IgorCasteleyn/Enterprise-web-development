package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domein.User;
import repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {
  @Autowired
  private UserRepository userRepository;

  @Override
  public User findByUsername(String username) {
    return userRepository.findByUsername(username);
  }
}
