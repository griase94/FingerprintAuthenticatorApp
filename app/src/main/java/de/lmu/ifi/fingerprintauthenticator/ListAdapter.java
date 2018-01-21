package de.lmu.ifi.fingerprintauthenticator;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by andyg on 13.01.2018.
 */

public class ListAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    AuthenticatorMainActivity.ListClickListener mListClickListener;

    public ListAdapter(Context context, String[] values, AuthenticatorMainActivity.ListClickListener listClickListener) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
        this.mListClickListener = listClickListener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.authenticator_list_row, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.rowText);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.rowIcon);
        textView.setText(values[position]);

        imageView.setOnClickListener(mListClickListener);

        return rowView;
    }


}
