package com.example.task13ya;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Shelly
 * @version 1.1
 * @since 18/01/2026
 *
 * This activity displays developer information and application credits.
 */
class Credits extends AppCompatActivity {

    /**
     * Initializes the credits screen and sets the layout view.
     *
     * @param savedInstanceState Bundle containing the activity's previously frozen state, if any.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enables edge-to-edge display for a modern UI look
        EdgeToEdge.enable(this);

        // Setting the content view to the credits layout
        setContentView(R.layout.credits_xml);
    }
}