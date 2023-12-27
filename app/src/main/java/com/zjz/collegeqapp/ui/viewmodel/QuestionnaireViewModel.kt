package com.zjz.collegeqapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.zjz.collegeqapp.model.entity.QuestionnaireEntity

class QuestionnaireViewModel: ViewModel() {
    // 问卷列表数据
    var QList = listOf(
        QuestionnaireEntity(
            title = "企业文化调查问卷",
            source = "张金喜",
            timeStamp = "2023-12-23"
        ),
        QuestionnaireEntity(
            title = "满意度调查问卷",
            source = "乾隆皇帝",
            timeStamp = "2023-11-2"
        ),
        QuestionnaireEntity(
            title = "青春期学习状况",
            source = "康熙",
            timeStamp = "2023-1-23"
        ),
        QuestionnaireEntity(
            title = "不同年龄段网购调查",
            source = "小李子",
            timeStamp = "2024-1-1"
        ),
        QuestionnaireEntity(
            title = "大学生社会实践情况调查",
            source = "吴一帆",
            timeStamp = "2023-12-24"
        )
    )
        private set

}