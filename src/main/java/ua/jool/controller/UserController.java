package ua.jool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.jool.domain.UserDTO;
import ua.jool.service.FileStorageService;
import ua.jool.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDto) {
        userService.create(userDto);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping("{userId}")
    public ResponseEntity<?> getUserById(@PathVariable("userId") Long id) {
        UserDTO userDto = userService.findUserById(id);
        return new ResponseEntity<UserDTO>(userDto, HttpStatus.OK);
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

    @PostMapping("{userId}/image")
    public ResponseEntity<?> uploadImage(
            @PathVariable("userId") Long id,
            @RequestParam("file") MultipartFile file
    ) {
        System.out.println(file.getOriginalFilename());
        fileStorageService.storeFile(file);
        userService.addImageToUser(file.getOriginalFilename(), id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("userid/{username}")
    public ResponseEntity<?> getUserByName(@PathVariable("username") String username) {
        UserDTO user = userService.findByName(username);
        return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
    }

    @PostMapping("update/password")
    public ResponseEntity<?> updateUserPassword(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("passwordConfirm") String passwordConfirm
            ) {
        userService.updatePassword(username, password, passwordConfirm);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("update/username")
    public ResponseEntity<?> updateUserPassword(
            @RequestParam("currentUsername") String currentUsername,
            @RequestParam("newUsername") String newUsername
            ) {
        userService.updateUsername(currentUsername, newUsername);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }



}
