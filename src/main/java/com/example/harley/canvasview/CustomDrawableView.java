package com.example.harley.canvasview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.PathShape;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class CustomDrawableView extends View {
    public String test = "Testing"
    public List<ShapeDrawable> drawset = new ArrayList<ShapeDrawable>();

    public CustomDrawableView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomDrawableView(Context context) {
        super(context);
    }

    public Point lastPoint;
    private Point curPoint;

    public void setcurPoint(Point p) {
        lastPoint = curPoint;
        curPoint = p;
    }


    public void add_shape(int x, int y, int width, int height, Shape type, int color) {
        ShapeDrawable mDrawable;
        mDrawable = new ShapeDrawable(type);
        mDrawable.getPaint().setColor(color);
        mDrawable.setBounds(x, y, x + width, y + height);

        drawset.add(mDrawable);
        this.invalidate();
    }

    public void add_shape(int x, int y, int width, int height, Shape type) {
        ShapeDrawable mDrawable;
        mDrawable = new ShapeDrawable(type);
        mDrawable.getPaint().setColor(0xFFFFFFFF);
        mDrawable.setBounds(x, y, x + width, y + height);
        drawset.add(mDrawable);
        this.invalidate();
    }

    public void add_line(int x1, int y1, int x2, int y2, int width, int height, int color) {
        System.out.println("Drew A Line!");
        Path path = new Path();
        //---
        //-12
        //---
        if (y1 == y2 && x1 < x2) {
            path.moveTo(x1, y1);
            path.lineTo(x2 + width, y2);
            path.lineTo(x2 + width, y2 + height);
            path.lineTo(x1, y1 + height);
            path.lineTo(x1, y1);

        }
        //---
        //21-
        //---
        if (y1 == y2 && x1 > x2) {
            path.moveTo(x1 + width, y1);
            path.lineTo(x1 + width, y1 + height);
            path.lineTo(x2, y2 + height);
            path.lineTo(x2, y2);
            path.lineTo(x1 + width, y1);

        }
        //---
        //-1-
        //-2-
        if (x1 == x2 && y1 < y2) {
            path.moveTo(x1, y1);
            path.lineTo(x1 + width, y1);
            path.lineTo(x2 + width, y2 + height);
            path.lineTo(x2, y2 + height);
            path.lineTo(x1, y1);

        }
        //-2-
        //-1-
        //---
        if (x1 == x2 && y2 < y1) {
            path.moveTo(x1, y1 + height);
            path.lineTo(x2, y2);
            path.lineTo(x2 + width, y2);
            path.lineTo(x1 + width, y1 + height);
            path.lineTo(x1, y1 + height);

        }
        //2--
        //-1-
        //---
        if (x2 < x1 && y2 < y1) {
            path.moveTo(x1, y1 + height);
            path.lineTo(x2, y2 + height);
            path.lineTo(x2, y2);
            path.lineTo(x2 + width, y2);
            path.lineTo(x1 + width, y1);
            path.lineTo(x1 + width, y1 + height);
            path.lineTo(x1, y1 + height);

        }
        //---
        //-1-
        //2--
        if (x2 < x1 && y1 < y2) {
            path.moveTo(x1, y1);
            path.lineTo(x2, y2);
            path.lineTo(x2, y2 + height);
            path.lineTo(x2 + width, y2 + height);
            path.lineTo(x1 + width, y1 + height);
            path.lineTo(x1 + width, y1);
            path.lineTo(x1, y1);

        }
        //---
        //-1-
        //--2
        if (x2 > x1 && y1 < y2) {
            path.moveTo(x1, y1);
            path.lineTo(x1 + width, y1);
            path.lineTo(x2 + width, y2);
            path.lineTo(x2 + width, y2 + height);
            path.lineTo(x2, y2 + height);
            path.lineTo(x1, y1 + height);
            path.lineTo(x1, y1);

        }
        //--2
        //-1-
        //---
        if (x2 > x1 && y1 > y2) {
            path.moveTo(x1, y1);
            path.lineTo(x2, y2);
            path.lineTo(x2 + width, y2);
            path.lineTo(x2 + width, y2 + height);
            path.lineTo(x1 + width, y1 + height);
            path.lineTo(x1, y1 + height);
            path.lineTo(x1, y1);

        }
        add_custom_shape(new PathShape(path, this.getWidth(), this.getHeight()), color);


    }

    public void add_custom_shape(Shape type, int color) {
        ShapeDrawable mDrawable;
        mDrawable = new ShapeDrawable(type);
        mDrawable.getPaint().setColor(color);
        mDrawable.setBounds(0, 0, this.getWidth(), this.getHeight());
        mDrawable.getPaint().setColor(color);
        mDrawable.getPaint().setStyle(Paint.Style.FILL);
        drawset.add(mDrawable);
        this.invalidate();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        System.out.println(drawset.size());
        for (int i = 0; i < drawset.size(); i++) {
            drawset.get(i).draw(canvas);
        }
    }

}



