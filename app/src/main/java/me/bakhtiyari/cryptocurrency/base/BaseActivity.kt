package me.bakhtiyari.cryptocurrency.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import me.bakhtiyari.cryptocurrency.presentation.viewmodel.BaseViewModel
import me.bakhtiyari.cryptocurrency.utils.SnackBarType
import me.bakhtiyari.cryptocurrency.utils.showSnackBar


abstract class BaseActivity<VDB : ViewDataBinding, ViewModel : BaseViewModel>(
    private val layout: Int
) : AppCompatActivity() {

    protected lateinit var binding: VDB
    protected abstract val viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layout)
        binding.lifecycleOwner = this


        initVariables()
        initObserves()
        initViews()

    }

    abstract fun initVariables()
    abstract fun initObserves()
    abstract fun initViews()

    fun showError(error: String) {
        showSnackBar(view = binding.root, message = error, type = SnackBarType.ERROR)
    }

    fun showMessage(message: String) {
        showSnackBar(view = binding.root, message = message, type = SnackBarType.NORMAL)
    }


}