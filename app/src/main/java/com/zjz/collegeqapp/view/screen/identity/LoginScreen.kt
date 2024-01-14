package com.zjz.collegeqapp.view.screen.identity

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zjz.collegeqapp.R
import com.zjz.collegeqapp.model.LocalUserViewModel

/**
 * 登录页面
 */

@Composable
fun LoginScreen(
    onNavigateToMain: ()->Unit = {},
    onNavigateToRegister: ()->Unit = {}
    ){

    val userViewModel = LocalUserViewModel.current

    // 屏幕宽度和高度
    val screenWidth : Float
    val screenHeight : Float
    with(LocalDensity.current){
        screenWidth = LocalConfiguration.current.screenWidthDp.dp.toPx()
        screenHeight = LocalConfiguration.current.screenHeightDp.dp.toPx()
    }

    var userName by remember {
        mutableStateOf("")
    }

    var userPassword by remember {
        mutableStateOf("")
    }

    var showPassword by remember {
        mutableStateOf(false)
    }

    var openLoginDialog by remember {
        mutableStateOf(false)
    }

    Box (modifier = Modifier.fillMaxSize()){
        // 背景图层
        Image(
            painter = painterResource(R.drawable.girl_kitten_flower_1440x2560),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        // 右上往左下渐变层
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.linearGradient(listOf(
                    Color(0xFF2DCDF5),Color.Transparent),
                    start = Offset(x = screenWidth,y = 0f),
                    end = Offset(x = 0f, y = screenHeight)
                ))
        )
        Column(
            modifier = Modifier.fillMaxSize().padding(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Spacer(modifier = Modifier.height(150.dp))
                Text(
                    text = "College Queso",
                    color = Color.White,
                    fontSize = 44.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "The Best Questionnaire Platform for College Students",
                    color = Color.LightGray,
                    fontSize = 12.sp
                )
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 用户名输入框
                TextField(
                    value = userName,
                    onValueChange = {userName = it},
                    singleLine = true,
                    leadingIcon = { Icon(
                        painter = painterResource(R.drawable.icon_username),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                        ) },
                    label = {
                        Text(
                            text = "用户名",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.LightGray,
                        unfocusedIndicatorColor = Color.LightGray,
                        focusedLabelColor = Color.LightGray,
                        unfocusedLabelColor = Color.LightGray,
                        cursorColor = Color.White
                    )
                )



                // 密码输入框
                TextField(
                    value = userPassword,
                    onValueChange = {userPassword = it},
                    singleLine = true,
                    leadingIcon = { Icon(
                        painter = painterResource(R.drawable.icon_password),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    ) },
                    label = {
                        Text(
                            text = "密码",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.LightGray,
                        unfocusedIndicatorColor = Color.LightGray,
                        focusedLabelColor = Color.LightGray,
                        unfocusedLabelColor = Color.LightGray,
                        cursorColor = Color.White
                    ),
                    trailingIcon = {
                        Icon(
                            painter = if (showPassword) painterResource(R.drawable.icon_visibility)
                                        else painterResource(R.drawable.icon_visibility_off),
                            contentDescription = null,
                            modifier = Modifier
                                .clickable { showPassword = !showPassword }
                                .size(32.dp),
                            tint = Color.White
                        )
                    },
                    visualTransformation = if (showPassword) VisualTransformation.None
                                            else PasswordVisualTransformation()

                )

                Spacer(modifier = Modifier.height(10.dp))

                // 登录按钮
                TextButton(onClick = {
                    if ( userViewModel.login(userName,userPassword) ) onNavigateToMain()
                    else openLoginDialog = true
                }){
                    Text(
                        text = "登录",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
                if (openLoginDialog){
                    AlertDialog(
                        // 登录失败弹窗
                        onDismissRequest = {openLoginDialog = false},
                        title = { Text(text = "登录失败", fontSize = 18.sp, color = Color.Black) },
                        text = { Text(text = "用户名或密码错误，请重试", fontSize = 14.sp, color = Color.Black) },
                        confirmButton = {
                            TextButton(onClick = {openLoginDialog = false}){
                                Text(text = "确定", fontSize = 16.sp, color = Color.Black)
                            }
                        }
                    )
                }

            }
            // 注册按钮
            TextButton(
                onClick = {onNavigateToRegister()}
            ){
                Text(
                    text = "还没有账号？点击立即注册",
                    color = Color.LightGray,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 26.dp)
                )
            }
        }
    }
}

