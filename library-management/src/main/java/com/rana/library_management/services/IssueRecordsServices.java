package com.rana.library_management.services;

import com.rana.library_management.entity.Book;
import com.rana.library_management.entity.IssueRecord;
import com.rana.library_management.entity.User;
import com.rana.library_management.repository.BookRepository;
import com.rana.library_management.repository.IssueRecordsRepository;
import com.rana.library_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class IssueRecordsServices {
    @Autowired
    private IssueRecordsRepository issueRecordsRepository;
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;



    public IssueRecord issueTheBook(Long bookId){
     Book book = bookRepository.findById(bookId).orElseThrow(()->new RuntimeException("Book Not Found"));
     //check the quantity is avalible or not
        if(book.getQuantity() <=0 || !book.getIsAvailable()){
            throw new RuntimeException("Book is not Available");
        }

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUserName(username).orElseThrow(()->new RuntimeException("user not found"));


        //now created the issue record
        IssueRecord issueRecord= new IssueRecord();

        issueRecord.setIssueDate(LocalDate.now());
        issueRecord.setDueDate(LocalDate.now().plusDays(14));
        issueRecord.setReturned(false);

        issueRecord.setUser(user);
        issueRecord.setBook(book);

        //now decreament the quantity
        book.setQuantity(book.getQuantity()-1);

        if(book.getQuantity()==0){
            book.setIsAvailable(false);
        }

    }

}
