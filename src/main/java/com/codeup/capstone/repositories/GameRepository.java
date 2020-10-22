package com.codeup.capstone.repositories;

import com.codeup.capstone.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game,Long> {
//    Game findByTitle(String title); // select * from games where title = ?
//    Game findFirstByTitle(String Title); // select * from games where title = ? limit 1
}
