package com.zjz.collegeqapp.ui.componets

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.zjz.collegeqapp.ui.viewmodel.TaskViewModel

@Composable
fun CircleRing(boxWidthDp:Int,taskViewModel: TaskViewModel){
    // 自定义图形绘制

    val strokeWidth = 30f

    Canvas(modifier = Modifier
        .size(boxWidthDp.dp)){
        // 扇形绘制
        drawArc(
            Color(0,0,0,15),
            startAngle = 160f,     // 开始位置
            sweepAngle = 220f,     // 展开角度
            useCenter = false,
            style = Stroke(strokeWidth, cap = StrokeCap.Round)
        )
        // 扇形填充颜色
        drawArc(
            Color.White,
            startAngle = 160f,     // 开始位置
            sweepAngle = taskViewModel.pointOfMonthPercent,     // 展开角度
            useCenter = false,
            style = Stroke(strokeWidth, cap = StrokeCap.Round)
        )
    }
}