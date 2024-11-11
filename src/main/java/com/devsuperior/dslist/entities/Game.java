package com.devsuperior.dslist.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Entity
@Table(name = "tb_game")
@Data
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", columnDefinition = "VARCHAR", length = 255)
    private String title;

	@Column(name = "score")
	private Double score;

	@Column(name = "game_year")
    private Integer year;

	@Column(name = "genre")
    private String genre;

	@Column(name = "platforms")
    private String platforms;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "short_description", columnDefinition = "VARCHAR", length = 255)
    private String shortDescription;

    @Column(name = "long_description", columnDefinition = "VARCHAR", length = 255)
    private String longDescription;
}
