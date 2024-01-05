package com.zjz.collegeqapp.ui.screen.secondlevel

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zjz.collegeqapp.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun QuestionnaireCreationScreen(onBack: ()->Unit){

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
        Column(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 14.dp)
        ) {
            Text(text = "你好", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = "感谢您能抽出几分钟时间来参加本次答题，现在我们就马上开始吧！")
            Divider(modifier = Modifier.padding(vertical = 8.dp))

            // 添加题目按钮
            Row(
                modifier = Modifier
                    .background(color = Color(0x33149EE7))
                    .clip(RoundedCornerShape(4.dp))
                    .padding(vertical = 20.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center

            ) {
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
    }
}