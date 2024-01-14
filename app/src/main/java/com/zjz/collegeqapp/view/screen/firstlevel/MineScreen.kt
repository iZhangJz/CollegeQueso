package com.zjz.collegeqapp.view.screen.firstlevel

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zjz.collegeqapp.R
import com.zjz.collegeqapp.model.LocalUserViewModel
import com.zjz.collegeqapp.view.componets.TopAppBar

@Composable
fun MineScreen(){

    val menuList = listOf(
        MenuItem(R.drawable.icon_menu_idcard,"个人资料"),
        MenuItem(R.drawable.icon_menu_history,"浏览记录"),
        MenuItem(R.drawable.icon_menu_tips,"常见问题"),
        MenuItem(R.drawable.icon_menu_setting,"软件设置")
    )

    val user = LocalUserViewModel.current.getUser()

    Column(modifier = Modifier){
        TopAppBar(){
            Text(text = "我的", fontSize = 18.sp, color = Color.White)
        }
        LazyColumn(modifier = Modifier.padding(10.dp)) {
            // 头像部分
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 24.dp).padding(horizontal = 12.dp)
                ) {
                    // 头像
                    Image(
                        painter = painterResource(R.drawable.avatar),
                        contentDescription = null,
                        modifier = Modifier.size(70.dp).clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        verticalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier.padding(horizontal = 16.dp).height(64.dp)
                    ) {
                        // 昵称
                        Text(text = "用户2568", color = Color(0xFF000000), fontSize = 20.sp,  )
                        // 用户名
                        if (user != null) {
                            Text(text = "id: ${user.getUserName()}", color = Color(0xAA333333), fontSize = 14.sp)
                        }
                    }
                }
            }
            // 菜单列表
            items(menuList){ menuList ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                ) {
                    Icon(
                        painter = painterResource(id = menuList.icon),
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                        tint = Color(0xFF149EE7)
                    )

                    Column(
                        modifier = Modifier.padding(horizontal = 8.dp)
                    ){
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                        ){
                            Text(
                                text = menuList.title,
                                fontSize = 16.sp,
                                color = Color(0xFF333333),
                                modifier = Modifier.weight(1f)
                            )
                            Icon(
                                painter = painterResource(R.drawable.icon_double_right),
                                contentDescription = null,
                                modifier = Modifier.width(13.dp).padding(),
                                tint = Color.Gray

                            )
                        }
                        Divider()
                    }
                }

            }
        }
    }

}


//菜单实体类
data class MenuItem(@DrawableRes val icon:Int,val title:String)
