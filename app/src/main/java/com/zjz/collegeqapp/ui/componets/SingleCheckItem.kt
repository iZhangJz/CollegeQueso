package com.zjz.collegeqapp.ui.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zjz.collegeqapp.R

// 单选框
@Composable
fun SingleCheckItem(index: Int ){

    var singleCheckTitle by remember {
        mutableStateOf("")
    }

    var singleOptionCount by remember {
        mutableStateOf(0)
    }

    val singleOptionStates = remember { mutableStateListOf<String>() }


    if (index == 1){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier =Modifier
                .fillMaxWidth()
            ) {
            Text(
                text = "单选题",
                fontSize = 20.sp,
                color = Color.Black,

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
                value = singleCheckTitle,
                onValueChange = {newTitle->
                    singleCheckTitle = newTitle
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

            for (i in singleOptionStates.indices){
                val currentIndex = rememberUpdatedState(i)
                SingleOption(currentIndex.value,singleOptionStates){
                    singleOptionCount--
                    singleOptionStates.removeAt(currentIndex.value)
                }
            }

            IconButton(
                onClick = {
                    singleOptionStates.add(singleOptionCount++,"")
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


// 单选选项文本输入框
@Composable
fun SingleOption(
    index: Int,
    states: MutableList<String>,
    onDelete: (Int) -> Unit
    ){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Column() {
            TextField(
                value = states[index],
                onValueChange = { newText ->
                    states[index] = newText
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.icon_dot),
                        contentDescription = null,
                        tint = Color.LightGray,
                        modifier = Modifier
                            .size(26.dp)
                    )
                },
                trailingIcon = {
                    IconButton(
                        // 删除选项框
                        onClick = { onDelete(index) }
                    ){
                        Icon(
                            Icons.Default.Close,
                            contentDescription = null,
                            tint = Color.Black
                        )
                    }
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.LightGray,
                    unfocusedIndicatorColor = Color.LightGray,
                    focusedLabelColor = Color.LightGray,
                    unfocusedLabelColor = Color.LightGray,
                    cursorColor = Color.Black
                ),
                placeholder = { Text("选项${index+1}") },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}