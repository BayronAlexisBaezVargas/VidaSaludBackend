package app.vidasalud.vidasaludbackend.controller;

import app.vidasalud.vidasaludbackend.model.Post;
import app.vidasalud.vidasaludbackend.model.User;
import app.vidasalud.vidasaludbackend.repository.PostRepository;
import app.vidasalud.vidasaludbackend.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    // Carpeta donde se guardarán las fotos
    private final String UPLOAD_DIR = "uploads/";

    public PostController(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<?> createPost(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("image") MultipartFile image,
            @RequestParam("username") String username // Temporalmente enviamos el usuario manual
    ) {
        try {
            // 1. Guardar archivo
            String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
            Path path = Paths.get(UPLOAD_DIR);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            Files.copy(image.getInputStream(), path.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            String imageUrl = "http://localhost:8080/uploads/" + fileName;

            // 2. Buscar usuario y crear Post
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            Post post = new Post();
            post.setTitle(title);
            post.setDescription(description);
            post.setImageUrl(imageUrl);
            post.setUser(user);

            return ResponseEntity.ok(postRepository.save(post));

        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Error al subir imagen");
        }
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
}
