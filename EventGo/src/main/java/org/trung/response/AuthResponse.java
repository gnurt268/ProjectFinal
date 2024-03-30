package org.trung.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private String message;
    public AuthResponse(String jwt,String message) {
        super();
        this.jwt = jwt;
        this.message = message;
    }
    public AuthResponse() {

    }
}
