package com.rana.library_management.services;

import com.rana.library_management.DTO.BookDTO;
import com.rana.library_management.entity.Book;
import com.rana.library_management.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServices {
    @Autowired
    private BookRepository bookRepository;
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book getBookById(Long id){
        Book newBook = bookRepository.findById(id).orElseThrow(()-> new RuntimeException("Book Not Found."));
        return newBook;
    }

    public Book addBook(BookDTO bookDTO){
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setIsbn(bookDTO.getIsbn());
        book.setQuantity(bookDTO.getQuantity());
       book.setAvailable(bookDTO.isAvailable());

       return bookRepository.save(book);
    }
    public Book updateBookById(Long id, BookDTO bookDTO){
       Book oldBook = bookRepository.findById(id).orElseThrow(()->new RuntimeException("Book Not Found."));

        oldBook.setTitle(bookDTO.getTitle());
        oldBook.setAuthor(bookDTO.getAuthor());
        oldBook.setIsbn(bookDTO.getIsbn());
        oldBook.setQuantity(bookDTO.getQuantity());
        oldBook.setAvailable(bookDTO.isAvailable());
        return bookRepository.save(oldBook);
    }

    public void deleteBookById(Long id){
        bookRepository.deleteById(id);
    }



}
