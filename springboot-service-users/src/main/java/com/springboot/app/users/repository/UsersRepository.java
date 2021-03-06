package com.springboot.app.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.app.commons.user.models.User;

/**
 * 
 * @author fraaan JpaSpecificationExecutor<Product> solo debemos extenderla si
 *         vamos a usar especificaciones en las querys... Si no.. NO!
 */
@Repository
public interface UsersRepository extends JpaRepository<User, Long>, UsersRepositoryCustom {

	User findByUsernameLike(String username);
}
