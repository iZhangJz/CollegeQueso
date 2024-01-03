package com.zjz.collegeqapp.ui.componets

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp


/**
 * 每天获取的积分上限为2000
 * 每一行代表400分
 * 每一分代表的高度0.075dp
 */

@Composable
fun ChartView(points: List<Int>,modifier: Modifier = Modifier){


    // 每一行的高度
    val heightForRow = 30;
    val countForRow = 5;

    // 折点圆半径
    val circleRadius = 2.5;

    // 画布宽度
    val canvasWidth = LocalConfiguration.current.screenWidthDp - 8 * 2;
    // 画布高度
    val canvasHeight = heightForRow * countForRow + 2 * circleRadius;

    Canvas(
        modifier = modifier.size(
            width = canvasWidth.dp,
            height = canvasHeight.dp
        )
    ){
        // 画每一行的分割线
        for (index in 0 .. countForRow){
            val y = (heightForRow * index + circleRadius).dp.toPx()
            drawLine(
                Color(0xFFC0C0C0),
                start = Offset(0f,y),
                end = Offset(size.width,y),
                strokeWidth = 2.5F
            )
        }
        // 画积分折线图
        // 画布平均宽度
        val averageOfWidth = canvasWidth / 7;
        for (index in 0 until points.count()){
            // 画折点圆圈
            val circleCenterPos = Offset(
                    (averageOfWidth * index + averageOfWidth / 2).dp.toPx(),
                    (heightForRow * countForRow - points[index] * 0.075 + circleRadius).dp.toPx()
                )
            drawCircle(
                Color(0xFF149EE7),
                radius = circleRadius.dp.toPx(),
                center = circleCenterPos,
            )
            // 画折线
            if(index < points.count() - 1){
                // 下一个折点的位置
                val nextCircleCenterPos = Offset(
                    (averageOfWidth * (index + 1) + averageOfWidth / 2).dp.toPx(),
                    (heightForRow * countForRow - points[index + 1] * 0.075 + circleRadius).dp.toPx()
                )
                drawLine(
                    Color(0XFF149EE7),
                    start = circleCenterPos,
                    end = nextCircleCenterPos,
                    strokeWidth = 5f
                )
            }
        }
    }
}