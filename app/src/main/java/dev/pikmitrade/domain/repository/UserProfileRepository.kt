package dev.pikmitrade.domain.repository

import dev.pikmitrade.domain.model.user.User

interface UserProfileRepository {
    suspend fun getUser(userId: String): User?
    suspend fun saveUser(user: User, userId: String)
}