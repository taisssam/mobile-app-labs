package dev.pikmitrade.data.repository

import dev.pikmitrade.domain.model.user.User
import dev.pikmitrade.domain.repository.UserProfileRepository
import java.util.concurrent.ConcurrentHashMap

class InMemoryUserProfileRepository : UserProfileRepository {
    private val users = ConcurrentHashMap<String, User>()

    override suspend fun getUser(userId: String): User? = users[userId]
    override suspend fun saveUser(user: User, userId: String) { users[userId] = user }
}