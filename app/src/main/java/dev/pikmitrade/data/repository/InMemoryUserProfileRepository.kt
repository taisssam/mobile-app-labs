package dev.pikmitrade.data.repository

import dev.pikmitrade.domain.model.user.User
import dev.pikmitrade.domain.repository.UserProfileRepository

class InMemoryUserProfileRepository : UserProfileRepository {
    private val users = mutableMapOf<String, User>()

    override fun getUser(userId: String): User? = users[userId]
    override fun saveUser(user: User, userId: String) {
        users[userId] = user
    }
}
