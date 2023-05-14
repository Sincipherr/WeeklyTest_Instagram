package com.geekster.WeeklyTest_Instagram.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpInput {

    private String userFirstName;
    private String userLastName;
    private Integer userAge;
    private String userEmail;
    private String userPhoneNumber;
    private String userPassword;


}
