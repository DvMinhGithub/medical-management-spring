package com.mdv.hospital.dto.response;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomPageResponse<T> {
    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private long totalElements;

    public CustomPageResponse(Page<T> page) {
        this.content = page.getContent();
        this.pageNumber = page.getNumber() + 1;
        this.pageSize = page.getSize();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
    }
}
