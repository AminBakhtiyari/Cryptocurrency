package me.bakhtiyari.cryptocurrency.ui.main

import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import me.bakhtiyari.cryptocurrency.R
import me.bakhtiyari.cryptocurrency.base.BaseActivity
import me.bakhtiyari.cryptocurrency.databinding.ActivityMainBinding
import me.bakhtiyari.cryptocurrency.presentation.viewmodel.MainViewModel

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    override val viewModel: MainViewModel by viewModels()

    override fun initVariables() {
        binding.viewModel = viewModel
    }

    override fun initObserves() {
    }

    override fun initViews() {
    }
}