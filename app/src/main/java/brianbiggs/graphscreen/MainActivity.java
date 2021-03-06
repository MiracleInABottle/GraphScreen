package brianbiggs.graphscreen;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
    private MyView drawingSurface;
    private ScaleGestureDetector scaleGestureDetector;
    private int curve;
    double [] xPoints = null;
    double [] yPoints = null;
    DataSeries data = new DataSeries();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final double [] xP1 = {0,0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8};
        final double [] xP2 = {0,0.267,0.50,0.701,0.901,1.101,1.301,1.502,1.702};
        final double [] yP1 = {0,0.267,0.50,0.701,0.901,1.101,1.301,1.502,1.702};
        final double [] yP2 = {0,0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8};

        ImageButton b1 = (ImageButton)findViewById(R.id.reverse_btn);
        ImageButton b2 = (ImageButton)findViewById(R.id.advance_btn);
        ImageButton fBtn = (ImageButton)findViewById(R.id.formulaBtn);
        drawingSurface = (MyView)findViewById(R.id.drawing_view);
        RadioButton linearRadBtn = (RadioButton)findViewById(R.id.linearFitRadioBtn);
        RadioButton quadRadBtn = (RadioButton)findViewById(R.id.quadraticFitRadioBtn);
        RadioButton expRadBtn = (RadioButton)findViewById(R.id.exponentialFitRadioBtn);
        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());

        linearRadBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                curve = CurveFit.LINEAR;
            }
        });
        quadRadBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                curve = CurveFit.QUAD;
            }
        });
        expRadBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                curve = CurveFit.EXP;
            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView info = (TextView)findViewById(R.id.infoTextView);

                drawingSurface.clearFocus();
                xPoints = xP2;
                yPoints = yP2;
                data = new DataSeries(xPoints, yPoints, 9);
                drawingSurface.setCurveType(curve);
                drawingSurface.setData(data);
                CurveFit fit = drawingSurface.getFit();
                String p = "";

                if (fit != null) {
                    double[] param = fit.getParameters();
                    if (param != null) {

                        for (int i = 0; i < param.length; i++) {
                            Log.i("fitparam", "" + param[i]);

                            p = p + param[i] + "    ";
                            info.setText(p);

                        }
                    }
                }
                drawingSurface.invalidate();
                info.setText(p);
                Toast.makeText(MainActivity.this, "Next button pressed", Toast.LENGTH_SHORT).show();
            }

        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView info = (TextView)findViewById(R.id.infoTextView);

                xPoints = xP1;
                yPoints = yP1;
                data = new DataSeries(xPoints, yPoints, 9);
                drawingSurface.setCurveType(curve);
                drawingSurface.setData(data);
                CurveFit fit = drawingSurface.getFit();
                String p = "";
                if (fit != null) {
                    double[] param = fit.getParameters();
                    if (param != null) {
                        for (int i = 0; i < param.length; i++) {
                            Log.i("fitparam", "" + param[i]);
                            p = p + param[i] + "    ";
                            info.setText(p);
                        }
                    }
                }
                drawingSurface.invalidate();
                info.setText(p);

                Toast.makeText(MainActivity.this, "Back button pressed", Toast.LENGTH_SHORT)
                        .show();
            }
        });

        fBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BasicAlertDialogFragment alertDialog = new BasicAlertDialogFragment();
                alertDialog.show(getFragmentManager(), "alert");
            }
        });

//        final DataSeries data = new DataSeries(xPoints, yPoints, 9);
        /*drawingSurface.setCurveType(curve);
        drawingSurface.setData(data);
        drawingSurface.invalidate();
        CurveFit fit = drawingSurface.getFit();
        if(fit!= null){
            double[] param = fit.getParameters();
            if(param != null){
                for(int i =0; i< param.length; i++){
                    Log.i("fitparam", "" + param[i]);
                }
            }
        }*/

    }




    /*public boolean onTouchEvent(MotionEvent ev) {
        scaleGestureDetector.onTouchEvent(ev);
        return true;
    }*/

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float scaleFactor = detector.getScaleFactor();
            scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 5.0f));

            drawingSurface.invalidate();
            return true;
        }
    }

}
