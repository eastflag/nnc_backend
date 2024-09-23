package com.eastflag.nnc.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>, UserCustomRepository {

  Optional<User> findByEmail(String email);

}
