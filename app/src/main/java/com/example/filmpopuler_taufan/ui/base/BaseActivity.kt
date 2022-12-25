package com.example.filmpopuler_taufan.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VM : ViewModel, VB : ViewBinding> : AppCompatActivity() {

    //membuat view model dengan kode VM
    //dan membuat ViewBinding dengan kode VB
    protected abstract val mViewModel: VM

    protected lateinit var mViewBinding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = getViewBinding()
    }


    abstract fun getViewBinding(): VB
}