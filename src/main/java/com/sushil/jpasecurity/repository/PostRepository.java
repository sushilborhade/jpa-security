package com.sushil.jpasecurity.repository;

import com.sushil.jpasecurity.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
