package ru.vallball.forum02.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import ru.vallball.forum02.model.User;


public interface UserService extends UserDetailsService{
	void save(User user);
	List<User> list();
	void delete(String username);
	void update(User user);
	User userByName(String name);
}
