package erik2310.firebasetutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    // Deklarer en Button variabel
    private Button mFirebase_button;

    // Deklarer en DatabaseReference variabel
    private DatabaseReference mDatabase;

    // Deklarer en EditText variabel
    private EditText mName_field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sætter knappen med id firebase_button til mFirebase_button variabel
        mFirebase_button = (Button)findViewById(R.id.firebase_button);

        // Sætter field med id name_field til mName_field
        mName_field = (EditText)findViewById(R.id.name_field);

        // Henter Firebase databasens root objekt og initialiserer den til mDatabase
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Laver en OnClick event listener til mFirebase_button
        mFirebase_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = mName_field.getText().toString().trim();

                mDatabase.child("Name").setValue(name);

            }
        });
    }
}
