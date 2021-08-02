package com.github.myauth.core;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtToken implements Token {

    private String encodeString;

}
