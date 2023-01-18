package com.seftian.demo.service.impl

import com.seftian.demo.entity.Role
import com.seftian.demo.entity.User
import com.seftian.demo.model.user.CreateUserRequest
import com.seftian.demo.model.user.LoginRequest
import com.seftian.demo.model.user.LoginResponse
import com.seftian.demo.repository.UserRepository
import com.seftian.demo.service.JwtService
import com.seftian.demo.service.UserService
import com.seftian.demo.util.getRandomString
import com.seftian.demo.util.validation.ValidationUtil
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
        private val userRepository: UserRepository,
        private val validationUtil: ValidationUtil,
        private val passwordEncoder: PasswordEncoder,
        private val jwtService: JwtService,
        private val authenticationManager: AuthenticationManager
) : UserService {
    override fun createUser(createUserRequest: CreateUserRequest): String {
        validationUtil.validate(createUserRequest)

        var newUser = User(
                userId = getRandomString(7),
                usernameBeneran = createUserRequest.username,
                passwordBeneran = passwordEncoder.encode(createUserRequest.password),
                role = Role.USER
        )

        val findUserByUsername = userRepository.findByUsernameBeneran(createUserRequest.username)

        if (findUserByUsername?.usernameBeneran == createUserRequest.username) {
            throw Exception("username udh ada yg pake")
        }


        var findUserById = userRepository.findByUserId(newUser.userId)
        while (findUserById != null) {
            newUser.userId = getRandomString(7)
            findUserById = userRepository.findByUserId(newUser.userId)
        }

        userRepository.save(newUser)

        return newUser.userId
    }

    override fun login(loginRequest: LoginRequest): LoginResponse {

        authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                        loginRequest.username,
                        loginRequest.password
                )
        )

        val findUser = userRepository.findByUsernameBeneran(loginRequest.username)
                ?: throw Exception("User kaga ada kocak")
        val jwt = jwtService.generateToken(findUser)

        val newLogin = User(
                userId = findUser.userId,
                usernameBeneran = findUser.usernameBeneran,
                passwordBeneran = findUser.passwordBeneran,
                token = jwt,
                role = findUser.role
        )

        userRepository.save(newLogin)

        return LoginResponse(
                token = newLogin.token
        )
    }
}
