package com.example.minecraftwayz;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class MainActivity extends Activity {

	Button Getbutton; 
	double RAD2DEG = 180/3.141;
	
	public double stringToDouble(String s) {
		if (s.matches("")) {
			return 0.0;
		} else {
			return Double.parseDouble(s);
		}
	}

	
	public double editTextToDouble(EditText et) {
		String txt = et.getText().toString();
		return stringToDouble(txt);
	}
	
	
	public double distance2d(double p1x, double p1y, double p2x, double p2y) {
		return (double) Math.sqrt(Math.pow((p1x - p2x), 2) + Math.pow((p2y - p2y), 2));
	}
	
	
	public double distance3d(double p1x, double p1y, double p2x, double p2y, double p1z, double p2z) {
		return (double) Math.sqrt(Math.pow((p2x - p1x), 2) + Math.pow((p2y - p1y), 2) + Math.pow((p2z - p1z), 2));
	}
	
	
	public double altitude(double ydiff, double distance) {
		if (distance > 1){
			return -Math.asin(ydiff / distance) * RAD2DEG;
		}
		else {
			return 0.0;
		}
	}
	
	
	public double azimuth(double xdiff, double zdiff) {
		double d = Math.sqrt(Math.pow((xdiff), 2) + Math.pow((zdiff), 2));
		if (d < 1) {
			Log.i("azimuth", "xz distance is < 1");
			return 0.0;
		}
		double angleFromZ = Math.asin(-xdiff / d) * RAD2DEG;
		if (zdiff >= 0){
			return angleFromZ;
		} else{
			if (xdiff > 0) {
				return -180 - angleFromZ;
			} else {
				return 180 - angleFromZ;
			}
		}
		
		
	}

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Getbutton = (Button) findViewById(R.id.Getbutton);
		final TextView alt1 = (TextView) findViewById(R.id.tv_el_calc);
		final TextView azm1 = (TextView) findViewById(R.id.tv_az_calc);
		
		Getbutton.setOnClickListener(new View.OnClickListener() {
			
		
			public void onClick(View V){
				Log.i("button", "button was pressed");
				
				double x0 = editTextToDouble((EditText) findViewById(R.id.et_start_x));
				double y0 = editTextToDouble((EditText) findViewById(R.id.et_start_y));
				double z0 = editTextToDouble((EditText) findViewById(R.id.et_start_z));
				double x1 = editTextToDouble((EditText) findViewById(R.id.et_end_x));
				double y1 = editTextToDouble((EditText) findViewById(R.id.et_end_y));
				double z1 = editTextToDouble((EditText) findViewById(R.id.et_end_z));
				double xdiff = x1 - x0;
				double ydiff = y1 - y0;
				double zdiff = z1 - z0;
				
				double alt = altitude(ydiff, distance3d(x0, y0, x1, y1, z0, z1));
				double azm = azimuth(xdiff, zdiff);
				
				alt1.setText(String.valueOf(alt));
				azm1.setText(String.valueOf(azm));
			}
		});
	}
	
	
}
