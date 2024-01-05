package com.zjz.collegeqapp.ui.screen.secondlevel


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun QuestionnaireDetailsScreen(onBack: () -> Unit){

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "问卷", modifier = Modifier.padding(horizontal = 114.dp), textAlign = TextAlign.Center, fontSize = 18.sp) },
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
        Text(text = "这是内容")
    }
}