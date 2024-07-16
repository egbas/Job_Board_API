package com.egbas.job_board.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateRequest {
    @NotBlank(message = "Lastname must not be null")
    @Size(min = 2, max = 125, message = "Last name must be at least 2 characters long")
    private String lastName;

    @NotBlank(message = "Firstname must not be null")
    @Size(min = 2, max = 125, message = "First name must be at least 2 characters long")
    private String firstName;

    @NotNull(message = "Date of birth must not be null")
    private LocalDate dateOfBirth;

    @Email(message = "Email must be unique and not null")
    @NotBlank(message = "Email must not be null")
    private String email;
}
