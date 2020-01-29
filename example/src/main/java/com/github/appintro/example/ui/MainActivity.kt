package com.github.appintro.example.ui

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.github.appintro.example.ui.fragment.MainTabsFragment
import com.github.appintro.example.ui.fragment.PermissionTabsFragment
import com.github.paolorotolo.appintroexample.R
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(toolbar) {
            title = resources.getString(R.string.app_name)
            setTitleTextColor(Color.parseColor("#FFFFFF"))
        }

        DrawerBuilder()
                .withActivity(this)
                .withHeader(R.layout.util_drawer_hdr)
                .withToolbar(toolbar)
                .addDrawerItems(
                        PrimaryDrawerItem()
                                .withIdentifier(1)
                                .withName("Home"),
                        PrimaryDrawerItem()
                                .withIdentifier(2)
                                .withName("Permissions")
                )
                .withOnDrawerItemClickListener {_, _, drawerItem ->
                    var fragment: Fragment = MainTabsFragment()

                    when (drawerItem.identifier.toInt()) {
                        1 -> fragment = MainTabsFragment()
                        2 -> fragment = PermissionTabsFragment()
                    }

                    supportFragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit()
                    false
                }
                .withShowDrawerOnFirstLaunch(true)
                .withFireOnInitialOnClick(true)
                .withSavedInstance(savedInstanceState)
                .build()
    }
}