package com.eoemusic.eoebackend.repository;

import com.eoemusic.eoebackend.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description: some desc
 * @author: Xiaoyu Wu
 * @email: wu.xiaoyu@northeastern.edu
 * @date: 20/01/23 1:09 AM
 */
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findById(Long id);
}
