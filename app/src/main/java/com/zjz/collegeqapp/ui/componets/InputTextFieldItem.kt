package com.zjz.collegeqapp.ui.componets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

// 输入文本框
@Composable
fun InputTextFieldItem(index:Int){

    var title by remember {
        mutableStateOf("")
    }
    if (index == 1){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier =Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "问答题",
                fontSize = 20.sp,
                color = Color.Black
            )
        }
    }

    Card(modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = title,
            onValueChange = {newTitle ->
                title = newTitle
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.LightGray,
                unfocusedIndicatorColor = Color.LightGray,
                focusedLabelColor = Color.LightGray,
                unfocusedLabelColor = Color.LightGray,
                cursorColor = Color.Black
            ),
            placeholder = { Text("${index}.标题") }
        )
    }
}