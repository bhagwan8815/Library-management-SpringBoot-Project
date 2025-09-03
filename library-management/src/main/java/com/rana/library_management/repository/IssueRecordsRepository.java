package com.rana.library_management.repository;

import com.rana.library_management.entity.IssueRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRecordsRepository extends JpaRepository<IssueRecord, Long> {

}
