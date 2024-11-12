package com.example.myapplication.presentation

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.data.Todo
import com.example.myapplication.data.TodoViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.core.showToast

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TodoListPage(viewModel: TodoViewModel) {
    val todoList by viewModel.todoList.observeAsState()
    var inputText by remember {
        mutableStateOf("")
    }
    //nav controller
    val navController = rememberNavController()
    //context variable
    val mContext = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(8.dp)
    ) {
        //input field
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = inputText, onValueChange = { it ->
                    inputText = it
                })
            Button(onClick = {
                if (inputText.isEmpty()) {
                    showToast(mContext, "field cannot be empty")
                } else {
                    viewModel.addTodo(inputText)
                    inputText = ""
                }
            }) {
                Text(text = "Add")
            }
        }
        //lazy column
        //checking type safe
        todoList?.let {
            LazyColumn(
                content = {
                itemsIndexed(it) { index: Int, item: Todo ->
                    TodoItem(
                        item = item,
                        onDelete = { viewModel.deleteTodo(item.id) },
                        onEdit = {
                            println("Edit clicked")
//                            navController.navigate()
                        }
                    )
                }
            })
        } ?: Text(
            text = "No Items Added Yet",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 17.sp
        )
    }
}