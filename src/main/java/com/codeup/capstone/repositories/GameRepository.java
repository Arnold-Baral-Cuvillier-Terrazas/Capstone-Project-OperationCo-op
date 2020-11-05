
package com.codeup.capstone.repositories;

import com.codeup.capstone.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface GameRepository extends JpaRepository<Game,Long> {

    Game findByTitle(String title); // select * from games where title = ?
    //    Game findFirstByTitle(String Title); // select * from games where title = ? limit 1

    //Following method shows you how to use named parameters in a HQL custom Query
    @Query("FROM Game g WHERE g.title LIKE %:term%")
    List<Game> searchByTitleLike(@Param("term") String term);

}
