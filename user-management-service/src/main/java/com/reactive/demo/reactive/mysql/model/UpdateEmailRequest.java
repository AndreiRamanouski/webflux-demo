package com.reactive.demo.reactive.mysql.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateEmailRequest {

    @NotNull
    private Long id;
    @NotNull
    @NotEmpty
    private String email;
}
