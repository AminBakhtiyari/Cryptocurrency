package me.bakhtiyari.cryptocurrency.ui.splash

import android.annotation.SuppressLint
import androidx.activity.viewModels
import me.bakhtiyari.cryptocurrency.R
import me.bakhtiyari.cryptocurrency.base.BaseActivity
import me.bakhtiyari.cryptocurrency.databinding.ActivitySplashBinding
import me.bakhtiyari.cryptocurrency.presentation.utils.EventObserver
import me.bakhtiyari.cryptocurrency.presentation.viewmodel.SplashViewModel
import me.bakhtiyari.cryptocurrency.ui.main.MainActivity
import me.bakhtiyari.cryptocurrency.utils.runActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity :
    BaseActivity<ActivitySplashBinding, SplashViewModel>(R.layout.activity_splash) {

    override val viewModel: SplashViewModel by viewModels()

    override fun initVariables() {
        binding.viewModel = viewModel
    }

    override fun initObserves() {
        viewModel.onStartMainEvent.observe(this, EventObserver {
            if (it) {
                this.runActivity(c = MainActivity::class.java, finish = true)
            }
        })
    }

    override fun initViews() {
        viewModel.initSplashScreen()
    }
}