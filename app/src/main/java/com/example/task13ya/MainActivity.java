package com.example.task13_ya;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.task13ya.R;

/**
 * @author Darya
 * @version 1.1
 * @since 18/01/2026
 *
 * This class handles the municipal event planning logic using custom alert dialogs.
 */
public class MainActivity extends AppCompatActivity
{
    private TextView textEventSummary;
    private ConstraintLayout parentLayout;
    private Button btnSelectType, btnSelectEquip, btnRateEvent, btnResetAll;

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == R.id.action_credits)
        {
            Intent intent = new Intent(this, Credits.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        btnSelectType = findViewById(R.id.btn1);
        btnSelectEquip = findViewById(R.id.btn2);
        btnRateEvent = findViewById(R.id.btn3);
        btnResetAll = findViewById(R.id.btn4);

        parentLayout = findViewById(R.id.main);
        textEventSummary = findViewById(R.id.tvSummary);

        // Set Click Listeners
        btnSelectType.setOnClickListener(v -> openTypeSelectionDialog());
        btnSelectEquip.setOnClickListener(v -> openEquipmentSelectionDialog());
        btnRateEvent.setOnClickListener(v -> openRatingAndFeedbackDialog());
        btnResetAll.setOnClickListener(v -> openResetConfirmationDialog());
    }

    /**
     * Opens a custom dialog to confirm resetting the event plan.
     */
    private void openResetConfirmationDialog()
    {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_btn4, null);
        Button confirmAction = dialogView.findViewById(R.id.btnYesReset);
        Button cancelAction = dialogView.findViewById(R.id.btnNoReset);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        builder.setCancelable(false);

        final AlertDialog alertDialog = builder.create();

        confirmAction.setOnClickListener(v -> {
            parentLayout.setBackgroundColor(Color.WHITE);
            textEventSummary.setText("Plan has been reset.");
            alertDialog.dismiss();
        });

        cancelAction.setOnClickListener(v -> alertDialog.dismiss());
        alertDialog.show();
    }

    /**
     * Opens a custom dialog for user rating and organizer name input.
     */
    private void openRatingAndFeedbackDialog()
    {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_btn3, null);
        EditText inputOrganizer = dialogView.findViewById(R.id.etOrganizer);
        RatingBar ratingBar = dialogView.findViewById(R.id.rbStars);
        Button submitData = dialogView.findViewById(R.id.btnSubmitRating);
        Button closeDialog = dialogView.findViewById(R.id.btnCancelRating);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        builder.setCancelable(false);

        final AlertDialog alertDialog = builder.create();

        submitData.setOnClickListener(v -> {
            String organizerName = inputOrganizer.getText().toString();
            float ratingValue = ratingBar.getRating();

            Toast.makeText(this, "Organizer: " + organizerName + " | Rating: " + ratingValue, Toast.LENGTH_SHORT).show();
            textEventSummary.setText("Organizer: " + organizerName + " (Rating: " + ratingValue + ")");
            alertDialog.dismiss();
        });

        closeDialog.setOnClickListener(v -> alertDialog.dismiss());
        alertDialog.show();
    }

    /**
     * Opens a custom dialog with checkboxes to select event equipment.
     */
    private void openEquipmentSelectionDialog()
    {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_btn2, null);
        CheckBox chkChairs = dialogView.findViewById(R.id.cbChairs);
        CheckBox chkGenerator = dialogView.findViewById(R.id.cbGenerator);
        CheckBox chkLighting = dialogView.findViewById(R.id.cbLighting);
        CheckBox chkDrinks = dialogView.findViewById(R.id.cbDrinks);
        CheckBox chkMusic = dialogView.findViewById(R.id.cbMusic);

        Button btnConfirm = dialogView.findViewById(R.id.btnConfirmEquip);
        Button btnCancel = dialogView.findViewById(R.id.btnCancelEquip);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        builder.setCancelable(false);

        final AlertDialog alertDialog = builder.create();

        btnConfirm.setOnClickListener(v -> {
            StringBuilder items = new StringBuilder("Equipment: ");
            if (chkChairs.isChecked()) items.append("Chairs, ");
            if (chkGenerator.isChecked()) items.append("Generator, ");
            if (chkLighting.isChecked()) items.append("Lighting, ");
            if (chkDrinks.isChecked()) items.append("Drinks, ");
            if (chkMusic.isChecked()) items.append("Music, ");

            String result = items.toString();
            if (result.endsWith(", ")) {
                result = result.substring(0, result.length() - 2);
            }

            textEventSummary.setText(result);
            alertDialog.dismiss();
        });

        btnCancel.setOnClickListener(v -> alertDialog.dismiss());
        alertDialog.show();
    }

    /**
     * Opens a custom dialog with image buttons to select the type of event.
     */
    private void openTypeSelectionDialog()
    {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_btn1, null);

        ImageButton imgPool = dialogView.findViewById(R.id.btn1Alart1);
        ImageButton imgShow = dialogView.findViewById(R.id.btn2Alart1);
        ImageButton imgParty = dialogView.findViewById(R.id.btn3Alart1);
        ImageButton imgFestival = dialogView.findViewById(R.id.btn4Alart1);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        builder.setCancelable(false);

        final AlertDialog alertDialog = builder.create();

        imgPool.setOnClickListener(v -> {
            parentLayout.setBackgroundColor(Color.CYAN);
            textEventSummary.setText("Event: Pool Party");
            alertDialog.dismiss();
        });

        imgShow.setOnClickListener(v -> {
            parentLayout.setBackgroundColor(Color.MAGENTA);
            textEventSummary.setText("Event: Live Music Show");
            alertDialog.dismiss();
        });

        imgParty.setOnClickListener(v -> {
            parentLayout.setBackgroundColor(Color.YELLOW);
            textEventSummary.setText("Event: Street Party");
            alertDialog.dismiss();
        });

        imgFestival.setOnClickListener(v -> {
            parentLayout.setBackgroundColor(Color.GREEN);
            textEventSummary.setText("Event: City Festival");
            alertDialog.dismiss();
        });

        alertDialog.show();
    }
}