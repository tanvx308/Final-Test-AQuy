package com.fis.java.testfinal.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fis.java.testfinal.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormCustomerDto {
    @NotEmpty(message = "Name should not be null.")
    @Length(max = 100, message = "Max length of name is 100.")
    private String name;

    @Past(message = "Birthdate should be in the past")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime birthday;

    @NotEmpty(message = "Address should not be null.")
    private String address;

    @NotEmpty(message = "Identity No should not be null.")
    @Length(max = 10, message = "Identity No`s length is 10.")
    private String identityNo;

    @NotEmpty(message = "Mobile should not be null.")
    @Length(max = 10, message = "Phone`s length is 10.")
    private String mobile;

    @NotNull(message = "Customer Type should not be null.")
    private Customer.CustomerType customerType;
}
