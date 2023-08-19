package me.bakhtiyari.cryptocurrency.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import me.bakhtiyari.cryptocurrency.presentation.viewmodel.BaseViewModel


abstract class BaseFragment<VDB : ViewDataBinding, ViewModel : BaseViewModel>(private val layout: Int) :
    Fragment() {

    protected lateinit var binding: VDB
    protected abstract val viewModel: ViewModel

    private val errorMessageObserver = Observer<String> {
        showError(it)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, layout, container, false)
        binding.lifecycleOwner = this

        viewModel.errorMessage.observe(viewLifecycleOwner, errorMessageObserver)
        initVariables()
        initObserves()
        initViews()


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewsCreated(binding, viewModel)
    }

    abstract fun initVariables()
    abstract fun initObserves()
    abstract fun initViews()
    abstract fun viewsCreated(binding: VDB, vm: ViewModel)

    fun showError(error: String) {
        (activity as BaseActivity<*, *>).showError(error)
    }

    fun showMessage(message: String) {
        (activity as BaseActivity<*, *>).showMessage(message)
    }

}