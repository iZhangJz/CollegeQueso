package com.zjz.collegeqapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zjz.collegeqapp.ui.componets.ChartView
import com.zjz.collegeqapp.ui.componets.CircleRing
import com.zjz.collegeqapp.ui.componets.appBarHeight
import com.zjz.collegeqapp.ui.viewmodel.TaskViewModel

@Composable
fun TasksScreen(taskViewModel: TaskViewModel = viewModel()){

    // 定义圆弧高度
    var boxWidthDp: Int
    with(LocalConfiguration.current){
        boxWidthDp = screenWidthDp / 2
    }

    // 当pointOfMonth改变时，更新学年积分
    LaunchedEffect(taskViewModel.pointOfMonth){
        taskViewModel.updatePointPercent()
    }
    // 当pointOfToday改变时，更新今日任务提醒
    LaunchedEffect(taskViewModel.pointOfToday){
        taskViewModel.updateTipsOfToday()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color(0xFF149EE7),Color.White)))
    ){
        Row(modifier = Modifier.statusBarsPadding().height(appBarHeight),
            verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "任务中心",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 18.sp
            )
        }

        // 任务积分
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 任务周期
            item {
                Text(
                    text = taskViewModel.taskDate,
                    fontSize = 12.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp))
            }
            // 积分进度
            item {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .height(boxWidthDp.dp)
                        .padding(top = 8.dp)
                ){
                    // 圆弧

                    CircleRing(boxWidthDp,taskViewModel)

                    // 进度数据
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            buildAnnotatedString {
                                append(taskViewModel.pointOfMonth.toString())
                                withStyle(SpanStyle(fontSize = 12.sp)){
                                    append("分")
                                }
                            },
                            fontSize = 36.sp,
                            color = Color.White,
                            modifier = Modifier
                                .padding(4.dp)
                        )
                        Text(
                            text = "月积分",
                            fontSize = 12.sp,
                            color = Color.White
                        )
                    }
                }
            }
            // 积分展示
            item {
                Row (
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-40).dp)
                ){
                    Column {
                        Text(text = "${taskViewModel.totalPointOfMonth}分", fontSize = 16.sp, color = Color.White)
                        Text(text = "总积分", fontSize = 12.sp, color = Color.White)
                    }
                    Column {
                        Text(text = "${taskViewModel.totalPointOfMonth - taskViewModel.pointOfMonth}分", fontSize = 16.sp, color = Color.White)
                        Text(text = "剩余积分", fontSize = 12.sp, color = Color.White)
                    }
                }
            }
            // 积分明细
            item {
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                        .background(color = Color.White)
                        .fillParentMaxSize()
                        .padding(top = 8.dp)
                        .padding(horizontal = 8.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "积分明细",
                        fontSize = 16.sp,
                        color = Color(0xFF333333),
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "最近一周获得积分情况",
                        fontSize = 14.sp,
                        color = Color(0xFF999999)
                    )
                    // 积分明细折线图
                    ChartView(taskViewModel.pointOfWeek, modifier = Modifier.padding(vertical = 8.dp))
                    // 日期
                    Row {
                        taskViewModel.dateOfWeek.forEach{
                            Text(
                                text = it,
                                fontSize = 12.sp,
                                color = Color(0xFF999999),
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    // 今日任务提醒
                    Text(
                        text = taskViewModel.tipsOfToday,
                        color = Color(0xFF149EE7),
                        fontSize = 14.sp,
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(color = Color(0x33149EE7))
                            .padding(8.dp)
                            .fillMaxWidth()
                    )
                }
            }
        }

    }
}