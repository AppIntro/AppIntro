package com.amqtech.opensource.appintroexample.ui;

import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

import com.amqtech.opensource.appintroexample.ui.fragment.MainTabsFragment;
import com.amqtech.opensource.appintroexample.ui.fragment.PermissionTabsFragment;
import com.github.paolorotolo.appintroexample.R;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar tb = findViewById(R.id.toolbar);
        tb.setTitle(getResources().getString(R.string.app_name));
        tb.setTitleTextColor(Color.parseColor("#FFFFFF"));

        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Home");
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("Permissions");

        new DrawerBuilder()
                .withActivity(this)
                .withHeader(R.layout.util_drawer_hdr)
                .withToolbar(tb)
                .addDrawerItems(
                        item1,
                        item2
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        if (drawerItem != null) {
                            Fragment fragment = null;
                            FragmentManager fragmentManager = getSupportFragmentManager();

                            switch ((int) drawerItem.getIdentifier()) {
                                case 1:
                                    fragment = new MainTabsFragment();
                                    break;
                                case 2:
                                    fragment = new PermissionTabsFragment();
                                    break;
                            }

                            if (fragment != null) {
                                fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
                            }

                            if (drawerItem instanceof Nameable) {
                                setTitle(((Nameable) drawerItem).getName().getText(getApplicationContext()));
                            }
                        }

                        return false;
                    }
                })
                .withShowDrawerOnFirstLaunch(true)
                .withFireOnInitialOnClick(true)
                .withSavedInstance(savedInstanceState)
                .build();
    }
}
