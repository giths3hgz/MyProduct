package androidthai.in.th.myproduct.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidthai.in.th.myproduct.MainActivity;
import androidthai.in.th.myproduct.R;
import androidthai.in.th.myproduct.utility.MyAlertDialog;
import androidthai.in.th.myproduct.utility.PostUserToServer;

/**
 * Created by masterung on 16/12/2017 AD.
 */

public class RegisterFragment extends Fragment{

//    Explicit
    private String nameString, userString, passwordString;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Toolbar Controller
        toolbarController();

//        Register Controller
        registerController();

    }   // Main Method

    private void registerController() {

        Button button = getView().findViewById(R.id.btnRegister);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Initial EditView
                EditText nameEditText = getView().findViewById(R.id.edtName);
                EditText userEditText = getView().findViewById(R.id.edtUser);
                EditText passwordEditText = getView().findViewById(R.id.edtPassword);

//                Get Value From EditText
                nameString = nameEditText.getText().toString().trim();
                userString = userEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();

//                Check Space
                if (nameString.isEmpty() || userString.isEmpty() || passwordString.isEmpty()) {
//                    Have Space
                    MyAlertDialog myAlertDialog = new MyAlertDialog(getActivity());
                    myAlertDialog.normalDialog(getString(R.string.title_have_space),
                            getString(R.string.message_have_space));

                } else {
//                    No Space
                    try {

                        PostUserToServer postUserToServer = new PostUserToServer(getActivity());
                        postUserToServer.execute(
                                nameString,
                                userString,
                                passwordString,
                                "http://androidthai.in.th/sky/addDataMasterUNG.php");

                        if (Boolean.parseBoolean(postUserToServer.get())) {
                            Toast.makeText(getActivity(), "Upload New User Success",
                                    Toast.LENGTH_SHORT).show();
                            getActivity().getSupportFragmentManager().popBackStack();

                        } else {
                            MyAlertDialog myAlertDialog = new MyAlertDialog(getActivity());
                            myAlertDialog.normalDialog("Cannot Upload New User",
                                    "Please Try Again");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }




            }
        });


    }

    private void toolbarController() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarRegister);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar()
                .setTitle("Please Registed");

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        return view;
    }
}
