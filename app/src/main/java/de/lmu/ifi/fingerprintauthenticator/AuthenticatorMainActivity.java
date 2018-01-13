package de.lmu.ifi.fingerprintauthenticator;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class AuthenticatorMainActivity extends Activity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticator_main);
        listView = (findViewById(R.id.main_listview));
        String[] services = {"Service 1", "Service 2"};
        listView.setAdapter(new ListAdapter(this,services));
    }
}
