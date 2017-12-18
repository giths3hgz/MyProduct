package androidthai.in.th.myproduct.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import androidthai.in.th.myproduct.MainActivity;
import androidthai.in.th.myproduct.R;

/**
 * Created by masterung on 19/12/2017 AD.
 */

public class DetailFragment extends Fragment {

    private String nameString, priceString, categoryString, imagePathString, detailString;

    public static DetailFragment detailInstance(String nameString,
                                                String priceString,
                                                String categoryString,
                                                String imagePathString,
                                                String detailString) {

        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("Name", nameString);
        bundle.putString("Price", priceString);
        bundle.putString("Category", categoryString);
        bundle.putString("ImagePath", imagePathString);
        bundle.putString("Detail", detailString);
        detailFragment.setArguments(bundle);
        return detailFragment;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Get Value From Argument
        getValueFromAgument();

//        Create Toolbar
        createToolbar();

//        Show View
        showView();


    }   // Main Method

    private void showView() {
        //        Category
        TextView catTextView = getView().findViewById(R.id.txtCategory);
        catTextView.setText(categoryString);

//        Image
        ImageView imageView = getView().findViewById(R.id.imvImage);
        Picasso.with(getActivity()).load(imagePathString).into(imageView);

//        Detail
        TextView detailTextView = getView().findViewById(R.id.txtDetail);
        detailTextView.setText(detailString);
    }

    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarDetail);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(nameString);
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle("Price = " + priceString + " THB");

        ((MainActivity) getActivity()).getSupportActionBar()
                .setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar()
                .setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    private void getValueFromAgument() {
        nameString = getArguments().getString("Name");
        priceString = getArguments().getString("Price");
        categoryString = getArguments().getString("Category");
        imagePathString = getArguments().getString("ImagePath");
        detailString = getArguments().getString("Detail");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        return view;
    }
}
