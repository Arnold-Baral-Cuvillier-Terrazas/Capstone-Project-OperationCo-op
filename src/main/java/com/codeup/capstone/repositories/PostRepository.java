package com.codeup.capstone.repositories;

import com.codeup.capstone.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("from Post m where m.group = ?1")
    List<Post> getMessagesByGroup_Id(long id);

}