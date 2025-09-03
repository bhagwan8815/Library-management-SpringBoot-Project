package com.rana.library_management.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private Integer isbn;
    private Integer quantity;
    private boolean isAvailable;
}
