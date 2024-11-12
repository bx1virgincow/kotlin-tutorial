package com.example.myapplication.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TodoViewModel : ViewModel() {
    private val _todoList = MutableLiveData<List<Todo>>();
    val todoList: LiveData<List<Todo>> = _todoList;

    //function
    private fun getTodo() {
        _todoList.value =  TodoManager.getTodo().reversed()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addTodo(title: String) {
        TodoManager.addTodo(title)
        getTodo()
    }

    fun deleteTodo(id: Int) {
        TodoManager.deleteTodo(id)
        getTodo()
    }
}