package com.zly.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.PathInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import static android.view.animation.Animation.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private AlphaAnimation mAlphaAnimation;
    private RotateAnimation mRotateAnimation;
    private Button mStart;
    private Button mStop;
    private ImageView mImageView;
    private Animation mAnimation;
    private TranslateAnimation mTranslateAnimation;
    private RadioButton mRadioButtonAlpha;
    private ScaleAnimation mScaleAnimation;
    private RadioButton mRadioButtonRotate;
    private RadioButton mRadioButtonTranslate;
    private RadioButton mRadioButtonScale;
    private AnimationDrawable rocketAnimation;
    AnimationDrawable animationDrawable;
    private RadioButton mRadioButtonDrawable;
    private RadioGroup mRadioGroupOne;
    private RadioGroup mRadioGroupTwo;

    @Override
    protected void onResume() {
        super.onResume ();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        android.util.Log.d("zly", "zly --> test");
        initView();
        initData();
    }

    private void initView() {
        mImageView = (ImageView)findViewById (R.id.picture);
        mImageView.setOnClickListener (this);
        mStart = (Button)findViewById (R.id.start);
        mStart.setOnClickListener (MainActivity.this);
        mStop = (Button)findViewById (R.id.stop);
        mStop.setOnClickListener (MainActivity.this);
        mRadioButtonAlpha = (RadioButton)findViewById(R.id.alpha);
        mRadioButtonAlpha.setOnClickListener (MainActivity.this);
        mRadioButtonRotate = (RadioButton)findViewById (R.id.rotate);
        mRadioButtonRotate.setOnClickListener (MainActivity.this);
        mRadioButtonTranslate = (RadioButton)findViewById (R.id.translate);
        mRadioButtonTranslate.setOnClickListener (MainActivity.this);
        mRadioButtonScale = (RadioButton)findViewById (R.id.scale);
        mRadioButtonScale.setOnClickListener (MainActivity.this);
        mRadioButtonDrawable = (RadioButton)findViewById (R.id.drawable);
        mRadioButtonDrawable.setOnClickListener (MainActivity.this);
        mRadioGroupOne = (RadioGroup)findViewById (R.id.radioGroupOne);
        mRadioGroupTwo = (RadioGroup)findViewById(R.id.radioGroupTwo);
    }

    private void initData() {
        initAlphaAniamtion();
        initRotateAnimation();
        initTranslateAnimation();
        initScaleAnimation();
        setAnimation(mAlphaAnimation);
    }

    private void initScaleAnimation() {
        mScaleAnimation = new ScaleAnimation (0, 2.0f, 0, 2.0f, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        mScaleAnimation.setDuration (3000);
    }

    private void initTranslateAnimation() {
        mTranslateAnimation = new TranslateAnimation (100f, 200f, 0f, 0f);
        mTranslateAnimation.setDuration (3000);
        mTranslateAnimation.setFillAfter (true);
    }

    private void initAlphaAniamtion() {
        /*
        mAlphaAnimation = new AlphaAnimation (1.0f, 0.0f);
        mAlphaAnimation.setDuration (3000);
        mAlphaAnimation.setAnimationListener (new Animation.AnimationListener () {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        */
        mAlphaAnimation = (AlphaAnimation) AnimationUtils.loadAnimation(MainActivity.this, R.anim.alpha);
    }

    private void initRotateAnimation() {
        mRotateAnimation = new RotateAnimation (0f, -360f, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        mRotateAnimation.setDuration (3000);
        //mRotateAnimation.setAnimationListener ();
    }

    private void initDrawableAnimation() {
//        mImageView.setBackgroundResource(R.drawable.frame_animation);   //方法一设置等同于background
//        rocketAnimation = (AnimationDrawable) mImageView.getBackground();
        mImageView.setImageResource(R.drawable.frame_animation);  //方法二 设置等同于src
        animationDrawable = (AnimationDrawable) mImageView.getDrawable();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId ()) {
            case R.id.start:
                //mAnimation.setInterpolator(new AnticipateOvershootInterpolator ());

                /*if (mRadioGroupTwo.getCheckedRadioButtonId () == R.id.drawable) {
                    animationDrawable.start();
                } else {
                    mImageView.startAnimation (mAnimation);
                }*/

                /*AnimationSet  animationSet = new AnimationSet (true);
                animationSet.setInterpolator(new AccelerateDecelerateInterpolator ());
                animationSet.addAnimation (mAlphaAnimation);
                animationSet.addAnimation (mTranslateAnimation);
                mImageView.startAnimation(animationSet);*/

                propertyAnimation();
                break;
            case R.id.stop:
                if (mRadioGroupTwo.getCheckedRadioButtonId () == R.id.drawable) {
                    animationDrawable.stop ();
                } else {
                    mAnimation.cancel ();
                }
                break;
            case R.id.alpha:
                setAnimation(mAlphaAnimation);
                mRadioGroupTwo.clearCheck ();
                break;
            case R.id.rotate:
                setAnimation(mRotateAnimation);
                mRadioGroupTwo.clearCheck ();
                break;
            case R.id.translate:
                setAnimation(mTranslateAnimation);
                mRadioGroupTwo.clearCheck ();
                break;
            case R.id.scale:
                setAnimation(mScaleAnimation);
                mRadioGroupTwo.clearCheck ();
                break;
            case R.id.drawable:
                initDrawableAnimation();
                mRadioGroupOne.clearCheck ();
                break;
            case R.id.picture:
                Toast.makeText(MainActivity.this, "test!!", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void setAnimation(Animation animation) {
       mAnimation = animation;
    }

    /**
    *  插值器
     */
    public class JellyInterpolator extends LinearInterpolator {
        private float factor;

        public JellyInterpolator() {
            this.factor = 0.15f;
        }

        @Override
        public float getInterpolation(float input) {
            return (float) (Math.pow(2, -10 * input) * Math.sin((input - factor / 4) * (2 * Math.PI) / factor) + 1);
        }
    }

    /**
     * 属性动画：对图片进行透明度的变化
     * */
    private void propertyAnimation() {
        boolean useXmlFlag = true;
        if (useXmlFlag) {
            //one way:
            Animator animator = AnimatorInflater.loadAnimator(MainActivity.this, R.animator.animator_alpha);
            animator.setTarget(mImageView);
            animator.start();
        } else {
            //two way:
            ObjectAnimator alphaAniamtion = ObjectAnimator.ofFloat(mImageView, "alpha", 1.0f, 0.0f);
//            alphaAniamtion.setFrameDelay(1000);
            alphaAniamtion.setDuration(5000);
            alphaAniamtion.setRepeatCount(1);
            alphaAniamtion.setRepeatMode(ValueAnimator.REVERSE);
            alphaAniamtion.start();
        }
    }
}
