package app.vidasalud.vidasaludbackend.repository;

import app.vidasalud.vidasaludbackend.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
