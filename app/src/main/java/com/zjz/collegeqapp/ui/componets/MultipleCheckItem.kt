package com.zjz.collegeqapp.ui.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// 多选框
@Composable
fun MultipleCheckItem(index: Int){

    var multipleCheckTitle by remember {
        mutableStateOf("")
    }

    var multipleOptionCount by remember {
        mutableStateOf(0)
    }

    val multipleOptionStates = remember { mutableStateListOf<String>() }

    if (index == 1){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "多选题",
                fontSize = 20.sp,
                color = Color.Black
            )
        }
    }

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column {
            TextField(
                value = multipleCheckTitle,
                onValueChange = {newTitle->
                    multipleCheckTitle = newTitle
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.LightGray,
                    unfocusedIndicatorColor = Color.LightGray,
                    focusedLabelColor = Color.LightGray,
                    unfocusedLabelColor = Color.LightGray,
                    cursorColor = Color.Black
                ),
                placeholder = { Text("${index}.标题") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            )

            for (i in multipleOptionStates.indices){
                val currentIndex = rememberUpdatedState(i)
                SingleOption(currentIndex.value,multipleOptionStates){
                    multipleOptionCount--
                    multipleOptionStates.removeAt(currentIndex.value)
                }
            }

            IconButton(
                onClick = {
                    multipleOptionStates.add(multipleOptionCount++,"")
                }
            ){
                Icon(
                    Icons.Default.AddCircle,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }

}