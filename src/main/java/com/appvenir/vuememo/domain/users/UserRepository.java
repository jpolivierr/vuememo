package com.appvenir.vuememo.domain.users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    public Optional<User> findByEmail(String email);

    public void save(UserDto user);

    public void deleteByEmail(String email);
    
}
