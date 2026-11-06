package com.example.appdiego.data.remote

import com.example.appdiego.data.model.Post
import retrofit2.http.*

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post>

    @POST("posts")
    suspend fun createPost(@Body post: Post): Post

    @PUT("posts/{id}")
    suspend fun updatePost(@Path("id") id: Int, @Body post: Post): Post

    @DELETE("posts/{id}")
    suspend fun deletePost(@Path("id") id: Int)
}