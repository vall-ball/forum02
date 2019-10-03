package ru.vallball.forum02.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.vallball.forum02.model.User;

public interface UserRepository extends JpaRepository<User, String>{

}
