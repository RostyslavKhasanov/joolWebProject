package ua.jool.service;

import org.springframework.data.domain.Pageable;
import ua.jool.domain.GameDTO;

import java.math.BigDecimal;
import java.util.List;

public interface GameService {

    void create(GameDTO gameDTO);

    List<GameDTO> findAll();

    GameDTO findGameById(Long id);

    List<GameDTO> findGameByPage(Pageable pageable);

    List<GameDTO> filterGame(String name);

    List<GameDTO> filterGameByDeveloper(String developerName);

    List<GameDTO> filterByCategory(String name);

    List<GameDTO> filterByPrice(BigDecimal priceFrom, BigDecimal priceTo);

    void deleteGameById(Long id);

    void addImageToGame(String image, Long id);
}
