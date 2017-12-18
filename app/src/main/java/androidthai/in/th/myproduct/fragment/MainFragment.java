package androidthai.in.th.myproduct.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import androidthai.in.th.myproduct.R;
import androidthai.in.th.myproduct.utility.GetAllData;
import androidthai.in.th.myproduct.utility.MyAlertDialog;

/**
 * Created by masterung on 16/12/2017 AD.
 */

public class MainFragment extends Fragment{

    private String userString, passwordString;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Register Controller
        registerController();

//        Login Controller
        loginController();

    }   // Main Method

    private void loginController() {
        Button button = getView().findViewById(R.id.btnLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Initial View
                EditText userEditText = getView().findViewById(R.id.edtUser);
                EditText passwordEditText = getView().findViewById(R.id.edtPassword);

//                Get Value From EditText
                userString = userEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();

//                Check Space
                if (userString.isEmpty() || passwordString.isEmpty()) {
//                    Have Space
                    MyAlertDialog myAlertDialog = new MyAlertDialog(getActivity());
                    myAlertDialog.normalDialog("Have Space",
                            "Please Fill All Blank");
                } else {
//                    No Space
                    checkUserAndPass();


                }


            }
        });
    }

    private void checkUserAndPass() {

        try {

            boolean status = true;  // true --> user False
            String truePasswordString = null, nameString = null;

            GetAllData getAllData = new GetAllData(getActivity());
            getAllData.execute("http://androidthai.in.th/sky/getAllDataMasterUNG.php");
            String jsonString = getAllData.get();

            Log.d("masterUNG", "JSON ==> " + jsonString);

            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i=0; i<jsonArray.length(); i+=1) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (userString.equals(jsonObject.getString("User"))) {
                    status = false;
                    truePasswordString = jsonObject.getString("Password");
                    nameString = jsonObject.getString("Name");
                }
            }

            if (status) {
                MyAlertDialog myAlertDialog = new MyAlertDialog(getActivity());
                myAlertDialog.normalDialog("User False",
                        "No This User in my Database");
            } else if (passwordString.equals(truePasswordString)) {
                Toast.makeText(getActivity(), "Welcome " + nameString, Toast.LENGTH_SHORT).show();

//                Replace Fragment With ServiceFragment
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentMainFragment, new ServiceFragment())
                        .commit();

            } else {
                MyAlertDialog myAlertDialog = new MyAlertDialog(getActivity());
                myAlertDialog.normalDialog("Password False",
                        "Please Try Again Password False");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void registerController() {
        TextView textView = getView().findViewById(R.id.txtNewRegister);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Replace Fragment
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentMainFragment, new RegisterFragment())
                        .addToBackStack(null)
                        .commit();

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }
}
