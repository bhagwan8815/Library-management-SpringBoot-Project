package com.rana.library_management.controller;

import com.rana.library_management.DTO.BookDTO;
import com.rana.library_management.entity.Book;
import com.rana.library_management.services.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookServices bookServices;

   @GetMapping("/getallbooks")
   public ResponseEntity<List<Book>> getAllBooks(){
        return ResponseEntity.ok(bookServices.getAllBooks());
    }

    @PostMapping("/getbookbyid/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        return ResponseEntity.ok(bookServices.getBookById(id));
    }


    // addBook. deleteBook and updateBook routes onnly access by the admin
    @PostMapping("/addbook")
    @PreAuthorize("hasRole('ADMIN')")   //it means if the user has role admin then it can access this routes
    public ResponseEntity<Book> addBook(@RequestBody BookDTO bookDTO, @PathVariable Long id){
       return ResponseEntity.ok(bookServices.addBook(bookDTO, id));
    }

    @PutMapping("/updatebook/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO){
       return ResponseEntity.ok(bookServices.updateBookById(id, bookDTO));
    }

   @DeleteMapping("/deletebook/{id}")
   @PreAuthorize("hasRole('ADMIN')")
   public ResponseEntity<Void> deleteBook(@PathVariable Long id){
    ResponseEntity.ok( bookServices.deleteBookById(id));
    return ResponseEntity.ok().build();
    }
}
