package com.springboot.app.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.springboot.app.users.models.User;

/**
 * 
 * @author fraaan JpaSpecificationExecutor<Product> solo debemos extenderla si
 *         vamos a usar especificaciones en las querys... Si no.. NO!
 */
@RepositoryRestResource(path = "users")
public interface UsersRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

	@Query("SELECT u FROM User u WHERE u.username LIKE :username")
	User findUserByUsernameWithQuery(String username);
}
