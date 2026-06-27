package br.edu.atitus.apisample.controllers;

import br.edu.atitus.apisample.dtos.PointDTO;
import br.edu.atitus.apisample.entities.Point;
import br.edu.atitus.apisample.entities.User;
import br.edu.atitus.apisample.services.PointService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ws/point")
public class PointController {

    private final PointService service;

    public PointController(PointService service) {
        this.service = service;
    }

    // Método auxiliar para pegar o usuário logado via Contexto de Segurança do Spring Security (JWT)
    private User getAuthenticatedUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    // 1. Cadastrar Ponto Turístico (POST /ws/point)
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody PointDTO dto) {
        try {
            User userLogado = getAuthenticatedUser();
            Point novoPonto = service.save(dto, userLogado);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoPonto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // 2. Atualizar Ponto Turístico (PUT /ws/point/{id}) - Requisito Obrigatório
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody PointDTO dto) {
        try {
            User userLogado = getAuthenticatedUser();
            Point pontoAtualizado = service.update(id, dto, userLogado);
            return ResponseEntity.ok(pontoAtualizado);
        } catch (Exception e) {
            // Se cair aqui por não achar o ponto ou pertencer a outro usuário, retorna o erro tratado
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // 3. Listar apenas pontos do usuário logado (GET /ws/point) - Requisito Obrigatório
    @GetMapping
    public ResponseEntity<Object> list() {
        try {
            User userLogado = getAuthenticatedUser();
            List<Point> listaPontos = service.findByUser(userLogado);
            return ResponseEntity.ok(listaPontos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}