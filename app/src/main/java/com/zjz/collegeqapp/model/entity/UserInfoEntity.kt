package com.zjz.collegeqapp.model.entity

class UserInfoEntity(private val userName:String,private val password:String) {

    var pointOfToday:Int = 0
        private set
    var pointOfMonth:Int = 0
        private set

    fun getUserName():String{
        return userName
    }

    fun getPassword():String{
        return password
    }

    override fun toString(): String {
        return "UserInfoEntity(userName='$userName', password='$password')"
    }
}