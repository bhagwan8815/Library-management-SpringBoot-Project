package com.rana.library_management.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name="issueRecords")
public class IssueRecord {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private LocalDate issueDate;
    private  LocalDate dueDate;
    private LocalDate returnedDate;
    private boolean isReturned;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;


    @ManyToOne
    @JoinColumn(name="book_id")
    private Book book;


}
