package com.example.myapplication.core

import android.content.Context
import android.widget.Toast


fun showToast(context: Context, description: String){
    Toast.makeText(context, description, Toast.LENGTH_SHORT).show()
}