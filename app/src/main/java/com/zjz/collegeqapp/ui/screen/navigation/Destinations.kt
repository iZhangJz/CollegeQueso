package com.zjz.collegeqapp.ui.screen.navigation

/**
 * 页面跳转注册
 * 对各个页面的路由名称进行管理
 */

sealed class Destinations(val route:String){
    // 一级页面主框架
    object MainFrame : Destinations("MainFrame")

    // 二级页面问卷详情页
    object QuesDetailsScreen : Destinations("QuestionnaireDetailsScreen")

    // 二级页面问卷编辑页
    object QuesCreationScreen : Destinations("QuestionnaireCreationScreen")
}
