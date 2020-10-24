package com.codeup.capstone.repositories;

import com.codeup.capstone.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("from Post a where a.id = ?1")
    Post getPostById(long id);

}
