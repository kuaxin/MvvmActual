package com.youjiaoyule.mvvmactual.base

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.youjiaoyule.mvvmactual.base.handler.BaseHandler

/**
 *  @author RenGX on 2020/6/11
 *
 */
abstract class BaseFragment:Fragment() {
    protected lateinit var loadService: LoadService<*>

    private var isInitView = false // 界面初始化是否完成

    private var isFirstLoad = true //是否首次加载

    private var isVisibleToUser = false //对用户是否可见

    private lateinit var mHandler:Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //初始化Handler
        mHandler = BaseHandler(this)
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

    //在OnViewCreate执行完后立即执行
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        //数据懒加载
        lazyLoad()
    }

    open fun lazyLoad() {

        if(!isVisibleToUser || !isFirstLoad){
            //如果已经加载过数据就不在加载数据
            return
        }
        initData()
        isFirstLoad = false
    }

    override fun onResume() {
        super.onResume()
        this.isVisibleToUser = true
    }

    override fun onPause() {
        super.onPause()
        this.isVisibleToUser = false
    }

    //ViewPager等容器中走这个方法
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        this.isVisibleToUser = !hidden
        lazyLoad()
    }


    abstract fun initView()

    abstract fun initData()

    open fun reLoad() = lazyLoad()

    abstract fun getLayoutId(): Int

    abstract fun onHandlerReceive(msg: Message)

}