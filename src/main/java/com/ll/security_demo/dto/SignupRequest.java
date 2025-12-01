package com.ll.security_demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {
    @NotBlank(message = "사용자명을 입력해주세요.")
    @Size(min =3, max = 20, message = "사용자명은 3~20자입니다")
    private String username;

    @NotBlank(message = "비밀번호를 입력하세요")
    @Size(min = 4, max= 20, message = "비밀번호는 4~20자입니다.")
    private String password;

    @NotBlank(message = "비밀번호 확인을 입력해주세요")
    private String passwordConfirm;

    @NotBlank(message = "이메일을 입력해주세요")
    @Email(message = "올바른 이메일 형식이 아닙니다")
    private String email;
}
