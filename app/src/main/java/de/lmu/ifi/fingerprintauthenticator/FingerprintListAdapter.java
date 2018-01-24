package de.lmu.ifi.fingerprintauthenticator;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andyg on 13.01.2018.
 */

public class FingerprintListAdapter extends ArrayAdapter<Service> {
    private final Context context;
    private final List<Service> values = new ArrayList<>();
    AuthenticatorMainActivity mainActivity;

    public FingerprintListAdapter(Context context, String deviceID, AuthenticatorMainActivity mainActivity) {
        super(context, -1);
        this.context = context;
        this.mainActivity = mainActivity;
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference deviceRef = database.getReference("devices");
        deviceRef.child(deviceID).child("services").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Service service = dataSnapshot.getValue(Service.class);
                values.add(service);
                Log.i("LIST ADAPTER", "child added!!!");
                FingerprintListAdapter.this.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Service service = dataSnapshot.getValue(Service.class);
                boolean changed = false;
                Log.i("LIST ADAPTER", "child changed!!!");
                for(Service ser:values){
                    if (ser.getName() == service.name){
                        ser.setStatus(service.getStatus());
                        changed = true;
                    }
                }
                if(changed)
                    FingerprintListAdapter.this.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Service service = dataSnapshot.getValue(Service.class);
                boolean changed = false;
                for(Service ser:values){
                    if (ser.getName() == service.name){
                        values.remove(ser);
                        changed = true;
                    }
                }
                if(changed)
                    FingerprintListAdapter.this.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.authenticator_list_row, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.rowText);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.rowIcon);

        final String text = values.get(position).name;
        boolean actionNeeded;
        switch(values.get(position).status){
            case APPROVED:
                actionNeeded = false;
                break;
            case SIGN_IN_APPROVAL_NEEDED:
                actionNeeded = true;
                break;
            case REGISTRATION_APPROVAL_NEEDED:
                actionNeeded = true;
                break;
            default:
                actionNeeded = false;
                break;
        }


        textView.setText(text);

        Log.i("LIST ADAPTER", "get view "+text+  " action needed? "+actionNeeded);



        if(actionNeeded){
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mainActivity.showFingerprintDialog(text);
                }
            });
            imageView.setVisibility(View.VISIBLE);
        }
        else {
            imageView.setVisibility(View.INVISIBLE);
        }

        return rowView;
    }



}
