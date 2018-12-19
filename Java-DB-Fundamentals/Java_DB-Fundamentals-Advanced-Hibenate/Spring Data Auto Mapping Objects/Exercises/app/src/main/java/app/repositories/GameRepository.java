package app.repositories;

import app.models.dto.view.AllGamesView;
import app.models.dto.view.DetailsGameView;
import app.models.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    Boolean existsByTitleEquals(String tittle);

    Boolean existsByIdEquals(Long id);

    Game findByIdEquals(Long id);

    Game findByTitleEquals(String title);

    @Query("SELECT new app.models.dto.view.AllGamesView(g.title, g.price) " +
            "FROM Game AS g")
    List<AllGamesView> getAllGames();

    @Query("SELECT new app.models.dto.view.DetailsGameView(g.title, g.price, g.description, g.releaseDate) " +
            "FROM Game AS g " +
            "WHERE g.title = :title")
    DetailsGameView getGameByTitle(String title);
}
