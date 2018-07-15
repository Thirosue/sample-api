package com.sample.web.admin.controller.api.staffs;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class StaffQuery implements Serializable {

    private static final long serialVersionUID = 7593564324192730932L;

    Long id;

    String email;

    String firstName;

    String lastName;

    String tel;
}
