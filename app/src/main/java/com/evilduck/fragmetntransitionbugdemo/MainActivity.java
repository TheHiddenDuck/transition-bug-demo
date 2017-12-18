package com.evilduck.fragmetntransitionbugdemo;

import android.os.Bundle;
import android.support.transition.Fade;
import android.support.transition.Slide;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.content, new FirstFragment())
                .commit();

        findViewById(R.id.important_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragment();
            }
        });
    }

    private void switchFragment() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.content);

        currentFragment.setExitTransition(new Fade());

        final FirstFragment fragment = new FirstFragment();
        fragment.setEnterTransition(new Slide(Gravity.BOTTOM));

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.content, fragment)
                .hide(currentFragment)
                .addToBackStack(null)
                .commit();
    }

}
