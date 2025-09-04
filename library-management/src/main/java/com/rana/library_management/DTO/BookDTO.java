package com.rana.library_management.DTO;

import lombok.Data;

@Data
public class BookDTO {

    private String title;
    private String author;
    private Integer isbn;
    private Integer quantity;
    private boolean isAvailable;

}
//DTO = Data Transfer Object
//        It’s a plain Java object (POJO) that is used to transfer data between layers (usually between backend and frontend).