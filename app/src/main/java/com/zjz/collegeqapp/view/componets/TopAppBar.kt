package com.zjz.collegeqapp.view.componets


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.zjz.collegeqapp.view.theme.blue200
import com.zjz.collegeqapp.view.theme.blue700


// 标题栏高度
val appBarHeight = 56.dp

@Composable
fun TopAppBar (content: @Composable ()->Unit){

    ProvideWindowInsets {

        // 状态栏高度转换为.dp
        val systemUiController = rememberSystemUiController()
        LaunchedEffect(key1 = Unit){
            systemUiController.setStatusBarColor(Color.Transparent)
        }
        val statusBarHeightDp = with(LocalDensity.current){
            // 获取状态栏高度
            LocalWindowInsets.current.statusBars.top.toDp()
        }

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.linearGradient(
                        // 颜色线性渐变
                        listOf(blue700, blue200)
                    )
                )
                .height(appBarHeight + statusBarHeightDp)
                .padding(top = statusBarHeightDp)
            ,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            content()
        }
    }

}
