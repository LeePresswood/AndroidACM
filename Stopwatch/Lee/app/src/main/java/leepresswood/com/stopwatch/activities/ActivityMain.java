package leepresswood.com.stopwatch.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import leepresswood.com.stopwatch.R;

public class ActivityMain extends Activity
{
	private TextView text_time;
	private Button button_start, button_stop, button_reset;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		text_time = (TextView) findViewById(R.id.text_time);

		button_start = (Button) findViewById(R.id.button_start);
		button_stop = (Button) findViewById(R.id.button_stop);
		button_reset = (Button) findViewById(R.id.button_reset);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void start(View view)
	{//Start counter.
		//Only want to do the functions if the button is enabled.
		if(button_start.isEnabled())
		{
			button_start.setEnabled(false);
			button_stop.setEnabled(true);
			button_reset.setEnabled(false);
		}
	}

	public void stop(View view)
	{//Stop counter if it has started already.
		//Only want to do the functions if the button is enabled.
		if(button_stop.isEnabled())
		{
			button_start.setEnabled(true);
			button_stop.setEnabled(false);
			button_reset.setEnabled(true);
		}
	}

	public void reset(View view)
	{//Reset timer to 0.
		//Only want to do the functions if the button is enabled.
		if(button_reset.isEnabled())
		{
			button_start.setEnabled(false);
			button_stop.setEnabled(true);
			button_reset.setEnabled(false);
		}
	}
}
