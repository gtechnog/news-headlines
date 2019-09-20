package com.gtechnog.sample.news.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.gtechnog.sample.network.api.Callback;
import com.gtechnog.sample.network.repository.HeadlinesRepository;
import com.gtechnog.sample.news.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewModelProviders.of(this, )
    }
}
