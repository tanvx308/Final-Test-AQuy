package com.fis.java.testfinal.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fis.java.testfinal.constant.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormCustomerDto {
    @NotEmpty(message = "CUS001: Name should not be null.")
    @Length(max = 100, message = "CUS002: Max length of name is 100.")
    private String name;
    @Past(message = "CUS003: Birthdate should be in the past")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime birthday;
    @NotEmpty(message = "CUS004: Address should not be null.")
    private String address;
    @NotEmpty(message = "CUS005: Identity No should not be null.")
    @Length(max = 10, message = "CUS006: Identity No`s length is 10.")
    private String identityNo;
    @NotEmpty(message = "CUS007: Mobile should not be null.")
    @Length(max = 10, message = "CUS008: Phone`s length is 10.")
    private String mobile;
    @NotNull(message = "CUS009: Customer Type should not be null.")
    private String customerType;
}
