package com.app.security.repository;

import com.app.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select distinct u from User u left join fetch u.roles where u.email = ?1")
    User findByEmailFetchRoles(String email);

}
