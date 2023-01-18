package com.seftian.demo.entity

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


@Entity
@Table(name = "users")
@IdClass(PrimaryKeyUserClass::class)
data class User(
        @Id
        @Column(name = "user_id") var userId: String = "",

        @Id
        @Column(name = "user_name_beneran", unique = true) var usernameBeneran: String = "",

        //dibikin private coba!
        @Column(name = "password_beneran") var passwordBeneran: String = "",

        @Column(name = "token") var token: String = "",

        @Column(name = "public_key")
        private var publicKey: String = "",

        @Enumerated(EnumType.STRING)
        @Column(name = "role") var role: Role = Role.USER

) : UserDetails {

        override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
                return mutableListOf(SimpleGrantedAuthority(role.name))
        }

        override fun getPassword(): String {
                return passwordBeneran
        }

        override fun getUsername(): String {
                return usernameBeneran
        }

        override fun isAccountNonExpired(): Boolean {
                return true
        }

        override fun isAccountNonLocked(): Boolean {
                return true
        }

        override fun isCredentialsNonExpired(): Boolean {
                return true
        }

        override fun isEnabled(): Boolean {
                return true
        }
}