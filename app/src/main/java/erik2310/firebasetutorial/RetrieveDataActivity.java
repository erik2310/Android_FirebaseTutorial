package erik2310.firebasetutorial;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RetrieveDataActivity extends AppCompatActivity {

    // Deklarer en DatabaseReference variabel
    private DatabaseReference mDatabase;

    // Deklarer en TextView variabel
    private TextView mName_View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_data);

        // Henter Firebase databasens root og initialiserer den til mDatabase
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Name");

        // Sætter field med id name_view til mName_View
        mName_View = (TextView) findViewById(R.id.name_view);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String name = dataSnapshot.getValue().toString();

                mName_View.setText("Name : " + name);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
