package app.services.impl;

import app.models.dto.binding.AddGameDto;
import app.models.dto.view.AllGamesView;
import app.models.dto.view.DetailsGameView;
import app.models.entities.Game;
import app.repositories.GameRepository;
import app.services.api.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


@Service
public class GameServiceImpl implements GameService {

    private GameRepository gameRepository;

    private ModelMapper modelMapper;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public String addGame(AddGameDto newGameDto) {
        if (this.gameRepository.existsByTitleEquals(newGameDto.getTitle())) {
            return "Game with such title already exists in the database.";
        }
        Game game = this.modelMapper.map(newGameDto, Game.class);
        this.gameRepository.save(game);
        return "Added " + game.getTitle();
    }

    @Override
    public String editGame(String[] gameInfo) throws ParseException {
        Long id = Long.parseLong(gameInfo[1]);

        if (!this.gameRepository.existsByIdEquals(id)) {
            return "No game with the provided ID was found in the database.";
        }

        Game game = this.gameRepository.findByIdEquals(id);
        for (int i = 2; i < gameInfo.length; i++) {
            String attribute = gameInfo[i].split("=")[0];
            String value = gameInfo[i].split("=")[1];
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
            switch (attribute) {
                case "price":
                    game.setPrice(new BigDecimal(value));
                    break;
                case "size":
                    game.setSize(new BigDecimal(value));
                    break;
                case "title":
                    game.setTitle(value);
                    break;
                case "trailer":
                    game.setTrailer(value);
                    break;
                case "image":
                    game.setImage(value);
                    break;
                case "description":
                    game.setDescription(value);
                    break;
                case "releaseDate":
                    game.setReleaseDate(sdf.parse(value));
                    break;
            }
        }
        this.gameRepository.save(game);
        return "Edited " + game.getTitle();
    }

    @Override
    public String deleteGame(Long id) {
        if (!this.gameRepository.existsByIdEquals(id)){
            return "No game with the provided ID was found in the database.";
        }
        Game game = this.gameRepository.findByIdEquals(id);
        String gameName = game.getTitle();
        this.gameRepository.delete(game);
        return "Deleted " + gameName;
    }

    @Override
    public String allGames() {
        List<AllGamesView> games = this.gameRepository.getAllGames();
        StringBuilder sb = new StringBuilder();
        for (AllGamesView game: games){
            sb.append(game.getTitle()).append(" ").append(String.valueOf(game.getPrice())).append("\n");
        }
        return sb.substring(0, sb.length() - 2);
    }

    @Override
    public String detailsGame(String title) {
        DetailsGameView game = this.gameRepository.getGameByTitle(title);
        return game.toString();
    }

    @Override
    public Game findByTitle(String title) {
        return this.gameRepository.findByTitleEquals(title);
    }
}

