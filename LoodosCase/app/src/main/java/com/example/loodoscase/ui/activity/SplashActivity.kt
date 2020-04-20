package com.example.loodoscase.ui.activity

import android.os.Bundle
import android.os.Handler
import com.example.loodoscase.R
import com.example.loodoscase.core.BaseActivity
import com.example.loodoscase.helper.startActivity
import com.example.loodoscase.util.Const
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity() {

    private lateinit var mRemoteConfig: FirebaseRemoteConfig
    private var SPLASH_TIME = 3000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mRemoteConfig = FirebaseRemoteConfig.getInstance()

        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(3600L)
            .build()

        mRemoteConfig.setDefaultsAsync(R.xml.remote_config)

        mRemoteConfig.setConfigSettingsAsync(configSettings)
            .addOnCompleteListener(this) {

                val txtLoodos = mRemoteConfig.getString(Const.LOODOS_TEXT)
                tvTitle.text = txtLoodos

                Handler().postDelayed({
                    startActivity<HomeActivity>()
                    finish()
                }, SPLASH_TIME)

            }
    }

}