package ua.jool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.jool.domain.GameDTO;
import ua.jool.service.FileStorageService;
import ua.jool.service.GameService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("games")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping
    public ResponseEntity<?> createGame(@Valid  @RequestBody GameDTO gameDTO) {
        gameService.create(gameDTO);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping("games")
    public ResponseEntity<List<GameDTO>> getGames() {
        List<GameDTO> gameDTOS = gameService.findAll();
        return new ResponseEntity<List<GameDTO>>(gameDTOS, HttpStatus.OK);
    }

    @GetMapping("pages")
    public ResponseEntity<?> getGamesByPage(
            @PageableDefault Pageable pageable
    ) {
        List<GameDTO> gameDTOS = gameService.findGameByPage(pageable);

        return new ResponseEntity<>(gameDTOS, HttpStatus.OK);
    }

    @GetMapping("filter/name")
    public ResponseEntity<?> getGamesByFilter(
            @RequestParam("name") String name
    ) {
        List<GameDTO> gameDTOS = gameService.filterGame(name);

        return new ResponseEntity<>(gameDTOS, HttpStatus.OK);
    }

    @GetMapping("filter/developer")
    public ResponseEntity<?> getGamesByFilterDev(
            @RequestParam("developer") String developer
    ) {
        List<GameDTO> gameDTOS = gameService.filterGameByDeveloper(developer);

        return new ResponseEntity<>(gameDTOS, HttpStatus.OK);
    }

    @GetMapping("filter/category")
    public ResponseEntity<?> getGamesByFilterCategories(
            @RequestParam("name") String name
    ) {
        List<GameDTO> gameDTOS = gameService.filterByCategory(name);
        return new ResponseEntity<>(gameDTOS, HttpStatus.OK);
    }

    @GetMapping("filter/price")
    public ResponseEntity<?> getGamesByFilterPrice(
            @RequestParam("priceFrom") BigDecimal priceFrom,
            @RequestParam("priceTo") BigDecimal priceTo
    ) {
        List<GameDTO> gameDTOS = gameService.filterByPrice(priceFrom, priceTo);
        return new ResponseEntity<>(gameDTOS, HttpStatus.OK);
    }

    @DeleteMapping("{gameId}")
    public ResponseEntity<?> deleteGame(
            @PathVariable("gameId") Long id
    ) {
        gameService.deleteGameById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("image")
    public ResponseEntity<?> getFile(
            @RequestParam("fileName") String fileName,
            HttpServletRequest request) {

        Resource resource = fileStorageService.loadFile(fileName);

        String contentType = null;

        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline: filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @PostMapping("{gameId}/image")
    public ResponseEntity<?> uploadImage(
            @PathVariable("gameId") Long id,
            @RequestParam("file") MultipartFile file
    ) {
        System.out.println(file.getOriginalFilename());
        fileStorageService.storeFile(file);
        gameService.addImageToGame(file.getOriginalFilename(), id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("search")
    public ResponseEntity<?> getGameById(
            @RequestParam("id") Long id) {
        GameDTO game = gameService.findGameById(id);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }
}
