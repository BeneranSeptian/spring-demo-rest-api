package com.seftian.demo.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
class PrimaryKeyUserClass (
        @Column(name = "user_name_beneran")
        var usernameBeneran: String = "",

        @Column(name = "user_id")
        var userId: String = ""

):Serializable

