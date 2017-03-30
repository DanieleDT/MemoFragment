package com.error404.memometwo;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListMemoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListMemoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListMemoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private DAO dao;
    private OnItemSelectedListener listener;
    private ListView myListView;
    private ArrayList<Memo> memoList = new ArrayList<Memo>();
    private MemoAdapter mem;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ListMemoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListMemoFragment newInstance(String param1, String param2) {
        ListMemoFragment fragment = new ListMemoFragment();
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
        dao = new DAO(getContext());
        dao.open();
        memoList = dao.loadAllMemo();
        //FloatingActionButton buttonCreateMemo = (FloatingActionButton) findViewById(R.id.fab);
        //buttonCreateMemo.setOnClickListener(new View.OnClickListener() {

            /*public void onClick(View view) {
                Intent intent = new Intent(MemoMeMain.this, activity_modifyOrAdd.class);
                Bundle b = new Bundle();
                b.putInt(Values.BUNDLE_KEY, Values.NO_POSITION);
                intent.putExtras(b);
                startActivity(intent);
            }
        });*/
        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
          //      this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //drawer.setDrawerListener(toggle);
        //toggle.syncState();
        //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //navigationView.setNavigationItemSelectedListener(this);

       /* myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
           public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                if (memoList.get(position).getEncryption() == Values.TRUE) {
                    //alert dialog che prende in input la password e la verifica
                    alertEncrypted(position);

                } else {
                    //vado alla nuova activity
                    Intent myIntent = new Intent(MemoMeMain.this, ShowMemo.class);
                    Bundle bun = new Bundle();
                    bun.putInt(Values.BUNDLE_KEY, position);
                    myIntent.putExtras(bun);
                    startActivity(myIntent);
                }
            }
        });*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_memo_me_main, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mem = new MemoAdapter(getContext(), R.layout.rawlayout, memoList);//adapter deve essere un arraylist di memo
        myListView = (ListView) view.findViewById(R.id.listOfNotes);//id della list view nella prima activity
        myListView.setAdapter(mem);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // go to activity to load pizza details fragment
                listener.onMemoItemSelected(position); // (3) Communicate with Activity using Listener
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    public interface OnItemSelectedListener {
        // This can be any number of events to be sent to the activity
        void onMemoItemSelected(int position);
    }
}