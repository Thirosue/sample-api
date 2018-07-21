package com.sample.web.base.controller.api.resource;

public interface PageableResource extends Resource {

    int getCount();

    int getPage();

    int getTotalPages();

    void setCount(int count);

    void setPage(int page);

    void setTotalPages(int totalPages);
}
