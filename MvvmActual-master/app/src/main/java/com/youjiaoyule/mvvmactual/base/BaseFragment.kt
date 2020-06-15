package com.youjiaoyule.mvvmactual.base

import android.app.Activity
import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir

/**
 *  @author RenGX on 2020/6/11
 *
 */
abstract class BaseFragment:Fragment() {
    protected lateinit var loadService: LoadService<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(getLayoutId(), null)
        loadService = LoadSir.getDefault().register(rootView){
            reLoad()
        }

        return loadService.loadLayout
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initData()

    }

    abstract fun initView()

    abstract fun initData()

    open fun reLoad() = initData()

    abstract fun getLayoutId(): Int

    abstract fun onHandlerReceive(msg: Message)
}