package com.github.app.javaexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.github.app.javaexample.ui.IntroAdapter;
import com.github.app.javaexample.ui.IntroEntry;
import com.github.app.javaexample.ui.custom.CustomBackgroundIntro;
import com.github.app.javaexample.ui.custom.CustomLayoutIntro;
import com.github.app.javaexample.ui.custom.SlidePolicyIntro;
import com.github.app.javaexample.ui.defaullt.DefaultIntro;
import com.github.app.javaexample.ui.defaullt.DefaultIntro2;
import com.github.app.javaexample.ui.permissions.PermissionsIntro;
import com.github.app.javaexample.ui.permissions.PermissionsIntro2;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<IntroEntry> introEntryList;

    private RecyclerView mainRecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainRecyclerview = findViewById(R.id.main_recycler_view);

        introEntryList = new ArrayList<>();

        mainRecyclerview.setHasFixedSize(true);
        mainRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        initIntroEntry();

        IntroAdapter adapter = new IntroAdapter(introEntryList,this);
        mainRecyclerview.setAdapter(adapter);
    }

    private void initIntroEntry() {
        introEntryList.add(new IntroEntry(R.string.default_intro_title, R.string.default_intro, DefaultIntro.class));
        introEntryList.add(new IntroEntry(R.string.default_intro2_title, R.string.default_intro2, DefaultIntro2.class));
        introEntryList.add(new IntroEntry(R.string.custom_layout_intro_title, R.string.custom_layout_intro, CustomLayoutIntro.class));
        introEntryList.add(new IntroEntry(R.string.custom_background_intro_title, R.string.custom_background_intro, CustomBackgroundIntro.class));
        introEntryList.add(new IntroEntry(R.string.slide_policy_intro_title, R.string.slide_policy_intro, SlidePolicyIntro.class));
        introEntryList.add(new IntroEntry(R.string.perms_intro1_title, R.string.perms_intro1, PermissionsIntro.class));
        introEntryList.add(new IntroEntry(R.string.perms_intro2_title, R.string.perms_intro2, PermissionsIntro2.class));
    }
}
