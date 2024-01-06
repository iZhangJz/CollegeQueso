package com.zjz.collegeqapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.zjz.collegeqapp.model.entity.UserInfoEntity
import com.zjz.collegeqapp.utils.SQLiteHelper

class UserViewModel(private val dbHelper: SQLiteHelper): ViewModel(){

    private var user : UserInfoEntity? = null

    //  用户登录状态
    val isLogin : Boolean
        get(){
            return user != null
        }

    fun login(userName: String,password: String): Boolean{
        if (!dbHelper.checkUserExist(userName,dbHelper.readableDatabase)){
            // 数据库不存在该用户，登录失败
            return false
        }
        // 检查密码是否正确
        if (!dbHelper.verifyPassword(userName,password)){
            return false
        }
        return true
    }
}