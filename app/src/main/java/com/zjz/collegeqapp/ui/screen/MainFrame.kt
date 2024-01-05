package com.zjz.collegeqapp.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zjz.collegeqapp.model.entity.NavigationEntity
import com.zjz.collegeqapp.ui.screen.firstlevel.MineScreen
import com.zjz.collegeqapp.ui.screen.firstlevel.QListScreen
import com.zjz.collegeqapp.ui.screen.firstlevel.TasksScreen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun  MainFrame(
    onNavigateToQues:()->Unit = {},
    onNavigateToQuesCreation:()->Unit = {}
    ){
    val navigationEntities = listOf(
        NavigationEntity(title = "主页", icon = Icons.Filled.Home),
        NavigationEntity(title = "任务", icon = Icons.Filled.DateRange),
        NavigationEntity(title = "我的", icon = Icons.Filled.Person),
    )

    var currentNavigationIndex by remember {
        mutableStateOf(0)
    }

    Scaffold(bottomBar = {
        BottomNavigation(
            backgroundColor = MaterialTheme.colors.surface) {
            navigationEntities.forEachIndexed { index, navigationItem ->
                BottomNavigationItem(
                    selected = currentNavigationIndex == index,
                    onClick = {
                        currentNavigationIndex = index
                    },
                    icon = {
                        Icon(
                            imageVector = navigationItem.icon,
                            contentDescription = null,
                            modifier = Modifier.size(26.dp)
                        )
                    },
                    label = {
                        Text(text = navigationItem.title, fontSize = 14.sp)
                    },
                    alwaysShowLabel = false,
                    selectedContentColor = Color(0xFF149EE7),
                    unselectedContentColor = Color(0xFF999999),
                )
            }
        }
    },
        modifier = Modifier.padding(bottom = 25.dp)) {

        // 实现一级页面之间跳转
        when(currentNavigationIndex){
            0-> QListScreen(
                onNavigateToQues = onNavigateToQues,
                onNavigateToQuesCreation = onNavigateToQuesCreation
                )
            1-> TasksScreen()
            2-> MineScreen()
        }
    }
}

@Preview
@Composable
fun MainFramePreview(){
    MainFrame()
}