package com.android.lxf.toolsutil.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.android.lxf.toolsutil.R;


public class RotateLoading extends View {
    private static final int DEFAULT_WIDTH = 6;
    private static final int DEFAULT_SHADOW_POSITION = 2;
    private Paint mPaint;
    private RectF loadingRectF;
    private RectF shadowRectF;
    private int topDegree = 10;
    private int bottomDegree = 190;
    private float arc;
    private int width;
    private boolean changeBigger = true;
    private int shadowPosition;
    private boolean isStart = false;
    private int color;

    public RotateLoading(Context context) {
        super(context);
        this.initView(context, (AttributeSet)null);
    }

    public RotateLoading(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initView(context, attrs);
    }

    public RotateLoading(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        this.color = -1;
        this.width = this.dpToPx(context, 6.0F);
        this.shadowPosition = this.dpToPx(this.getContext(), 2.0F);
        if(null != attrs) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RotateLoading);
            this.color = typedArray.getColor(R.styleable.RotateLoading_loading_color, -1);
            this.width = typedArray.getDimensionPixelSize(R.styleable.RotateLoading_loading_width, this.dpToPx(context, 6.0F));
            this.shadowPosition = typedArray.getInt(R.styleable.RotateLoading_shadow_position, 2);
            typedArray.recycle();
        }

        this.mPaint = new Paint();
        this.mPaint.setColor(this.color);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth((float)this.width);
        this.mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.arc = 10.0F;
        this.loadingRectF = new RectF((float)(2 * this.width), (float)(2 * this.width), (float)(w - 2 * this.width), (float)(h - 2 * this.width));
        this.shadowRectF = new RectF((float)(2 * this.width + this.shadowPosition), (float)(2 * this.width + this.shadowPosition), (float)(w - 2 * this.width + this.shadowPosition), (float)(h - 2 * this.width + this.shadowPosition));
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(this.isStart) {
            this.mPaint.setColor(Color.parseColor("#1a000000"));
            canvas.drawArc(this.shadowRectF, (float)this.topDegree, this.arc, false, this.mPaint);
            canvas.drawArc(this.shadowRectF, (float)this.bottomDegree, this.arc, false, this.mPaint);
            this.mPaint.setColor(this.color);
            canvas.drawArc(this.loadingRectF, (float)this.topDegree, this.arc, false, this.mPaint);
            canvas.drawArc(this.loadingRectF, (float)this.bottomDegree, this.arc, false, this.mPaint);
            this.topDegree += 10;
            this.bottomDegree += 10;
            if(this.topDegree > 360) {
                this.topDegree -= 360;
            }

            if(this.bottomDegree > 360) {
                this.bottomDegree -= 360;
            }

            if(this.changeBigger) {
                if(this.arc < 160.0F) {
                    this.arc = (float)((double)this.arc + 2.5D);
                    this.invalidate();
                }
            } else if(this.arc > 10.0F) {
                this.arc -= 5.0F;
                this.invalidate();
            }

            if(this.arc == 160.0F || this.arc == 10.0F) {
                this.changeBigger = !this.changeBigger;
                this.invalidate();
            }

        }
    }

    public void start() {
        this.startAnimator();
        this.isStart = true;
        this.invalidate();
    }

    public void stop() {
        this.stopAnimator();
        this.invalidate();
    }

    public boolean isStart() {
        return this.isStart;
    }

    private void startAnimator() {
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(this, "scaleX", new float[]{0.0F, 1.0F});
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(this, "scaleY", new float[]{0.0F, 1.0F});
        scaleXAnimator.setDuration(300L);
        scaleXAnimator.setInterpolator(new LinearInterpolator());
        scaleYAnimator.setDuration(300L);
        scaleYAnimator.setInterpolator(new LinearInterpolator());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{scaleXAnimator, scaleYAnimator});
        animatorSet.start();
    }

    private void stopAnimator() {
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(this, "scaleX", new float[]{1.0F, 0.0F});
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(this, "scaleY", new float[]{1.0F, 0.0F});
        scaleXAnimator.setDuration(300L);
        scaleXAnimator.setInterpolator(new LinearInterpolator());
        scaleYAnimator.setDuration(300L);
        scaleYAnimator.setInterpolator(new LinearInterpolator());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{scaleXAnimator, scaleYAnimator});
        animatorSet.addListener(new Animator.AnimatorListener() {
            public void onAnimationStart(Animator animation) {
            }

            public void onAnimationEnd(Animator animation) {
                RotateLoading.this.isStart = false;
            }

            public void onAnimationCancel(Animator animation) {
            }

            public void onAnimationRepeat(Animator animation) {
            }
        });
        animatorSet.start();
    }

    public int dpToPx(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(1, dpVal, context.getResources().getDisplayMetrics());
    }
}