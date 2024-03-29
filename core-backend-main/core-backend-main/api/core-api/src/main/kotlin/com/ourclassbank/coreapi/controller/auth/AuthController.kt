package com.ourclassbank.coreapi.controller.auth

import com.ourclassbank.coreapi.controller.auth.request.DemoUserSigninRequest
import com.ourclassbank.coreapi.controller.auth.request.UserSigninRequest
import com.ourclassbank.coreapi.controller.auth.response.UserPasswordChangeRequest
import com.ourclassbank.coreapi.controller.auth.response.UserSigninResponse
import com.ourclassbank.coredomain.usecase.AuthUsecase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Tag(name = "인증/인가")
@RestController
class AuthController(
    private val authUsecase: AuthUsecase,
) {
    @Operation(summary = "회원 로그인")
    @PostMapping("/api/v1/auth/signin")
    fun signin(@RequestBody request: UserSigninRequest): UserSigninResponse {
        return UserSigninResponse(authUsecase.signin(request.username, request.password))
    }

    @Operation(summary = "데모 회원 로그인")
    @PostMapping("/api/v1/auth/signin/demo")
    fun demoSignin(@RequestBody request: DemoUserSigninRequest): UserSigninResponse {
        return UserSigninResponse(authUsecase.demoSignin(request.demoUserType.username))
    }

    @Operation(summary = "회원 비밀번호 변경")
    @PostMapping("/api/v1/auth/password/change")
    fun passwordReset(@RequestBody request: UserPasswordChangeRequest) {
        request.run {
            authUsecase.passwordChange(username, name, newPassword)
        }
    }
}
