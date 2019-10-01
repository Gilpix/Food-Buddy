package home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kulartist.foodbuddy.R;

public class RegulatoryCustomAdapter extends BaseAdapter {
    Context context;
    String signsDescription[];
    String rTiming[];
    int signsImage[];
    LayoutInflater inflter;

    public RegulatoryCustomAdapter(Context applicationContext, String[] countryList,String[] restTiming, int[] flags) {
        this.context = context;
        this.signsDescription = countryList;
        this.rTiming = restTiming;
        this.signsImage = flags;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return signsDescription.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.reugulatory_list_view, null);
        TextView country = (TextView) view.findViewById(R.id.title);
        TextView time = (TextView) view.findViewById(R.id.content);
        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        time.setText(rTiming[i]);
        country.setText(signsDescription[i]);
        icon.setImageResource(signsImage[i]);
        return view;
    }
}