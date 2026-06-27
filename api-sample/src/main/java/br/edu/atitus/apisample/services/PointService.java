package br.edu.atitus.apisample.services;

import br.edu.atitus.apisample.dtos.PointDTO;
import br.edu.atitus.apisample.entities.Point;
import br.edu.atitus.apisample.entities.User;
import br.edu.atitus.apisample.repositories.PointRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PointService {

    private final PointRepository repository;

    public PointService(PointRepository repository) {
        this.repository = repository;
    }

    // Método para salvar um novo ponto (POST)
    public Point save(PointDTO dto, User user) throws Exception {
        validarCampos(dto);

        Point point = new Point();
        point.setName(dto.getName().trim());
        point.setDescription(dto.getDescription().trim());
        point.setLatitude(dto.getLatitude());
        point.setLongitude(dto.getLongitude());
        point.setUser(user); // Associa o ponto ao usuário autenticado

        return repository.save(point);
    }

    // Método para atualizar um ponto existente (PUT) - Requisito Obrigatório
    public Point update(UUID id, PointDTO dto, User user) throws Exception {
        // 1. Verificar se o ponto existe — se não, lança exceção
        Point pointExistente = repository.findById(id)
                .orElseThrow(() -> new Exception("Ponto turístico não encontrado com o ID informado!"));

        // 2. Verificar se o ponto pertence ao usuário logado — se não, lança exceção
        if (!pointExistente.getUser().getId().equals(user.getId())) {
            throw new Exception("Acesso negado! Este ponto pertence a outro usuário.");
        }

        validarCampos(dto);

        // Atualiza os dados
        pointExistente.setName(dto.getName().trim());
        pointExistente.setDescription(dto.getDescription().trim());
        pointExistente.setLatitude(dto.getLatitude());
        pointExistente.setLongitude(dto.getLongitude());

        return repository.save(pointExistente);
    }

    // Método para listar apenas os pontos do usuário logado - Requisito Obrigatório
    public List<Point> findByUser(User user) {
        return repository.findByUser(user);
    }

    // Validações básicas de campos obrigatórios
    private void validarCampos(PointDTO dto) throws Exception {
        if (dto == null) {
            throw new Exception("Objeto DTO nulo!");
        }
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new Exception("O nome do ponto turístico é obrigatório!");
        }
        if (dto.getDescription() == null || dto.getDescription().isBlank()) {
            throw new Exception("A descrição do ponto turístico é obrigatória!");
        }
        if (dto.getLatitude() < -90 || dto.getLatitude() > 90) {
            throw new Exception("Latitude inválida! Deve estar entre -90 e 90.");
        }
        if (dto.getLongitude() < -180 || dto.getLongitude() > 180) {
            throw new Exception("Longitude inválida! Deve estar entre -180 e 180.");
        }
    }
}