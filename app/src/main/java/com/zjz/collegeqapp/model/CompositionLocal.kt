package com.zjz.collegeqapp.model

import androidx.compose.runtime.compositionLocalOf
import com.zjz.collegeqapp.ui.viewmodel.UserViewModel

val LocalUserViewModel = compositionLocalOf<UserViewModel> { error("UserViewModel Context Not Found") }