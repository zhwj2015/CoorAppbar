package com.example.zhangweijin.coorappbar.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhangweijin.coorappbar.R;
import com.example.zhangweijin.coorappbar.adapter.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ToolbarContent.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ToolbarContent#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ToolbarContent extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TabLayout mTab;
    private ViewPager mPager;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ToolbarContent.
     */
    // TODO: Rename and change types and number of parameters
    public static ToolbarContent newInstance(String param1, String param2) {
        ToolbarContent fragment = new ToolbarContent();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ToolbarContent() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_toolbar_content, container, false);
        initView(view);
        setUpTab();
        return view;
    }
    private void initView(View view) {
        mPager = (ViewPager) view.findViewById(R.id.viewpager);
        mTab = (TabLayout) view.findViewById(R.id.tablayout);
    }

    private void setUpTab() {
        ContentFragment c =  ContentFragment.newInstance("test", R.drawable.toolbaricon);
        ContentFragment c1 =  ContentFragment.newInstance("test1", R.drawable.toolbaricon);
        ContentFragment c2 =  ContentFragment.newInstance("test2", R.drawable.toolbaricon);
        ContentFragment c3 =  ContentFragment.newInstance("test3", R.drawable.toolbaricon);
        List<Fragment> fragments  = new ArrayList<Fragment>();
        fragments.add(c);
        fragments.add(c1);
        fragments.add(c2);
        fragments.add(c3);
        List<CharSequence> titles = new ArrayList<CharSequence>();
        titles.add("商品列表");
        titles.add("商品详情");
        titles.add("商品评价");
        titles.add("商品推荐");
        PagerAdapter pa = new PagerAdapter(getActivity().getSupportFragmentManager() ,fragments, titles);
        mPager.setAdapter(pa);
//        mTab.addTab(mTab.newTab().setText("商品列表").setIcon(R.drawable.toolbaricon));
//        mTab.addTab(mTab.newTab().setText("商品详情").setIcon(R.drawable.toolbaricon));
//        mTab.addTab(mTab.newTab().setText("商品评价").setIcon(R.drawable.toolbaricon));
//        mTab.addTab(mTab.newTab().setText("商品推荐").setIcon(R.drawable.toolbaricon));

        mTab.setupWithViewPager(mPager);
        mTab.setTabsFromPagerAdapter(pa);

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
