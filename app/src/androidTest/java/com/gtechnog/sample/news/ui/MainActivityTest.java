package com.gtechnog.sample.news.ui;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import com.gtechnog.sample.news.R;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    private MainActivity mainActivity;

    @Before
    public void setUp() throws Exception {
        mainActivity = activityTestRule.getActivity();
    }

    @Test
    public void testLaunchActivity() {
        View view = mainActivity.findViewById(R.id.root_container);
    }

    @After
    public void tearDown() throws Exception {
        mainActivity = null;
        activityTestRule.finishActivity();
    }
}