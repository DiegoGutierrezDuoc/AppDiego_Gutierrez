package com.example.appdiego.repository

import com.example.appdiego.data.model.Post
import com.example.appdiego.data.remote.RetrofitInstance

class PostRepository {
    suspend fun getPosts(): List<Post> = RetrofitInstance.api.getPosts()

    suspend fun createPost(post: Post): Post = RetrofitInstance.api.createPost(post)

    suspend fun updatePost(id: Int, post: Post): Post = RetrofitInstance.api.updatePost(id, post)

    suspend fun deletePost(id: Int) = RetrofitInstance.api.deletePost(id)
}