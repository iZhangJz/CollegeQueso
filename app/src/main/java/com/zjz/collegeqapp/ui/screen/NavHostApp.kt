package com.zjz.collegeqapp.ui.screen

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.zjz.collegeqapp.ui.screen.navigation.Destinations
import com.zjz.collegeqapp.ui.screen.secondlevel.QuestionnaireDetailsScreen

/**
 * 导航控制器
 */

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavHostApp(){

    val navController = rememberAnimatedNavController()

    AnimatedNavHost(navController = navController, startDestination = Destinations.MainFrame.route){
        // 一级页面
        composable(
            Destinations.MainFrame.route,
            enterTransition = {
            // 设置页面跳转动画
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Right,
                animationSpec = tween(500)
                )
        }, exitTransition = {
            slideOutOfContainer(
                AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(500)
                )
        })  {
            MainFrame(onNavigateToQues = {
                navController.navigate(Destinations.QuesDetailsScreen.route)
            })
        }
        // 二级页面--问卷详情页
        composable(Destinations.QuesDetailsScreen.route){
            QuestionnaireDetailsScreen()
        }
    }

}