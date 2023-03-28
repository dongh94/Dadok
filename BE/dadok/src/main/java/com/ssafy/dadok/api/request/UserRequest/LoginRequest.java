package com.ssafy.dadok.api.request.UserRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*Login용 Dto*/
@Getter
@Setter
@NoArgsConstructor
public class LoginRequest {
    String id;
    String pw;
}
