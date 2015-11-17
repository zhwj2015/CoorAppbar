package com.example.zhangweijin.coorappbar.fragments;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.zhangweijin.coorappbar.R;
import com.example.zhangweijin.coorappbar.Utils.IpUtil;
import com.example.zhangweijin.coorappbar.Utils.NetworkUtil;
import com.example.zhangweijin.coorappbar.adapter.ListAdatpter;
import com.example.zhangweijin.coorappbar.model.Ip;
import com.example.zhangweijin.coorappbar.model.Person;


import java.util.ArrayList;
import java.util.List;


import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContentFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "name";
    private static final String ARG_PARAM2 = "image";

    // TODO: Rename and change types of parameters
    private String name;
    private int image;
    private RecyclerView.LayoutManager lm;
    private boolean isLoadingMore = false;
    private ListAdatpter adapter;
    private ProgressBar pb;
    private FloatingActionButton mFab;
    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param name Person name.
     * @param imageId Person Image.
     * @return A new instance of fragment ContentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContentFragment newInstance(String name, int  imageId) {
        ContentFragment fragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, name);
        args.putInt(ARG_PARAM2, imageId);
        fragment.setArguments(args);
        return fragment;
    }

    public ContentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(ARG_PARAM1);
            image = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_content, container, false);
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.recycler);
        pb = (ProgressBar) view.findViewById(R.id.progress);
        setUpRecycler(rv, view);
        setUpFab(view);
        return view;
    }
    private void setUpFab(final View view) {
        mFab = (FloatingActionButton) view.findViewById(R.id.fab);
        mFab.setVisibility(View.VISIBLE);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "gs", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setUpRecycler(RecyclerView mRecycler,final View view) {
        lm = new LinearLayoutManager(getActivity());
        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(lm);

        mRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = ((LinearLayoutManager) lm).findLastVisibleItemPosition();
                int totalItemCount = lm.getItemCount();
                if (lastVisibleItem >= totalItemCount -1 && dy > 0) {
                    if(isLoadingMore ){
                        Log.d("TAG","ignore manually update!");
                    } else{
                        if (NetworkUtil.isNetWorkAvailable(getActivity())) {
                            Log.d("isLoadingMore", Boolean.toString(isLoadingMore) );
                            pb.setVisibility(View.VISIBLE);
                            loadPage();//这里多线程也要手动控制isLoadingMore
                            isLoadingMore = true;

                        }else {
                            Snackbar.make(recyclerView,  "网络未连接", Snackbar.LENGTH_SHORT).setAction("设置", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = null;
                                    //判断手机系统的版本  即API大于10 就是3.0或以上版本
                                    if(android.os.Build.VERSION.SDK_INT>10){
                                        intent = new Intent(Settings.ACTION_SETTINGS);
                                    }else{
                                        intent = new Intent();
                                        ComponentName component = new ComponentName("com.android.settings","com.android.settings.Settings");
                                        intent.setComponent(component);
                                        intent.setAction("android.intent.action.VIEW");
                                    }
                                    getActivity().startActivity(intent);
                                }
                            }).show();
                        }

                    }
                }
            }
        });
        List<Person> persons = new ArrayList<Person>();
        Person p = new Person(name, image);
        Person p2 = new Person(name, image);
        Person p3 = new Person(name, image);
        Person p4 = new Person(name, image);
        persons.add(p);
        persons.add(p2);
        persons.add(p3);
        persons.add(p4);
        adapter = new ListAdatpter(persons);
        mRecycler.setAdapter(adapter);
    }

    private void loadPage() {
        String query = "202.202.33.33";
        IpUtil.taobaoIPService.getIp(query, new Callback<Ip>() {
            @Override
            public void success(Ip ip, Response response) {
                synchronized (this){
                    Person p = new Person(ip.getData().getArea(), R.drawable.toolbaricon);
                    adapter.addPerson(p);
                    isLoadingMore = false;
                    pb.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity(), error.getResponse().getBody().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
