package com.student.mpbackoffice

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform