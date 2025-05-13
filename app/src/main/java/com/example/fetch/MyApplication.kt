package com.example.fetch

import android.app.Application
import com.example.fetch.koin.initKoin

class MyApplication: Application()  {
    override fun onCreate() {
        super.onCreate()
        initKoin() {
        }
    }
}