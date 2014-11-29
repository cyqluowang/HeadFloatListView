package com.cyq.showbottom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListAdapter;
import android.widget.ListView;



/**
 * 底部View自动隐藏和消失listview(其他ListView可以继承该类，如CtripHeadRefreshListView类等)
 * 
 * @author zhiwen.nan
 * @Date 2013-9-28 下午3:35:15
 * 
 */
public class HeadFloatListView extends ListView implements OnScrollListener {

    public View mHeadBar;
    private int mCurrentScrollState;
    private int oldFirstVisibleItem = 0;
    private static final String TAG = "HeadFloatListView";


  public HeadFloatListView(Context context) {
    this(context, null);
    super.setOnScrollListener(this);
  }

  public HeadFloatListView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
    super.setOnScrollListener(this);
  }

  public HeadFloatListView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    super.setOnScrollListener(this);
  }


  @Override
  public void setAdapter(ListAdapter adapter) {
    super.setAdapter(adapter);
  }


  @Override
  public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
	  showHeadViewOnHead(firstVisibleItem,visibleItemCount,totalItemCount);

  }

  @Override
  public void onScrollStateChanged(AbsListView view, int scrollState) {
	  hideHeadViewOnScrollStateChanged(view,scrollState);
  }

  float StartY = 0;
  Boolean isRuning = false;
	@SuppressLint("ClickableViewAccessibility") 
	@Override
  public boolean onTouchEvent(MotionEvent ev) {
		float y = ev.getY();
		int action = ev.getAction() & MotionEvent.ACTION_MASK;
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			StartY = y;
			break;
		case MotionEvent.ACTION_MOVE:
			if (!isRuning) {
				if ((y - StartY) < 0) {
					hideHeadBar();
				}

				if ((y - StartY) > 0) {
					showHeadBar();
				}
			}
			break;
		case MotionEvent.ACTION_UP:
			StartY = 0;
			break;
		}
		return super.onTouchEvent(ev);
  }
    
    /**
     * 滑动到顶部时，要隐藏HeadView
     * @param view
     * @param scrollState
     */
    private void hideHeadViewOnScrollStateChanged(AbsListView view, int scrollState) {
        mCurrentScrollState = scrollState;
        if(view!=null){
        	 if (view.getFirstVisiblePosition() == 0 && scrollState == SCROLL_STATE_IDLE) {
//        		 showHeadBar();
                 Log.d(TAG, "hide Head view");
             }
        }
    }

    /**
     * 显示底部浮动栏
     */
    private Animation translateAnimation = null; 
    public void showHeadBar() {
        if (mHeadBar != null && mHeadBar.getVisibility() == View.GONE) {
            translateAnimation = AnimationUtils.loadAnimation(this.getContext(), R.anim.in_anim);  
            mHeadBar.startAnimation(translateAnimation);  
            translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                	mHeadBar.setVisibility(View.VISIBLE);
                	isRuning = true;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                	isRuning = false;
                }
            });
        }
    }

    /**
     * 隐藏浮动底部栏
     */
    private void hideHeadBar() {

        if (mHeadBar != null  && mHeadBar.getVisibility() == View.VISIBLE) {
             translateAnimation = AnimationUtils.loadAnimation(this.getContext(), R.anim.out);  
             mHeadBar.startAnimation(translateAnimation);  
             translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                	mHeadBar.setVisibility(View.GONE);
                	isRuning = true;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                	isRuning = false;
                }
            });
        }
    }

    /**
     * 滑动到底部时直接显示HeadView
     * @param visibleItemCount
     * @param totalItemCount
     * @param firstVisibleItem
     */
    private void showHeadViewOnHead(int visibleItemCount, int totalItemCount, int firstVisibleItem) {
        
        	Log.d(TAG, "visible bottem item count:"  + "firstVisibleItem:" +  firstVisibleItem + "oldFirstVisibleItem:" + oldFirstVisibleItem + mHeadBar);
             if(getLastVisiblePosition() ==   totalItemCount -1 && mCurrentScrollState != SCROLL_STATE_IDLE){
//                 showHeadBar();
             }
    }



    /**
   * 将需要隐藏显示的view传入
   * 
   * @param HeadBar
   */
  public void setHeadBar(ViewGroup HeadBar) {
    this.mHeadBar = HeadBar;
  }

}
