package com.sample.web.admin.controller.api.inquiry;

import com.sample.domain.enmn.Category;
import com.sample.domain.enmn.Genre;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class InquiryQuery {

    Category category;

    List<Genre> genre;

    String title;
}


