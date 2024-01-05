package com.zjz.collegeqapp.ui.screen.firstlevel

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zjz.collegeqapp.ui.componets.QuesItem
import com.zjz.collegeqapp.ui.componets.TopAppBar
import com.zjz.collegeqapp.ui.viewmodel.QuestionnaireViewModel

@Composable
fun QListScreen( QViewModel:QuestionnaireViewModel = viewModel(),onNavigateToQues:()->Unit = {}){
    Column(modifier = Modifier){
        TopAppBar(){
            // 搜索按钮
            Spacer(modifier = Modifier.width(10.dp))
            Surface(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .weight(1f),
                color = Color(0x33FFFFFF)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        "搜索感兴趣的问卷",
                        color = Color.White,
                        fontSize = 16.sp,
                        // 设置文本长度 省略号隐藏
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis)
                }
            }

            Spacer(modifier = Modifier.width(40.dp))
            // 创建问卷按钮
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(26.dp))
            Spacer(modifier = Modifier.width(10.dp))
        }
        LazyColumn {
            // 问卷列表
            items(QViewModel.QList) {ques ->
                QuesItem(ques, modifier = Modifier.clickable { onNavigateToQues() })
            }
        }


    }

}
