package leepresswood.com.stopwatch.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import leepresswood.com.stopwatch.R;

public class ActivityMain extends Activity
{
	private TextView text_time;
	private Button button_start, button_stop, button_reset;
	private Integer hours, minutes, seconds, decimal;

	private Timer timer;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		text_time = (TextView) findViewById(R.id.text_time);

		button_start = (Button) findViewById(R.id.button_start);
		button_stop = (Button) findViewById(R.id.button_stop);
		button_reset = (Button) findViewById(R.id.button_reset);

		resetTime();
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
			setButtonsEnabled(false, true, false);
			timer = new Timer();
			timer.scheduleAtFixedRate(incrementTime(), 0, 100);
		}
	}

	public void stop(View view)
	{//Stop counter if it has started already.
		//Only want to do the functions if the button is enabled.
		if(button_stop.isEnabled())
		{
			setButtonsEnabled(true, false, true);
			timer.cancel();
		}
	}

	public void reset(View view)
	{//Reset timer to 0.
		//Only want to do the functions if the button is enabled.
		if(button_reset.isEnabled())
		{
			resetTime();
		}
	}

	private void setButtonsEnabled(boolean start, boolean stop, boolean reset)
	{
		button_start.setEnabled(start);
		button_stop.setEnabled(stop);
		button_reset.setEnabled(reset);
	}

	private void resetTime()
	{
		hours = 0;
		minutes = 0;
		seconds = 0;
		decimal = 0;

		text_time.setText(R.string.timer_default);
	}

	private TimerTask incrementTime()
	{//Increment decimal. Change the rest as needed.
		return new TimerTask()
		{
			@Override
			public void run()
			{
				//Run the increment logic
				decimal++;
				if(decimal == 10)
				{
					decimal = 0;
					seconds++;

					if(seconds == 60)
					{
						seconds = 0;
						minutes++;

						if(minutes == 60)
						{
							minutes = 0;
							hours++;

							if(hours == 100)
							{
								hours = 99;
							}
						}
					}
				}

				runOnUiThread(new Runnable()
				{
					@Override
					public void run()
					{
						//Change the text. Should always be double-digit in every field except decimal.
						String hour_text = hours >= 10 ? hours.toString() : "0" + hours.toString();
						String minute_text = minutes >= 10 ? minutes.toString() : "0" + minutes.toString();
						String second_text = seconds >= 10 ? seconds.toString() : "0" + seconds.toString();

						text_time.setText(hour_text + ":" + minute_text + ":" + second_text + "." + decimal.toString());
					}
				});
			}
		};
	}
}
