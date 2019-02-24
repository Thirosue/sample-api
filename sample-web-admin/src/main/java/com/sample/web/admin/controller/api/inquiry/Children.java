package com.sample.web.admin.controller.api.inquiry;

import com.sample.domain.enmn.Sex;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class Children {

    String name;

    Sex sex;

    LocalDate birthDay;

}
