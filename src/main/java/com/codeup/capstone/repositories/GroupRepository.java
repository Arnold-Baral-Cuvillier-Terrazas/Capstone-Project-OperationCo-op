package com.codeup.capstone.repositories;


import com.codeup.capstone.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
