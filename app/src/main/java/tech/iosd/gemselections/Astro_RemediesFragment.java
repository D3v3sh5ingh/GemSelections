package tech.iosd.gemselections;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tech.iosd.gemselections.Adapters.videoAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class Astro_RemediesFragment extends Fragment {
    View view;
    public Astro_RemediesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }
        // Inflate the layout for this fragment

        view= inflater.inflate(R.layout.fragment_astro__remedies, container, false);
        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.recyView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        videoAdapter adapter=new videoAdapter(getContext());
        recyclerView.setAdapter(adapter);
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Astrological Remedies");
    }

}
