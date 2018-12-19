package app.services.api;


import app.models.dto.binding.AddGameDto;
import app.models.entities.Game;

import java.text.ParseException;

public interface GameService {
    String addGame(AddGameDto newGameDto) throws ParseException;

    String editGame(String[] gameInfo) throws ParseException;

    String deleteGame(Long id);

    String allGames();

    String detailsGame(String title);

    Game findByTitle(String title);
}
