package br.edu.atitus.apisample.repositories;

import br.edu.atitus.apisample.entities.Point;
import br.edu.atitus.apisample.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PointRepository extends JpaRepository<Point, UUID> {
    // Método para listar somente os pontos que pertencem a um usuário específico
    List<Point> findByUser(User user);
}