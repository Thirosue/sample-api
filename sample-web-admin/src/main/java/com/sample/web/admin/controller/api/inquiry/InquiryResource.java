package com.sample.web.admin.controller.api.inquiry;

import com.sample.domain.enmn.Category;
import com.sample.domain.enmn.Genre;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class InquiryResource {

    String name;

    String email;

    Category category;

    List<Genre> genre;

    String title;

    String body;

    List<Children> children;
}
