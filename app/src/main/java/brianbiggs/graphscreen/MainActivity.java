package brianbiggs.graphscreen;

import android.app.Activity;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageButton;


public class MainActivity extends Activity {
    private MyView drawingSurface;
    private ScaleGestureDetector scaleGestureDetector;
    private Matrix matrix = new Matrix();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton b1 = (ImageButton)findViewById(R.id.reverse_btn);
        ImageButton b2 = (ImageButton)findViewById(R.id.advance_btn);
        drawingSurface = (MyView)findViewById(R.id.drawing_view);
        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingSurface.setPx(drawingSurface.getPx()+10);
                drawingSurface.invalidate();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingSurface.setPx(drawingSurface.getPx()-10);
                drawingSurface.invalidate();
            }
        });
    }
    public boolean onTouchEvent(MotionEvent ev) {
        scaleGestureDetector.onTouchEvent(ev);
        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float scaleFactor = detector.getScaleFactor();
            scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 5.0f));

            matrix.setScale(scaleFactor, scaleFactor);
            drawingSurface.setDx(drawingSurface.getDx()+10);
            drawingSurface.invalidate();
            return true;
        }
    }

}
