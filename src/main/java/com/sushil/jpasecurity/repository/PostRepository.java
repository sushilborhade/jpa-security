package com.sushil.jpasecurity.repository;

import com.sushil.jpasecurity.model.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
