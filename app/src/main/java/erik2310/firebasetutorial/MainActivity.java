package erik2310.firebasetutorial;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    // Deklarer en Button variabel
    private Button mFirebase_button;
    private Button mOpen_Retrieve_Data_button;

    // Deklarer en DatabaseReference variabel
    private DatabaseReference mDatabase;

    // Deklarer en EditText variabel
    private EditText mUsername_field;
    private EditText mPassword_field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sætter knappen med id firebase_button til mFirebase_button variabel
        mFirebase_button = (Button)findViewById(R.id.firebase_button);

        // Sætter knappen med id open_retrieve_data_button til mOpen_Retrieve_Data_button variabel
        mOpen_Retrieve_Data_button = (Button) findViewById(R.id.open_retrieve_data_button);

        // Sætter field med id username_field til mUsername_field
        mUsername_field = (EditText) findViewById(R.id.username_field);

        // Sætter field med id password_field til mPassword_field
        mPassword_field = (EditText) findViewById(R.id.password_field);

        // Henter Firebase databasens root/Users og initialiserer den til mDatabase
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

        // Laver en OnClick event listener til mFirebase_button
        mFirebase_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Henter username og password, gemmer dem i String datatyperne
                String username = mUsername_field.getText().toString().trim();
                String password = mPassword_field.getText().toString().trim();

                // Laver en instans af HashMap som gemmer en String, String værdi
                HashMap<String, String> dataMap = new HashMap<>();

                // Lægger en værdi ind med et navn
                dataMap.put("Username", username);
                dataMap.put("Password", password);

                // Sender det til databasen som et objekt med et tilfældigt navn, men tjekker om den er sendt til databasen
                mDatabase.push().setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "User created successfully!", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MainActivity.this, "User creation failed!", Toast.LENGTH_LONG).show();
                        }

                    }
                });

            }
        });

        // Laver en OnClick event listener til mOpen_Retrieve_Data_button
        mOpen_Retrieve_Data_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openRetrieveData = new Intent(MainActivity.this, RetrieveDataActivity.class);
                startActivity(openRetrieveData);
            }
        });
    }
}
