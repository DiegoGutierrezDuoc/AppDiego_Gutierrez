package com.example.appdiego.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appdiego.data.model.Post
import com.example.appdiego.repository.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {
    private val repository = PostRepository()

    private val _postList = MutableStateFlow<List<Post>>(emptyList())
    val postList: StateFlow<List<Post>> = _postList

    init {
        fetchPosts()
    }

    fun fetchPosts() {
        viewModelScope.launch {
            try {
                _postList.value = repository.getPosts()
            } catch (e: Exception) {
                println("Error al obtener datos: ${e.localizedMessage}")
            }
        }
    }

    fun createPost(title: String, body: String) {
        viewModelScope.launch {
            try {
                val newPost = Post(userId = 1, id = 0, title = title, body = body)
                val response = repository.createPost(newPost)
                // Asignamos un ID local si la API devuelve 0 o nulo
                val createdPost = response.copy(id = if (response.id == 0) (_postList.value.maxOfOrNull { it.id } ?: 0) + 1 else response.id)
                _postList.value = listOf(createdPost) + _postList.value
            } catch (e: Exception) {
                println("Error al crear: ${e.localizedMessage}")
            }
        }
    }

    fun updatePost(post: Post) {
        viewModelScope.launch {
            try {
                val updated = repository.updatePost(post.id, post)
                _postList.value = _postList.value.map { if (it.id == updated.id) updated else it }
            } catch (e: Exception) {
                println("Error al actualizar: ${e.localizedMessage}")
            }
        }
    }

    fun deletePost(id: Int) {
        viewModelScope.launch {
            try {
                repository.deletePost(id)
                _postList.value = _postList.value.filter { it.id != id }
            } catch (e: Exception) {
                println("Error al eliminar: ${e.localizedMessage}")
            }
        }
    }
}