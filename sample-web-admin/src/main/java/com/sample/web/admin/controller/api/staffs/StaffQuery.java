package com.sample.web.admin.controller.api.staffs;

import com.sample.domain.dto.common.SystemQuery;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class StaffQuery extends SystemQuery {

    private static final long serialVersionUID = 7593564324192730932L;

    Long id;

    String email;

    String firstName;

    String lastName;

    String tel;
}
