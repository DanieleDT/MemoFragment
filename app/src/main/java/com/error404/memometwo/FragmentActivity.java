package com.error404.memometwo;

import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FragmentActivity extends AppCompatActivity implements ListMemoFragment.OnItemSelectedListener,ListMemoFragment.OnFragmentInteractionListener,ShowMemoFragment.OnFragmentInteractionListener,ModifyOrAddFragment.OnFragmentInteractionListener{
    private static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        context=getApplicationContext();
        if (savedInstanceState == null) {
            // Instance of first fragment
            ListMemoFragment firstFragment = new ListMemoFragment();
            // Add Fragment to FrameLayout (flContainer), using FragmentManager
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();// begin  FragmentTransaction
            ft.add(R.id.flContainer, firstFragment);                                // add    Fragment
            ft.commit();                                                            // commit FragmentTransaction
        }
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            System.out.println("ciao");
            //ShowMemoFragment secondFragment = new ShowMemoFragment();
            //Bundle args = new Bundle();
            //args.putInt("position", 0);//del secondo fragment seleziono il primo elemento
            //secondFragment.setArguments(args);          // (1) Communicate with Fragment using Bundle
            //FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();// begin  FragmentTransaction
            //ft2.add(R.id.fl2Container, secondFragment);                               // add    Fragment
            //ft2.commit();                                                            // commit FragmentTransaction
        }
    }

    public void onMemoItemSelected(int position){
        System.out.println(position);
    }
    public void onFragmentInteraction(Uri uri){

    }
    public static Context getIstanceContext(){
        return context;
    }
    @Override
    public void onBackPressed(){
        int fragments = getFragmentManager().getBackStackEntryCount();
        if (fragments == 1) {
            finish();
        }
        super.onBackPressed();

        //ciao

        getSupportFragmentManager().popBackStack();
        System.out.println("back pressed");
    }
}
