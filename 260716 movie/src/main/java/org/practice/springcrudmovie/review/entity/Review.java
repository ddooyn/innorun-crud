package org.practice.springcrudmovie.review.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.practice.springcrudmovie.common.entity.BaseEntity;
import org.practice.springcrudmovie.movie.entity.Movie;

@Entity
@Getter
@Table(name = "reviews")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @Column(nullable = false)
    private Double rating;

    @Column(length = 1000)
    private String content;

    public Review(Movie movie, Double rating, String content) {
        this.movie = movie;
        this.rating = rating;
        this.content = content;
    }

    public void update(Double rating, String content) {
        this.rating = rating;
        this.content = content;
    }
}