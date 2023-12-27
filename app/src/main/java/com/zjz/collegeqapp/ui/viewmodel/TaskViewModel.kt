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

}