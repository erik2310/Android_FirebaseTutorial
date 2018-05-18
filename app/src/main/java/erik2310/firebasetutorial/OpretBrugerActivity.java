package erik2310.firebasetutorial;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class OpretBrugerActivity extends AppCompatActivity {

    // Deklarer en EditText variabel
    private EditText mEmail_field;
    private EditText mPassword_field;

    // Deklarer en Button variabel
    private Button mCreate_User_button;

    // Deklarer en FirebaseAuth variabel
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opret_bruger);

        // Sætter field med id email_field til mEmail_field variabel
        mEmail_field = (EditText) findViewById(R.id.email_field);

        // Sætter field med id password_field til mPassword_field variabel
        mPassword_field = (EditText) findViewById(R.id.password_field);

        // Sætter knappen med id create_user_button til mCreate_User_button variabel
        mCreate_User_button = (Button) findViewById(R.id.create_user_button);

        // Henter en instance af Firebase Authentication
        mAuth = FirebaseAuth.getInstance();

        mCreate_User_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gemmer email og password i en String datatype
                String email = mEmail_field.getText().toString();
                String password = mPassword_field.getText().toString();

                // Opretter en bruger med createAccount metoden
                createAccount(email, password);
            }
        });

    }

    private void createAccount(String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(OpretBrugerActivity.this, "Created user successfully!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(OpretBrugerActivity.this, "Create user failed!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
