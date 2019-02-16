package com.sample.web.admin.controller.api.staffs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PasswordUpdateResource implements Serializable {

    private static final long serialVersionUID = 7593564324192730932L;

    Long id;

    String password;

    String passwordConfirm;
}
