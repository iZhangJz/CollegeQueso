package com.zjz.collegeqapp.ui.screen.secondlevel

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zjz.collegeqapp.R
import kotlinx.coroutines.launch
import com.zjz.collegeqapp.ui.componets.InputTextFieldItem
import com.zjz.collegeqapp.ui.componets.SingleCheckItem
import com.zjz.collegeqapp.ui.componets.MultipleCheckItem

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun QuestionnaireCreationScreen(onBack: ()->Unit){

    var textFieldCount by remember {
        mutableStateOf(0)
    }

    var singleCheckCount by remember {
        mutableStateOf(0)
    }

    var multipleCheckCount by remember {
        mutableStateOf(0)

    }
    var totalNumberOfItems by remember {
        mutableStateOf(0)
    }
    val serialNumber = remember { mutableListOf<Int>() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "问卷编辑" , modifier = Modifier.padding(horizontal = 100.dp), textAlign = TextAlign.Center, fontSize = 18.sp) },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowLeft,
                        contentDescription = null,
                        modifier = Modifier
                            .clickable { onBack() }
                            .padding(8.dp)
                    ) },
            )  },
        modifier = Modifier
            .background(MaterialTheme.colors.primary )
            .statusBarsPadding()
    ){
        val modalBottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
        val coroutineScope = rememberCoroutineScope()
        ModalBottomSheetLayout(
            sheetState = modalBottomSheetState,
            sheetShape = RoundedCornerShape(8.dp),
            sheetContent = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(300.dp)
                ) {
                   // 弹窗主体内容 选择题型
                    Text(
                        text = "题型选择",
                        fontSize = 22.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(8.dp)
                    )

                   Row(
                       horizontalArrangement = Arrangement.SpaceBetween,
                       verticalAlignment = Alignment.CenterVertically,
                       modifier = Modifier
                           .fillMaxWidth()
                           .padding(24.dp)
                   ){
                       // 单选框
                       TextButton(
                           onClick = {
                               singleCheckCount++
                               totalNumberOfItems++
                               serialNumber.add(totalNumberOfItems)
                           },
                           modifier = Modifier.padding(8.dp)
                       ){
                           Column {
                               Icon(
                                   painter = painterResource(R.drawable.icon_single_check),
                                   contentDescription = null,
                                   tint = Color.Gray,
                                   modifier = Modifier.size(34.dp)
                               )
                               Text(
                                   text = "单选",
                                   color = Color.Black
                               )
                           }
                       }
                       // 多选框
                       TextButton(
                           onClick = {
                               multipleCheckCount++
                               totalNumberOfItems++
                               serialNumber.add(totalNumberOfItems)
                           },
                           modifier = Modifier.padding(8.dp)
                       ){
                           Column {
                               Icon(
                                   painter = painterResource(R.drawable.icon_multiple_check),
                                   contentDescription = null,
                                   tint = Color.Gray,
                                   modifier = Modifier.size(34.dp)
                               )
                               Text(
                                   text = "多选",
                                   color = Color.Black
                               )
                           }

                       }

                       // 文本填入
                       TextButton(
                           onClick = {
                               textFieldCount++
                               totalNumberOfItems++
                               serialNumber.add(totalNumberOfItems)
                           },
                           modifier = Modifier.padding(8.dp)
                       ){
                           Column {
                               Icon(
                                   painter = painterResource(R.drawable.icon_edit),
                                   contentDescription = null,
                                   tint = Color.Gray,
                                   modifier = Modifier.size(34.dp)
                               )
                               Text(
                                   text = "文本",
                                   color = Color.Black
                                   )
                           }
                       }
                   }
                }
            },
            ){
            LazyColumn(
                    modifier = Modifier.padding(horizontal = 14.dp,vertical = 14.dp)
                ) {
                    item {
                        Text(text = "你好", fontSize = 18.sp)
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(text = "感谢您能抽出几分钟时间来参加本次答题，现在我们就马上开始吧！")
                        Divider(modifier = Modifier.padding(vertical = 8.dp))
                    }

                    item {
                        // 实时添加题目内容
                        // 添加单选
                        repeat(singleCheckCount){
                            SingleCheckItem(index = serialNumber[it])
                            println("singleCheckCount: $singleCheckCount")
                            Divider(modifier = Modifier.padding(vertical = 8.dp))
                        }

                        // 添加多选
                        repeat(multipleCheckCount){
                            MultipleCheckItem(index = serialNumber[it])
                            Divider(modifier = Modifier.padding(vertical = 8.dp))
                        }

                        // 添加文本
                        repeat(textFieldCount){
                            InputTextFieldItem(index = serialNumber[it])
                            Divider(modifier = Modifier.padding(vertical = 8.dp))
                        }
                    }

                    item {
                        // 添加题目按钮
                        Row(
                            modifier = Modifier
                                .background(color = Color(0x33149EE7))
                                .padding(vertical = 20.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            TextButton(
                                onClick = {
                                    coroutineScope.launch { modalBottomSheetState.show() }
                                }
                            ){
                                Icon(
                                    painter = painterResource(R.drawable.add),
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp),
                                    tint = Color(0xFF149EE7)
                                )
                                Text(
                                    text = "添加题目",
                                    color = Color(0xFF149EE7),
                                    fontSize = 16.sp
                                )
                            }
                        }
                        Divider(modifier = Modifier.padding(vertical = 8.dp))
                    }

                    item {
                        Text(
                            text = "您已完成本次问卷，感谢你的帮助与支持",
                            modifier = Modifier.padding(8.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
        }
    }
}





