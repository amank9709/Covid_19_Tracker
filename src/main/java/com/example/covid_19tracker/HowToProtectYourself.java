package com.example.covid_19tracker;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;

import androidx.appcompat.app.AppCompatActivity;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import technolifestyle.com.imageslider.FlipperLayout;
import technolifestyle.com.imageslider.FlipperView;

public class HowToProtectYourself extends AppCompatActivity {

    FlipperLayout flipperLayout;
    ProgressBar progressBar;
    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_protect_yourself);
        flipperLayout = findViewById(R.id.flipper_layout);
        progressBar = findViewById(R.id.ImageProgressBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setFlipperImage();


    }

    private void setFlipperImage() {

        String[] url = {"https://www.who.int/images/default-source/health-topics/coronavirus/risk-communications/general-public/protect-yourself/blue-1.tmb-1920v.png?sfvrsn=3d15aa1c_4",
                "https://www.who.int/images/default-source/health-topics/coronavirus/risk-communications/general-public/protect-yourself/blue-2.tmb-1920v.png?sfvrsn=2bc43de1_4",
                "https://www.who.int/images/default-source/health-topics/coronavirus/risk-communications/general-public/protect-yourself/blue-3.tmb-1920v.png?sfvrsn=b1ef6d45_4",
                "https://www.who.int/images/default-source/health-topics/coronavirus/risk-communications/general-public/protect-yourself/blue-4.tmb-1920v.png?sfvrsn=a5317377_14",
                "https://www.who.int/images/default-source/health-topics/coronavirus/risk-communications/general-public/safe-greetings.tmb-1920v.png?sfvrsn=2e97004e_4",
                "https://www.who.int/images/default-source/health-topics/coronavirus/risk-communications/general-public/wearing-gloves.tmb-1920v.png?sfvrsn=ec69b46a_4",
                "https://www.who.int/images/default-source/health-topics/coronavirus/eng-mythbusting-ncov-(13).tmb-1920v.png?sfvrsn=d2a2dc01_1",
                "https://www.who.int/images/default-source/health-topics/coronavirus/eng-mythbusting-ncov-(30).tmb-768v.png?sfvrsn=c0e196fb_2",
                "https://www.who.int/images/default-source/health-topics/coronavirus/eng-mythbusting-ncov-(19).tmb-1920v.png?sfvrsn=99db25de_1",
                "https://www.who.int/images/default-source/health-topics/coronavirus/eng-mythbusting-ncov-(23).tmb-1920v.png?sfvrsn=b399c676_1",
                "https://www.who.int/images/default-source/health-topics/coronavirus/eng-mythbusting-ncov-(33).tmb-768v.png?sfvrsn=a54904b3_1",
                "https://www.who.int/images/default-source/health-topics/coronavirus/social-media-squares/be-ready-social-3.tmb-1920v.jpg?sfvrsn=1706a18f_24",
                "https://www.who.int/images/default-source/health-topics/coronavirus/social-media-squares/be-ready-social-2.tmb-1920v.jpg?sfvrsn=28a6f92d_4",
                "https://www.who.int/images/default-source/health-topics/coronavirus/social-media-squares/be-ready-social-1.tmb-1920v.jpg?sfvrsn=c81745a7_4",
                "https://www.who.int/images/default-source/health-topics/coronavirus/social-media-squares/be-smart-if-you-develop.tmb-1920v.jpg?sfvrsn=1486258a_21",
                "https://www.who.int/images/default-source/health-topics/coronavirus/social-media-squares/be-smart-inform.tmb-1920v.jpg?sfvrsn=f6dbe358_24",
                "https://www.who.int/images/default-source/health-topics/coronavirus/social-media-squares/be-safe.tmb-1920v.jpg?sfvrsn=1f6e4aef_24",
                "https://www.who.int/images/default-source/health-topics/coronavirus/social-media-squares/be-kind-to-support.tmb-1920v.jpg?sfvrsn=1856f2a3_25",
                "https://www.who.int/images/default-source/health-topics/coronavirus/social-media-squares/be-kind-to-address-stigma.tmb-1920v.jpg?sfvrsn=4615bfbe_24",
                "https://www.who.int/images/default-source/health-topics/coronavirus/social-media-squares/be-kind-to-address-fear.tmb-1920v.jpg?sfvrsn=a8e99f14_24",
                "https://www.who.int/images/default-source/health-topics/coronavirus/risk-communications/home-care-posters/home-care-ill-people-a4-covid.tmb-1920v.png?sfvrsn=71718c4b_4",
                "https://www.who.int/images/default-source/health-topics/coronavirus/risk-communications/home-care-posters/home-care-everyone-a4-covid.tmb-1920v.png?sfvrsn=29ba54d8_4",
                "https://www.who.int/images/default-source/health-topics/coronavirus/risk-communications/home-care-posters/home-care-caregivers-a4-covid.tmb-1920v.png?sfvrsn=8c412ecd_4",
                "https://www.who.int/images/default-source/health-topics/coronavirus/risk-communications/home-care-social-stills/home-care-ill-people-square-covid.tmb-1920v.png?sfvrsn=cc2c6ce4_4",
                "https://www.who.int/images/default-source/health-topics/coronavirus/risk-communications/home-care-social-stills/home-care-everyone-square-covid.tmb-1920v.png?sfvrsn=8667ecde_4",
                "https://www.who.int/images/default-source/health-topics/coronavirus/risk-communications/home-care-social-stills/home-care-caregivers-square-covid.tmb-1920v.png?sfvrsn=1fa44d91_4",

        };


        progressBar.setVisibility(View.VISIBLE);


        new java.util.Timer().schedule(

                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        // your code here, and if you have to refresh UI put this code:
                        runOnUiThread(new Runnable() {
                            public void run() {

                                for (int i = 0; i < url.length; i++) {
                                    FlipperView view = new FlipperView(getBaseContext());
                                    view.setImageScaleType(ImageView.ScaleType.FIT_XY);
                                    flipperLayout.setCircleIndicatorHeight(20);
                                    flipperLayout.setCircleIndicatorWidth(300);
                                    flipperLayout.removeCircleIndicator();
                                    flipperLayout.showCircleIndicator();
                                    view.setOnFlipperClickListener(new FlipperView.OnFlipperClickListener() {
                                        @Override
                                        public void onFlipperClick(@NotNull FlipperView flipperView) {
                                        }
                                    });
                                    try {
                                        view.setImage(url[i], new Function2<ImageView, Object, Unit>() {
                                            @Override
                                            public Unit invoke(ImageView imageView, Object image) {
                                                Picasso.get().load((String) image).into(imageView);
                                                return Unit.INSTANCE;
                                            }
                                        });
                                    } catch (MalformedURLException e) {
                                        e.printStackTrace();
                                    }
                                    flipperLayout.addFlipperView(view);
                                    progressBar.setVisibility(View.INVISIBLE);

                                }

                                //your code

                            }
                        });
                    }
                },
                1500
        );


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideDown(this);
    }


}




