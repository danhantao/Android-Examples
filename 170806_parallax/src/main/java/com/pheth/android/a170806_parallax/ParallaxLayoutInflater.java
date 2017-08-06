package com.pheth.android.a170806_parallax;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

/**
 * *************************************************************************
 *
 * @创建人: danhantao
 * @创建时间: danhantao(2017-08-06 16:03)
 * <p>
 * <p>
 * *************************************************************************
 */
public class ParallaxLayoutInflater extends LayoutInflater{

    private ParallaxFragment fragment;


    protected ParallaxLayoutInflater(LayoutInflater original, Context newContext,ParallaxFragment fragment) {
        super(original, newContext);
        this.fragment = fragment;
        setFactory(new ParallaxFactory(this));
    }



    @Override
    public LayoutInflater cloneInContext(Context context) {
        return new ParallaxLayoutInflater(this,context,fragment);
    }

    // 自定义工厂类，试图创建工厂类
    class ParallaxFactory implements Factory{

        private LayoutInflater inflater;

        public ParallaxFactory(LayoutInflater inflater) {
            this.inflater = inflater;
        }

        @Override
        public View onCreateView(String s, Context context, AttributeSet attributeSet) {
            // 实例化view
            View view = null;
            if (view == null){
                view = createView(s,context,attributeSet);
            }
            if (view != null){
                // 获取自定义属性，并将自定义标签绑定到view是上面
                getCustomAttrs(context,attributeSet,view);
                fragment.getViews().add(view);
                Log.i("danhantao","加载视图:"+view);
            }
            return view;
        }

        private void getCustomAttrs(Context context, AttributeSet attributeSet, View view) {
              // 所有自定义的属性
            int[] attrIds = {
                    R.attr.a_in,
                    R.attr.a_out,
                    R.attr.x_in,
                    R.attr.x_out,
                    R.attr.y_in,
                    R.attr.y_out
            };
            TypedArray typeArrays = context.obtainStyledAttributes(attributeSet,attrIds);
            if (typeArrays != null && typeArrays.length() > 0){
                ParallxViewTag tag = new ParallxViewTag();
                tag.alphaIn = typeArrays.getFloat(0,0f);
                tag.alphaOut = typeArrays.getFloat(1,0f);
                tag.xIn = typeArrays.getFloat(2,0f);
                tag.xOut = typeArrays.getFloat(3,0f);
                tag.yIn = typeArrays.getFloat(4,0f);
                tag.yOut = typeArrays.getFloat(5,0f);
                view.setTag(R.id.parallax_view_tag,tag);
            }
            typeArrays.recycle();

        }

        private View createView(String name,String prefix,Context context,AttributeSet attr){
            try {
                return inflater.createView(name,prefix,attr);
            }catch (Exception e){
                return null;
            }
        }

        private final String[] prefixs = {
                "android.widget.",
                "android.view."
        };


        private View createView(String name,Context context,AttributeSet attr){
            // 自定义控件
            if (name.contains(".")){
                //自定义控件，已经是全类名了
                return createView(name,null,context,attr);
            }else {
//				android.widget.ImageView   "android.widget."+name
//				android.view.SurfaceView
                for (String prefix : prefixs){
                    final View view = createView(name, prefix, context, attr);
                    if (view != null){
                        return view;
                    }
                }

            }
            return null;
        }
    }


}
