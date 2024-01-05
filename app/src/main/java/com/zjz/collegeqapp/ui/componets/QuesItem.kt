package com.zjz.collegeqapp.ui.componets

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zjz.collegeqapp.model.entity.QuestionnaireEntity


@Composable
fun QuesItem(questionnaire: QuestionnaireEntity,modifier: Modifier = Modifier){
    Column(
        modifier = modifier.padding(8.dp)
    ) {
        Text(
            text = questionnaire.title,
            color = Color(0xFF333333),
            fontSize = 18.sp,
            // 最多为一行 超出“...”表示
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        ) {
            Text(
                "来源：${questionnaire.source}",
                color = Color(0xFF999999),
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                questionnaire.timeStamp,
                color = Color(0xFF999999),
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
                )

        }

        Spacer(Modifier.height(8.dp))
        // 分割线
        Divider()

    }
}