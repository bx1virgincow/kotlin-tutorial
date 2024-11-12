package com.example.myapplication.data

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.util.Date

data class Todo(
    var id: Int,
    var title: String,
    var createdAt: Date
)


//fake todo data
@RequiresApi(Build.VERSION_CODES.O)
fun getFakeTodo() : List<Todo>{
    return listOf<Todo>(
        Todo(0, "First Todo", Date.from(Instant.now())),
        Todo(1, "Second Todo", Date.from(Instant.now())),
        Todo(2, "Third Todo", Date.from(Instant.now()))
    )
}