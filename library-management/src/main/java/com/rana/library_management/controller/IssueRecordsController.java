package com.rana.library_management.controller;

import com.rana.library_management.entity.IssueRecord;
import com.rana.library_management.services.IssueRecordsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/issuerecords")
public class IssueRecordsController {

    @Autowired
    private IssueRecordsServices issueRecordsServices;

   @PostMapping("/issuethebook/{id}")
   public ResponseEntity<IssueRecord> issueTheBook(@PathVariable Long bookId){
        return ResponseEntity.ok(issueRecordsServices.issueTheBook(bookId));
    }

    @PostMapping("/returnthebook/{issuerecordid}")
    public ResponseEntity<IssueRecord> returnTheBook(@PathVariable Long issueRecordId){
       return ResponseEntity.ok(issueRecordsServices.returnThebook(issueRecordId));

    }
}
