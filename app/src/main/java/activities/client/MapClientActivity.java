package activities.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.danielse.uberfem.R;
import com.danielse.uberfem.providers.AuthProvider;

import activities.MainActivity;

public class MapClientActivity extends AppCompatActivity {

    Button buttonLogout;
    AuthProvider authProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_client);

        buttonLogout = findViewById(R.id.buttonLogout);
        authProvider = new AuthProvider();

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Obviamente el trasfondo de esto, por lógica de autenticación, se encuentra en AuthProvider
                authProvider.logout();
                //Por consiguiente, que nos devuelva a MainActivity para reiniciar el proceso de ingreso/registro
                Intent intent = new Intent(MapClientActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}