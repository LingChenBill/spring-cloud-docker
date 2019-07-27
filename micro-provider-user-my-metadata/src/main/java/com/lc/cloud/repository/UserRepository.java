package com.lc.cloud.repository;

import com.lc.cloud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * user repository.
 *
 * @author zyz.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
