package ru.vallball.forum02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.vallball.forum02.dao.UserRepository;
import ru.vallball.forum02.model.User;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.getOne(username);
		if (user != null) return user;
		throw new UsernameNotFoundException("Пользователь " + username + " не найден");
	}

	@Override
	public void save(User user) {
		userDao.save(user);
		
	}

	@Override
	public List<User> list() {
		return userDao.findAll();
	}

	@Override
	public void delete(String username) {
		userDao.deleteById(username);
		
	}

	@Override
	public void update(User user) {
		userDao.save(user);
			
	}

	@Override
	public User userByName(String name) {
		return userDao.findById(name).get();
	}

}


