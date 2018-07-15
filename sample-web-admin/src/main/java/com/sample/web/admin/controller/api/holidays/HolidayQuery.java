package com.sample.web.admin.controller.api.holidays;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class HolidayQuery implements Serializable {

    private static final long serialVersionUID = 7593564324192730932L;

    Long id;

    String holidayName;

}
