package com.youjiaoyule.mvvmactual

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.youjiaoyule.mvvmactual.activity.home.HomeFragment
import com.youjiaoyule.mvvmactual.base.BaseFragment
import com.youjiaoyule.mvvmactual.utils.FragmentUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var homeFragment:BaseFragment? = null
    var mineFragment:BaseFragment? = null
    var classifyFragment:BaseFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment()
        FragmentUtils.showFragment(homeFragment!!)
        bottom_navigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener {
            FragmentUtils.hideFragments(supportFragmentManager)
            when(it.itemId){
                R.id.menu_home ->{
                    FragmentUtils.showFragment(homeFragment!!)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.menu_mine ->{
                    FragmentUtils.showFragment(mineFragment!!)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.menu_classify ->{
                    FragmentUtils.showFragment(classifyFragment!!)
                    return@OnNavigationItemSelectedListener true
                }
            }

            false
        })
    }

    private fun addFragment() {
        if(homeFragment == null){
            homeFragment = HomeFragment(0)
            FragmentUtils.addFragment(supportFragmentManager,homeFragment!!,R.id.fl_main,true)
        }
        if(mineFragment == null){
            mineFragment = HomeFragment(1)
            FragmentUtils.addFragment(supportFragmentManager,mineFragment!!,R.id.fl_main,true)
        }
        if(classifyFragment == null){
            classifyFragment = HomeFragment(2)
            FragmentUtils.addFragment(supportFragmentManager,classifyFragment!!,R.id.fl_main,true)
        }

    }
}