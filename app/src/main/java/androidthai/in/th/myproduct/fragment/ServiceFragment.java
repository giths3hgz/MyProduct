package androidthai.in.th.myproduct.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import androidthai.in.th.myproduct.R;
import androidthai.in.th.myproduct.utility.FoodAdapter;
import androidthai.in.th.myproduct.utility.GetAllData;

/**
 * Created by masterung on 18/12/2017 AD.
 */

public class ServiceFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Create ListView
        createListView();

    }

    private void createListView() {

        ListView listView = getView().findViewById(R.id.listviewFood);
        String tag = "19DecV1";
        String[] columnStrings = new String[]{"id", "Category", "NameFood", "Price", "Detail", "ImagePath"};

        try {

            GetAllData getAllData = new GetAllData(getActivity());
            getAllData.execute("http://androidthai.in.th/sky/getAllFoodMasterUNG.php");
            String jsonString = getAllData.get();

            Log.d(tag, "JSON ==> " + jsonString);

            JSONArray jsonArray = new JSONArray(jsonString);

            final String[] catStrings = new String[jsonArray.length()];
            final String[] nameStrings = new String[jsonArray.length()];
            final String[] priceStrings = new String[jsonArray.length()];
            final String[] detailStrings = new String[jsonArray.length()];
            final String[] imagePathStrings = new String[jsonArray.length()];

            for (int i = 0; i < jsonArray.length(); i += 1) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                catStrings[i] = jsonObject.getString(columnStrings[1]);
                nameStrings[i] = jsonObject.getString(columnStrings[2]);
                priceStrings[i] = jsonObject.getString(columnStrings[3]);
                detailStrings[i] = jsonObject.getString(columnStrings[4]);
                imagePathStrings[i] = jsonObject.getString(columnStrings[5]);

            } //for

            FoodAdapter foodAdapter = new FoodAdapter(getActivity(), imagePathStrings,
                    nameStrings, priceStrings);
            listView.setAdapter(foodAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.contentMainFragment, DetailFragment.detailInstance(
                                    nameStrings[position],
                                    priceStrings[position],
                                    catStrings[position],
                                    imagePathStrings[position],
                                    detailStrings[position]))
                            .addToBackStack(null)
                            .commit();

                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_service, container, false);
        return view;
    }
}
