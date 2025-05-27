package com.student.mpbackoffice

import android.app.Application
import com.student.mpbackoffice.di.initKoin
import com.student.mpbackoffice.di.sharedModule
import org.koin.android.ext.koin.androidContext

class MP_Application : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MP_Application)
            modules(sharedModule)
        }
    }
}