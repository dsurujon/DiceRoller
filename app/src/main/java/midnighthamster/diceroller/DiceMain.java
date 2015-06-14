package midnighthamster.diceroller;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Arrays;
import java.util.Random;


public class DiceMain extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_main);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        NumberPicker np=(NumberPicker) findViewById(R.id.numberPicker);
        np.setMaxValue(10);
        np.setMinValue(1);
        TextView resultText = (TextView) findViewById(R.id.textView2);
        resultText.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dice_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //get the number of dice selected in numberPicker
    public void getNoDice(View view){
        int numberDice;
        LinearLayout gLayout = (LinearLayout) findViewById(R.id.gridLayout1);
        int rowCount = gLayout.getChildCount();
        gLayout.removeViews(5,rowCount-5);

        NumberPicker np=(NumberPicker) findViewById(R.id.numberPicker);
        numberDice = np.getValue();
        TextView resultText = (TextView) findViewById(R.id.textView2);
        int[] rolls = new int[numberDice];
        for (int i=0; i<numberDice; i++){
            Random rand = new Random();
            rolls[i] = rand.nextInt(5)+1;
            ImageView thisDie = new ImageView(DiceMain.this);
            String diceName = "dice"+rolls[i];
            int resID = getResources().getIdentifier(diceName , "drawable", getPackageName());
            thisDie.setImageResource(resID);
            gLayout.addView(thisDie);
        }
        //display the resulting roll as an array
        resultText.setText(Arrays.toString(rolls));
        /*
        //try displaying dice
        ImageView firstDie = (ImageView) findViewById(R.id.imageView);
        String diceName = "dice"+rolls[0];
        int resID = getResources().getIdentifier(diceName , "drawable", getPackageName());
        firstDie.setImageResource(resID);
        */
    }
}
