package com.example.epubreader

import android.app.Application
import com.folioreader.Config
import com.folioreader.FolioReader

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val config = Config()
            .setFont("Lato")
            .setFontSize(2)
            .setTheme(Config.Theme.DARK)
            .setDirection(Config.Direction.VERTICAL)

        FolioReader.get().setConfig(config, true)
    }
}
