package com.example.ex1101_shared_preferences;

/**
 * @author	Maya Leibovich mayaLeibovich3@gmail.com
 * @version	 1.1
 * @since	24/2/2025
 * Shared preferences file practice
 */

import static java.lang.System.exit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity{

    EditText eTName;
    TextView tVCount;
    Button btnCount, btnRest, btnExit;

    SharedPreferences settings;
    SharedPreferences.Editor editor;

    int count = 0;
    String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eTName = findViewById(R.id.eTName);
        tVCount = findViewById(R.id.tVCount);
        btnCount = findViewById(R.id.btnCount);
        btnRest = findViewById(R.id.btnRest);
        btnExit = findViewById(R.id.btnExit);

        settings = getSharedPreferences ("PREFS_NAME_COUNT", MODE_PRIVATE);

        name = settings.getString("name", "------");
        eTName.setText(name);
        count = settings.getInt("count", -1);
        tVCount.setText(count + "");
    }

    /**
     * count onclick - every click adds ont to count
     * @param	view view
     * @return	none
     */
    public void clickCount(View view) {
        count++;
        tVCount.setText(count + "");
    }

    /**
     * rest onclick - rest count
     * @param	view view
     * @return	none
     */
    public void clickRest(View view) {
        count = 0;
        tVCount.setText("0");
    }

    /**
     * count onclick - writes to file
     * @param	view view
     * @return	none
     */
    public void clickExit(View view) {
         // activate the editor

        name = eTName.getText().toString();
        editor = settings.edit();
        editor.putString("name", name);
        editor.putInt("count", count);

        editor.commit(); // close file
        finish(); // end program
    }


    /**
     * creats menu
     * @param	menu Menu
     * @return	none
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * handles menu item selection
     * @param	item MenuItem
     * @return	none
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        String st = item.getTitle().toString();
        if(st.equals("Credits"))
        {
            Intent creditesIntent = new Intent(this, Credits.class);
            startActivity(creditesIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}