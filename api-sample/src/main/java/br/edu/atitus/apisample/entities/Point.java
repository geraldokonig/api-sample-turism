package br.edu.atitus.apisample.entities;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tb_point")
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length = 500)
    private String description;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Construtores
    public Point() {}

    // Getters e Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}