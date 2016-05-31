package com.example.fitssystemwindowstest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // This call is necessary when not calling setContentView.
        getDelegate().onPostCreate(null);

        findViewById(android.R.id.content)
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new RootFragment())
                .commit();
    }

    @SuppressWarnings("WeakerAccess")
    public static final class RootFragment extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//            !!!THIS IS SHIT!!!!
//            FUCKING CoordinatorLayout instead of FrameLayout fixes fitsSystemWindows=true flag in
//                            ALL ADDED FRAGMENTS!!!! SHIT!!!
//            return inflater.inflate(R.layout.root_bad, container, false);
            return inflater.inflate(R.layout.root_good, container, false);
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getFragmentManager().beginTransaction()
                            .add(android.R.id.content, new ChildFragment())
                            .addToBackStack(null)
                            .commit();
                }
            });
        }
    }

    @SuppressWarnings("WeakerAccess")
    public static final class ChildFragment extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.child, container, false);
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            view.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().onBackPressed();
                }
            });
        }
    }
}
