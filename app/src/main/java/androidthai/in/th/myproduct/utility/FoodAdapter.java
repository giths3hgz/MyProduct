package androidthai.in.th.myproduct.utility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import androidthai.in.th.myproduct.R;

/**
 * Created by masterung on 18/12/2017 AD.
 */

public class FoodAdapter extends BaseAdapter{

    private Context context;
    private String[] iconStrings, nameStrings, priceStrings;

    public FoodAdapter(Context context,
                       String[] iconStrings,
                       String[] nameStrings,
                       String[] priceStrings) {
        this.context = context;
        this.iconStrings = iconStrings;
        this.nameStrings = nameStrings;
        this.priceStrings = priceStrings;
    }

    @Override
    public int getCount() {
        return iconStrings.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.listview_food, parent, false);

//        Initial View
        ImageView imageView = view.findViewById(R.id.imvIcon);
        TextView nameTextView = view.findViewById(R.id.txtName);
        TextView priceTextView = view.findViewById(R.id.txtPrice);

//        Setup Icon
        Picasso.with(context).load(iconStrings[position]).into(imageView);

//        Setup Text
        nameTextView.setText(nameStrings[position]);
        priceTextView.setText("Price = " + priceStrings[position] + " THB.");

        return view;
    }
}
