package com.sphinx.user;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("select distinct u from User u join u.identifiers i where "
			+ "lower(u.firstName) like ?1 "
			+ "or lower(u.middleName) like ?1 "
			+ "or lower(u.lastName) like ?1 "
			+ "or lower(i.identifier) like ?1 "
			+ "order by u.lastName, u.firstName ")
	public List<User> findSuggestions(String lowerCasePattern, Pageable pageable);
	
}
