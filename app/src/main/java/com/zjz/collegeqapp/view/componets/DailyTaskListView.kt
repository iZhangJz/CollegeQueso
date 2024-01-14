package com.zjz.collegeqapp.view.componets


import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zjz.collegeqapp.R

@Composable
fun DailyTaskListView(){

    DailyTaskItem("每日登录","500积分/每日首次登录","已获得500积分/每日上限500积分",1f)
    DailyTaskItem("问卷填写","50积分/每有效填写一篇问卷","已获得250积分/每日上限500积分",0.5f)
    DailyTaskItem("发布问卷","200积分/每有效发布一篇问卷","已获得400积分/每日上限1000积分",0.4f)
}

@Composable
fun DailyTaskItem(taskName:String,subTaskName:String,progressText:String,progressPercent:Float){

    val inlineContentId = "inlineContentId"
    val subTaskAnnotatedName = buildAnnotatedString {
        append(subTaskName)
        appendInlineContent(inlineContentId,"[icon]")
    }

    val helpIcon = painterResource(id = R.drawable.help_icon)
    val inlineContentMap = mapOf(
        Pair(
            inlineContentId,
            InlineTextContent(Placeholder(
                width = 14.sp,
                height = 14.sp,
                placeholderVerticalAlign = PlaceholderVerticalAlign.AboveBaseline
                )
            ){
                Image(
                    painter = helpIcon,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        Log.i("===","点击了问号")
                    })
        })
    )

    Row (
        modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Column(
            modifier = Modifier.weight(8f)
        ) {
            Text(
                text = taskName,
                fontSize = 16.sp,
                color = Color(0xFF333333)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = subTaskAnnotatedName,
                inlineContent = inlineContentMap,
                fontSize = 14.sp,
                color = Color(0xFF333333)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                // 进度条
                LinearProgressIndicator(
                    progress = progressPercent,
                    modifier = Modifier.weight(3f).clip(RoundedCornerShape(10.dp))
                )
                Text(
                    text = progressText,
                    fontSize = 10.sp,
                    color = Color(0xFF333333),
                    modifier = Modifier.weight(7f, fill = false).padding(10.dp))
            }
        }

        // “去填写”跳转按钮
        OutlinedButton(
            onClick = {},
            border = if (progressPercent >= 1f) ButtonDefaults.outlinedBorder else BorderStroke(1.dp, Color(0xFFFF5900)),
            shape = CircleShape,
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFFF5900)),
            modifier = Modifier.weight(2f),
            enabled = (progressPercent < 1f)
            ){
            Text(text = "去完成")
        }
    }
}