package com.lxtest.view;

import android.graphics.Color;
import android.widget.TextView;


import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

/**
 * Created by liangxin on 2016/9/8.
 */public class MyTextView extends SimpleViewManager<TextView>{
    @Override
    public String getName() {
        return "MyTextView";
    }

    @Override
    protected TextView createViewInstance(ThemedReactContext context){
        TextView mTextView=new TextView(context);
        return mTextView;
    }


    @ReactProp(name="text")
    public void setText(TextView view,String text){
        view.setText(text);
    }
    @ReactProp(name="textSize")
    public void setTextSize(TextView view,float fontSize){
        view.setTextSize(fontSize);
    }

   /* @ReactProp(name="textColor",defaultInt = Color.BLACK)
    public void setColor(TextView view, int textColor ){

        view.setTextColor(textColor);
    }*/
    @ReactProp(name="isAlpha",defaultBoolean = false)
    public void setTextAlpha(TextView view, boolean  isAlpha){
        if(isAlpha){
            view.setAlpha(0.5f);
        }else{}
    }

}
