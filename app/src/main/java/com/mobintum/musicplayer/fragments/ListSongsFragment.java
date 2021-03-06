package com.mobintum.musicplayer.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mobintum.musicplayer.R;
import com.mobintum.musicplayer.adapters.SongListAdapter;
import com.mobintum.musicplayer.models.Song;

import java.util.ArrayList;


public class ListSongsFragment extends Fragment {


    private static final String ARG_PARAM_ARRAY = "paramArray";


    private ArrayList<Song> songs;
    private ListView listSongs;
    private SongListAdapter adapter;

//Canal de comunicacion entre Manager y el fragmento actual
    private OnFragmentInteractionListener mListener;


    public static ListSongsFragment newInstance(ArrayList<Song> songs) {
        ListSongsFragment fragment = new ListSongsFragment();
        //caja de objetos
        Bundle args = new Bundle();

        args.putSerializable(ARG_PARAM_ARRAY, songs);

        fragment.setArguments(args);
        return fragment;
    }

    public ListSongsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.songs = (ArrayList<Song>) getArguments().getSerializable(ARG_PARAM_ARRAY   );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Se trae el XML que posee todos los elementos de la vista
        View viewRoot = inflater.inflate(R.layout.fragment_list_songs, container, false);

        listSongs = (ListView) viewRoot.findViewById(R.id.listSongs);

        adapter = new SongListAdapter(getActivity(),R.layout.item_listview_song, songs);

        listSongs.setAdapter(adapter);

        listSongs.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mListener != null ){
                    mListener.onSongSelected(position);
                }
            }
        });
        return viewRoot;
    }





    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        public void onSongSelected(int position);
    }

}
