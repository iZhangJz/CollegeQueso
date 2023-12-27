package com.zjz.collegeqapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.zjz.collegeqapp.ui.componets.TopAppBar

@Composable
fun MineScreen(){
    Column(modifier = Modifier){
        TopAppBar(){
            Text(text = "个人中心页面")
        }
    }
}