package com.codeup.capstone.repositories;

import com.codeup.capstone.models.Group;
import com.codeup.capstone.models.GroupUser;
import com.codeup.capstone.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupUserRepository extends JpaRepository<GroupUser, Long> {
    List<GroupUser> findAllByGroupAdmin(Boolean isAdmin);

    public GroupUser getFromUser(User user);
}
