package ua.jool.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.jool.domain.GameDTO;
import ua.jool.entity.GameEntity;
import ua.jool.exception.ResourceNotFoundException;
import ua.jool.repository.GameRepository;
import ua.jool.service.GameService;
import ua.jool.utils.ObjectMapperUtils;

import java.math.BigDecimal;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ObjectMapperUtils modelMapper;

    @Override
    public List<GameDTO> findAll() {
        List<GameEntity> gameEntities = gameRepository.findAll();
        List<GameDTO> gameDTOS = modelMapper.mapAll(gameEntities, GameDTO.class);
        return gameDTOS;
    }

    @Override
    public void create(GameDTO gameDTO) {
        GameEntity game = modelMapper.map(gameDTO, GameEntity.class);
        gameRepository.save(game);
    }

    @Override
    public List<GameDTO> findGameByPage(Pageable pageable) {
        Page<GameEntity> gameEntities = gameRepository.findAll(pageable);
        List<GameDTO> gameDtos = modelMapper.mapAll(gameEntities.getContent(), GameDTO.class);
        return gameDtos;
    }

    @Override
    public void deleteGameById(Long id) {
        GameEntity gameEntity = gameRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Could not delete product with id[" + id + "]. Not found")
        );
        gameRepository.deleteById(id);
    }

    @Override
    public void addImageToGame(String image, Long id) {
        GameEntity gameEntity = gameRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Game with id [" + id + "] not found")
                );
        gameEntity.setImage(image);
        gameRepository.save(gameEntity);
    }

    @Override
    public GameDTO findGameById(Long id) {
        GameEntity gameEntity =
                gameRepository.findById(id)
                        .orElseThrow(
                                () -> new ResourceNotFoundException("Game with id[" + id + "] not found")
                        );

        GameDTO gameDTO = modelMapper.map(gameEntity, GameDTO.class);
        return gameDTO;
    }

    @Override
    public List<GameDTO> filterByCategory(String name) {
        List<GameEntity> gameEntities = gameRepository.findByCategories_Name(name);
        List<GameDTO> gameDtos = modelMapper.mapAll(gameEntities, GameDTO.class);
        return gameDtos;
    }

    @Override
    public List<GameDTO> filterByPrice(BigDecimal priceFrom, BigDecimal priceTo) {
        List<GameEntity> gameEntities = gameRepository.findByPriceBetween(priceFrom, priceTo);
        List<GameDTO> gameDTOS = modelMapper.mapAll(gameEntities, GameDTO.class);
        return gameDTOS;
    }

    @Override
    public List<GameDTO> filterGame(String name) {
        List<GameEntity> gameEntities = gameRepository.findByNameLike("%" + name + "%");
        List<GameDTO> gameDtos = modelMapper.mapAll(gameEntities, GameDTO.class);
        return gameDtos;
    }

    @Override
    public List<GameDTO> filterGameByDeveloper(String developerName) {
        List<GameEntity> gameEntities = gameRepository.findByDeveloper_Name(developerName);
        List<GameDTO> gameDtos = modelMapper.mapAll(gameEntities, GameDTO.class);
        return gameDtos;
    }
}
