package activities.driver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import com.danielse.uberfem.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class MapDriverActivity extends AppCompatActivity implements OnMapReadyCallback {

    //Para LOGOUT-----------------------------------------------------------------
    /*
    Button buttonLogout;
    AuthProvider authProvider;
     */
    //Para LOGOUT-----------------------------------------------------------------

    private GoogleMap googleMap;
    private SupportMapFragment supportMapFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_driver);

        //Diferencia entre SupportMapFragment y Fragment??
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);


        //Para LOGOUT-----------------------------------------------------------------
        /*
        buttonLogout = findViewById(R.id.buttonLogout);
        authProvider = new AuthProvider();

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Obviamente el trasfondo de esto, por lógica de autenticación, se encuentra en AuthProvider
                authProvider.logout();
                //Por consiguiente, que nos devuelva a MainActivity para reiniciar el proceso de ingreso/registro
                Intent intent = new Intent(MapDriverActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
         */
        //Para LOGOUT-----------------------------------------------------------------
    }

    @Override
    public void onMapReady(GoogleMap mMap) {
        googleMap = mMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }
}