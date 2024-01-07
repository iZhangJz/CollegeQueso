package com.zjz.collegeqapp.ui.screen

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.zjz.collegeqapp.model.LocalUserViewModel
import com.zjz.collegeqapp.ui.screen.identity.LoginScreen
import com.zjz.collegeqapp.ui.screen.identity.RegisterScreen
import com.zjz.collegeqapp.ui.screen.navigation.Destinations
import com.zjz.collegeqapp.ui.screen.secondlevel.QuestionnaireCreationScreen
import com.zjz.collegeqapp.ui.screen.secondlevel.QuestionnaireDetailsScreen
import com.zjz.collegeqapp.ui.viewmodel.UserViewModel
import com.zjz.collegeqapp.utils.SQLiteHelper

/**
 * 导航控制器
 */

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavHostApp(dbHelper: SQLiteHelper){

    val navController = rememberAnimatedNavController()

    ProvideWindowInsets {

        CompositionLocalProvider(LocalUserViewModel provides UserViewModel(dbHelper)){
            val userViewModel = LocalUserViewModel.current
            AnimatedNavHost(
                navController = navController,
                startDestination = if (userViewModel.isLogin) Destinations.MainFrame.route else Destinations.LoginScreen.route
            ){

                // 登录页面
                composable(
                    Destinations.LoginScreen.route,
                    enterTransition = {
                        // 设置页面跳转动画
                        slideIntoContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(500))
                    },
                    exitTransition = {
                        slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(500))
                    },
                    ){
                        LoginScreen(
                            onNavigateToMain = {navController.navigate(Destinations.MainFrame.route){
                                popUpTo("LoginScreen"){inclusive = true}
                            } },
                            onNavigateToRegister = {navController.navigate(Destinations.RegisterScreen.route)}
                        )
                }


                // 注册页面
                composable(
                    Destinations.RegisterScreen.route,
                    enterTransition = {
                        // 设置页面跳转动画
                        slideIntoContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(500))
                    }, exitTransition = {
                        slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(500))
                    }){
                        RegisterScreen(
                            onNavigateToMain = {navController.navigate(Destinations.MainFrame.route){
                                popUpTo("LoginScreen"){inclusive = true}
                            } }
                        )
                }


                // 一级页面(主页面)
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
                    MainFrame(
                        onNavigateToQues = { navController.navigate(Destinations.QuesDetailsScreen.route) },
                        onNavigateToQuesCreation = {navController.navigate(Destinations.QuesCreationScreen.route)}
                    )
                }

                // 二级页面--问卷详情页
                composable(Destinations.QuesDetailsScreen.route){
                    QuestionnaireDetailsScreen(
                        // 返回一级页面
                        onBack = { navController.popBackStack() }
                    )
                }

                // 二级页面--问卷编辑页
                composable(Destinations.QuesCreationScreen.route){
                    QuestionnaireCreationScreen (
                        onBack = {navController.popBackStack()}
                    )
                }
            }
        }
    }
}