package com.example.cine.Controller.Auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Data
@Setter @Getter
public class CredencialesRequest {
    private String username, password, oldUsername;
}
