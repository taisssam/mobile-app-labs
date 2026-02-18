package dev.pikmitrade.domain.repository

import dev.pikmitrade.domain.model.user.User

interface UserProfileRepository {
    fun getUser(userId: String): User?
    fun saveUser(user: User, userId: String)
}
