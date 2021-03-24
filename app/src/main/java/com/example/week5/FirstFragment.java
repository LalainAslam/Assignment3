package com.example.week5;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.week5.ApiInterface.Apiinterface;
import com.example.week5.Model.CustomAdapter;
import com.example.week5.Model.Result;
import com.example.week5.Model.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView r;
    String[] strr = {"one", "two", "three"};
    String[] strr2;
    Result ur[];
    TextView tv;
    public FirstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        ur = new Result[3];
        strr2 = new String[3];
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vi;
        vi= inflater.inflate(R.layout.fragment_first, container, false);
        tv = vi.findViewById(R.id.text_view);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://randomuser.me/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Apiinterface service = retrofit.create(Apiinterface.class);
        //Try service1 = retrofit.create(Try.class);

        Call<UserResponse> call = service.getMultipleUser(10);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {



                Log.v("", response.body().toString());
                //ur[0] = response.body().getResults().get(0);
                // ur[1] = response.body().getResults().get(1);
                strr2[0] = response.body().getResults().get(0).getName().getFirst();
                strr2[1] = response.body().getResults().get(1).getName().getFirst();
                strr2[2] = response.body().getResults().get(2).getName().getFirst();
                //strr2[1] = ur[1].getName().getFirst();
                tv.setText(strr2[0]);

                //textview.setText(response.body().getResults().get(0).getName().getFirst());
                //textview2.setText(response.body().getResults().get(0).getName().getLast());
                //textview3.setText(response.body().getResults().get(0).getEmail());
                //textview4.setText(response.body().getResults().get(0).getGender());
                //textview5.setText(response.body().getResults().get(0).getCell());
                //Log.w("", r);
                //Picasso.get().load(response.body().getResults().get(0).getPicture().getLarge()).into(imageview);
                //Picasso.get().load(response.body().getResults().get(0).getName().getFirst()).into();
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

                Log.e("", t.getLocalizedMessage());
            }
        });
        //tv.setText(strr2[0]);

        r = vi.findViewById(R.id.rv);
        r.getAdapter().notifyDataSetChanged();
        r.setAdapter(new CustomAdapter(strr2));
        r.setLayoutManager(new LinearLayoutManager(getActivity()));
        return vi;
    }
}
