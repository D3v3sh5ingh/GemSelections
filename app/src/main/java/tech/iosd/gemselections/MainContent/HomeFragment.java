package tech.iosd.gemselections.MainContent;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import tech.iosd.gemselections.Emerald.EmeraldActivity;
import tech.iosd.gemselections.Gomed.GomedActivity;
import tech.iosd.gemselections.Lehsunia.LehsuniaActivity;
import tech.iosd.gemselections.MoongaActivity;
import tech.iosd.gemselections.Neelam.NeelamActivity;
import tech.iosd.gemselections.Pukhraj.BainganiPukhraj;
import tech.iosd.gemselections.Pukhraj.GulabiPukhraj;
import tech.iosd.gemselections.Pukhraj.HaraPukhraj;
import tech.iosd.gemselections.Pukhraj.PitambariNeelam;
import tech.iosd.gemselections.Pukhraj.PukhrajActivity;
import tech.iosd.gemselections.Pukhraj.SafedPukhraj;
import tech.iosd.gemselections.R;
import tech.iosd.gemselections.Ruby.RubyActivity;
import tech.iosd.gemselections.SemiPreciousGems.MotiActivity;
import tech.iosd.gemselections.Utils.WebViewActivity;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anonymous on 17/6/17.
 */

public class HomeFragment extends Fragment implements View.OnClickListener {

    private ImageView baingani, panna, pukhraj, manik, neelam, gomed, gulabi, pitambari, lehsunia, moti, moonga, heera, safed, hara;

    private YouTubePlayer YPlayer;
    private static final String DEVELOPER_KEY = "AIzaSyBKlHdEkS-X7Vb2mW2qQSlF1TOxKzWpSU8";
    private static final int RECOVERY_REQUEST = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(tech.iosd.gemselections.R.layout.frag_main_home, container, false);

        TextView textView = (TextView)view.findViewById(R.id.marquee_text);

        YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();

        youTubePlayerFragment.initialize(DEVELOPER_KEY, new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {

                if (!wasRestored) {
                    YPlayer = player;
                    List<String> videos = new ArrayList<String>();
                    videos.add("0SMvY7ko5NA");
                    videos.add("TByLvRR0oLk");
                    videos.add("IdVOcN7p1mM");
                    videos.add("Mk9Es_UmZ6Y");
                    YPlayer.cueVideos(videos);

                    YPlayer.setOnFullscreenListener(new YouTubePlayer.OnFullscreenListener() {
                        @Override
                        public void onFullscreen(boolean b) {
                            if(!b){
                                //YPlayer.setFullscreen(false);
                                if(getActivity().getResources().getConfiguration().orientation != Configuration.ORIENTATION_PORTRAIT)
                                {
                                    getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                                }
                            }
                        }
                    });
                }


            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider arg0, YouTubeInitializationResult arg1) {
                if (arg1.isUserRecoverableError()) {
                    arg1.getErrorDialog(getActivity(), RECOVERY_REQUEST).show();
                } else {
                    String error = String.format(getString(tech.iosd.gemselections.R.string.player_error), arg1.toString());
                    Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show();
                }
            }
        });
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(tech.iosd.gemselections.R.id.youtube_fragment, youTubePlayerFragment).commit();

        baingani = (ImageView) view.findViewById(tech.iosd.gemselections.R.id.home_baingani);   baingani.setOnClickListener(this);
        panna = (ImageView) view.findViewById(tech.iosd.gemselections.R.id.home_panna);     panna.setOnClickListener(this);
        pukhraj = (ImageView) view.findViewById(tech.iosd.gemselections.R.id.home_stonePukhraj);    pukhraj.setOnClickListener(this);
        manik = (ImageView) view.findViewById(tech.iosd.gemselections.R.id.home_manik); manik.setOnClickListener(this);
        neelam = (ImageView) view.findViewById(tech.iosd.gemselections.R.id.home_neelam);   neelam.setOnClickListener(this);
        gomed = (ImageView) view.findViewById(tech.iosd.gemselections.R.id.home_gomed);     gomed.setOnClickListener(this);
        gulabi = (ImageView) view.findViewById(tech.iosd.gemselections.R.id.home_gulabi);       gulabi.setOnClickListener(this);
        pitambari = (ImageView) view.findViewById(tech.iosd.gemselections.R.id.home_pitambari);     pitambari.setOnClickListener(this);
        lehsunia = (ImageView) view.findViewById(tech.iosd.gemselections.R.id.home_lehsunia);       lehsunia.setOnClickListener(this);
        moti = (ImageView) view.findViewById(tech.iosd.gemselections.R.id.home_moti);   moti.setOnClickListener(this);
        moonga = (ImageView) view.findViewById(tech.iosd.gemselections.R.id.home_moonga);   moonga.setOnClickListener(this);
        heera = (ImageView) view.findViewById(tech.iosd.gemselections.R.id.home_heera);        heera.setOnClickListener(this);
        safed = (ImageView) view.findViewById(tech.iosd.gemselections.R.id.home_safed);     safed.setOnClickListener(this);
        hara = (ImageView) view.findViewById(tech.iosd.gemselections.R.id.home_hara);       hara.setOnClickListener(this);

//        Animation anim = new AlphaAnimation(0.0f, 1.0f);
//        anim.setDuration(30);
//        anim.setStartOffset(20);
//        anim.setRepeatMode(Animation.REVERSE);
//        anim.setRepeatCount(Animation.INFINITE);
//        textView.startAnimation(anim);

        textView.setSelected(true);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        load_Images();
        getActivity().setTitle("Gem Selections");
    }

    private void load_Images() {
        InputStream is = null;

        try {
            is = getActivity().getAssets().open("images/precious-gems/Purple-sapphire.jpg");
            baingani.setImageBitmap(BitmapFactory.decodeStream(is));

            is = getActivity().getAssets().open("images/precious-gems/Emerald.jpg");
            panna.setImageBitmap(BitmapFactory.decodeStream(is));

            is = getActivity().getAssets().open("images/precious-gems/peela-pukhraj.jpg");
            pukhraj.setImageBitmap(BitmapFactory.decodeStream(is));

            is = getActivity().getAssets().open("images/precious-gems/Indian-Ruby.jpg");
            manik.setImageBitmap(BitmapFactory.decodeStream(is));

            is = getActivity().getAssets().open("images/precious-gems/Blue-Sapphire.jpg");
            neelam.setImageBitmap(BitmapFactory.decodeStream(is));

            is = getActivity().getAssets().open("images/precious-gems/Gomedh.jpg");
            gomed.setImageBitmap(BitmapFactory.decodeStream(is));

            is = getActivity().getAssets().open("images/precious-gems/pink-sapphire.jpg");
            gulabi.setImageBitmap(BitmapFactory.decodeStream(is));

            is = getActivity().getAssets().open("images/precious-gems/Pitambari-neelam.jpg");
            pitambari.setImageBitmap(BitmapFactory.decodeStream(is));

            is = getActivity().getAssets().open("images/precious-gems/Cats-Eye.jpg");
            lehsunia.setImageBitmap(BitmapFactory.decodeStream(is));

            is = getActivity().getAssets().open("images/precious-gems/Pearl.jpg");
            moti.setImageBitmap(BitmapFactory.decodeStream(is));

            is = getActivity().getAssets().open("images/precious-gems/Red-Coral.jpg");
            moonga.setImageBitmap(BitmapFactory.decodeStream(is));

            is = getActivity().getAssets().open("images/precious-gems/diamond.jpg");
            heera.setImageBitmap(BitmapFactory.decodeStream(is));

            is = getActivity().getAssets().open("images/precious-gems/White-Sapphire.jpg");
            safed.setImageBitmap(BitmapFactory.decodeStream(is));

            is = getActivity().getAssets().open("images/precious-gems/green-sapphire.jpg");
            hara.setImageBitmap(BitmapFactory.decodeStream(is));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void show_dialog(final String title, final int content, final String imgurl, final String onclick, final String video_url) {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(tech.iosd.gemselections.R.layout.dialog_baingani_pukhraj);
        dialog.setTitle(title);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.copyFrom(dialog.getWindow().getAttributes());
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;

        dialog.show();
        dialog.getWindow().setAttributes(params);

        ImageView img = (ImageView) dialog.findViewById(tech.iosd.gemselections.R.id._img);
        try {
            InputStream is = getActivity().getAssets().open(imgurl);
            img.setImageBitmap(BitmapFactory.decodeStream(is));
        } catch (IOException e) {
            e.printStackTrace();
        }

        TextView textView = (TextView) dialog.findViewById(tech.iosd.gemselections.R.id._text);
        textView.setText(getString(content));

        Button btn = (Button) dialog.findViewById(tech.iosd.gemselections.R.id._button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(
                        new Intent(getActivity(), WebViewActivity.class)
                                .putExtra("URL",onclick)
                                .putExtra("parent", "MainActivity")
                );
                dialog.dismiss();
            }
        });

        Button btn1 = (Button) dialog.findViewById(tech.iosd.gemselections.R.id._video);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v="+video_url));
                startActivity(intent);
                dialog.dismiss();
            }
        });

    }

    @Override
    public void onClick(View v) {
        if(v == baingani){
            startActivity(
                    new Intent(getActivity(), BainganiPukhraj.class)
            );

           // show_dialog("Baingani Pukhraj", tech.iosd.gemselections.R.string.baingani_pukhraj_text, "images/precious-gems/gems/Purple-sapphire.jpg", "http://khannagems.com/index.php/gem-stones/baingani-pukhraj.html", "GT6d2ShkyQ4");
        }
        if(v == panna)
        {
            startActivity(
                    new Intent(getActivity(), EmeraldActivity.class)
            );
        }
        if(v == moti){
            startActivity(
                    new Intent(getActivity(), MotiActivity.class)
            );
           // show_dialog("Moti | Fresh Water Pearl", tech.iosd.gemselections.R.string.moti_text, "images/Fresh-water-pearl.jpg", "http://khannagems.com/index.php/gem-stones/moti.html", "5lbgEk97vbM");
        }
        if(v == pitambari){
            startActivity(
                    new Intent(getActivity(), PitambariNeelam.class)
            );
            // show_dialog("Pitambari Neelam", tech.iosd.gemselections.R.string.pitambari_text, "images/precious-gems/gems/Pitambari-neelam.jpg", "http://khannagems.com/index.php/gem-stones/pitambari-neelam.html", "CeDODoOMGCU");
        }
        if(v == safed){
            startActivity(
                    new Intent(getActivity(), SafedPukhraj.class)
            );
            //show_dialog("Safed Pukhraj", tech.iosd.gemselections.R.string.safed_text, "images/precious-gems/safed-pukhraj.jpg", "http://khannagems.com/index.php/gem-stones/pukhraj.html", "7LHUls5FNdk");
        }
        if(v == gulabi){
            startActivity(
                    new Intent(getActivity(), GulabiPukhraj.class)
            );
            //show_dialog("Gulabi Pukhraj", tech.iosd.gemselections.R.string.gulabi_text, "images/precious-gems/gems/pink-sapphire.jpg", "http://khannagems.com/index.php/gem-stones/pukhraj.html", "sgnb-nr1UHQ");
        }
        if(v == hara){
            startActivity(
                    new Intent(getActivity(), HaraPukhraj.class)
            );
            //show_dialog("Hara Pukhraj", tech.iosd.gemselections.R.string.hara_text, "images/precious-gems/hara-pukhraj.jpg", "http://khannagems.com/index.php/gem-stones/pukhraj.html", "GVOcqJeG5iM");
        }
        if(v == moonga){
            startActivity(
                    new Intent(getActivity(), MoongaActivity.class)
            );
        }
        if(v == manik){
            startActivity(
                    new Intent(getActivity(), RubyActivity.class)
            );
        }
        if(v == neelam){
            startActivity(
                    new Intent(getActivity(), NeelamActivity.class)
            );
        }
        if(v == pukhraj){
            startActivity(
                    new Intent(getActivity(), PukhrajActivity.class)
            );
        }
        if(v == gomed){
            startActivity(
                    new Intent(getActivity(), GomedActivity.class)
            );
        }
        if(v ==lehsunia){
            startActivity(
                    new Intent(getActivity(),LehsuniaActivity.class)
            );
        }
        if (v == heera) {
//            getActivity().getSupportFragmentManager()
//                    .beginTransaction()
//                    .add(R.id.container_main,new MainDiamondFragment())
//                    .addToBackStack("Main")
//                    .commit();
//            getFragmentManager().popBackStackImmediate();

            startActivity(new Intent(getActivity(),ActivityForDiamonds.class));



        }
    }
}