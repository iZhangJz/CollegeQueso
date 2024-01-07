package com.zjz.collegeqapp.ui.screen.identity

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
 * 注册页面
 */

@Composable
fun RegisterScreen(
    onNavigateToMain:()->Unit = {}
){

    var userName by remember {
        mutableStateOf("")
    }

    var userPassword by remember {
        mutableStateOf("")
    }

    var showPassword by remember {
        mutableStateOf(false)
    }

    var passwordConfirmation by remember {
        mutableStateOf("")
    }

    var openRegisterDialog by remember {
        mutableStateOf(false)
    }

    var registerRes by remember {
        mutableStateOf(Pair(false,"请输入"))
    }

    val userViewModel = LocalUserViewModel.current

    // 屏幕宽度和高度
    val screenWidth : Float
    val screenHeight : Float
    with(LocalDensity.current){
        screenWidth = LocalConfiguration.current.screenWidthDp.dp.toPx()
        screenHeight = LocalConfiguration.current.screenHeightDp.dp.toPx()
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.linearGradient(
                    listOf(Color(0xFF2DCDF5),Color.Transparent),
                    start = Offset(x = screenWidth,y = 0f),
                    end = Offset(x = 0f, y = screenHeight)
                ))
        )

        Column(
            modifier = Modifier.fillMaxSize().padding(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Column {
                Text(
                    text = "用户注册",
                    fontSize = 28.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 用户名注册输入框
                TextField(
                    value = userName,
                    onValueChange = {userName = it},
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.icon_username),
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = {
                        Text(
                            text = "用户名",
                            fontSize = 16.sp,
                            color = Color.Gray
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
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.icon_password),
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = {
                        Text(
                            text = "密码",
                            fontSize = 16.sp,
                            color = Color.Gray
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
                            tint = Color.Gray
                        )
                    },
                    visualTransformation = if (showPassword) VisualTransformation.None
                    else PasswordVisualTransformation()
                )

                // 确认密码输入框
                TextField(
                    value = passwordConfirmation,
                    onValueChange = {passwordConfirmation = it},
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.icon_password),
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = {
                        Text(
                            text = "确认密码",
                            fontSize = 16.sp,
                            color = Color.Gray
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
                            tint = Color.Gray
                        )
                    },
                    visualTransformation = if (showPassword) VisualTransformation.None
                    else PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(12.dp))
                // 注册按钮
                TextButton(
                    onClick = {
                        registerRes = userViewModel.register(userName,userPassword,passwordConfirmation)
                        if (registerRes.first) onNavigateToMain()
                        else openRegisterDialog = true
                    }
                ){
                    Row() {

                        Text(
                            text = "注册",
                            color = Color.Black,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            painter = painterResource(R.drawable.icon_register),
                            contentDescription = null,
                            tint = Color.LightGray,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                // 注册信息弹窗
                if (openRegisterDialog){
                    AlertDialog(
                        onDismissRequest = {openRegisterDialog = false},
                        text = { Text(text = registerRes.second, fontSize = 18.sp, color = Color.Black) },
                        confirmButton = {
                            TextButton(onClick = {openRegisterDialog = false}){
                                Text(text = "确认", fontSize = 16.sp, color = Color.Black)
                            }
                        }
                    )
                }
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 页脚
                Text(
                    text = "College Queso",
                    color = Color.LightGray,
                    fontSize = 14.sp
                )
                Text(
                    text = "The Best Questionnaire Platform for College Students",
                    color = Color.LightGray,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(26.dp))
            }
        }
    }
}