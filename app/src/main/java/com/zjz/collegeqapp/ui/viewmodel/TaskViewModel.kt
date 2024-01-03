package com.zjz.collegeqapp.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


class TaskViewModel : ViewModel() {

    var taskDate by mutableStateOf("任务周期：2023.12.1-2023.12.31")
        private set

    var pointOfMonth by mutableStateOf(22000)
        private set

    // 一个月能获得的最大积分
    var totalPointOfMonth = 50000

    // 百分比： 220f * pointOfMonth / totalPoint
    var pointOfMonthPercent by mutableStateOf(0f)
        private set

    // 更新积分进度条
    fun updatePointPercent(){
        pointOfMonthPercent = 240f * pointOfMonth / totalPointOfMonth
    }

    // 一周积分情况
    var pointOfWeek by mutableStateOf(
        listOf(200,0,620,1000,1420,540,730)
    )
        private set

    // 日期
    var dateOfWeek = listOf("01.01","01.02","01.03","01.04","01.05","01.06","今日")

    // 今日提醒文字
    var tipsOfToday by mutableStateOf("今日获得0积分，快去完成下面的任务吧")
        private set
    //今日获取积分
    var pointOfToday = 1200
        private set

    fun updateTipsOfToday(){
        tipsOfToday = if (pointOfToday == 2000){
            "今日获得2000积分，任务完成"
        }else{
            "今日获得${pointOfToday}积分，快去完成下面的任务吧"
        }
    }
}