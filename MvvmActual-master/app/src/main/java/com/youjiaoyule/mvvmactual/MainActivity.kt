package com.youjiaoyule.mvvmactual

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.youjiaoyule.mvvmactual.activity.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val homeFragment = HomeFragment(0)
        val mineFragment = HomeFragment(1)
        val classifyFragment = HomeFragment(2)

        supportFragmentManager.beginTransaction().replace(R.id.fl_main,homeFragment).show(homeFragment).commit()

        bottom_navigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_home ->{
                    supportFragmentManager.beginTransaction().replace(R.id.fl_main,homeFragment).commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.menu_mine ->{
                    supportFragmentManager.beginTransaction().replace(R.id.fl_main,mineFragment).commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.menu_classify ->{
                    supportFragmentManager.beginTransaction().replace(R.id.fl_main,classifyFragment).commit()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })

    }
}