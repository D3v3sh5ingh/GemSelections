package tech.iosd.gemselections.Ruby;

import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import tech.iosd.gemselections.Utils.WebViewActivity;
import tech.iosd.gemselections.R;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

/**
 * Created by anonymous on 29/6/17.
 */

public class RubyActivity extends YouTubeBaseActivity implements AdapterView.OnItemSelectedListener, YouTubePlayer.OnInitializedListener {

    private Bitmap img;
    private ImageView banner;

    boolean first = false;

    private YouTubePlayerView playerView;
    private static final String DEVELOPER_KEY = "AIzaSyBKlHdEkS-X7Vb2mW2qQSlF1TOxKzWpSU8";
    private static final int RECOVERY_REQUEST = 1;


    private String[] _options =
            {
                    "ABOUT RUBY | MANIK",
                    "INDIAN RUBY",
                    "AFRICAN RUBY",
                    "NEW BURMA RUBY",
                    "OLD BURMA RUBY",
                    "RUBY IDENTIFICATION"
            };

    private String[] _quality1 =
            {
                    "----- NONE -----",
                    "A Quality",
                    "B Quality",
                    "C Quality",
                    "D Quality",
                    "E Quality",
                    "F Quality"
            };

    private String[] _quality2 = {
            "---- NONE -----",
            "A Qualtiy",
            "B Quality",
            "C Quality"
    };
    private Spinner spinner1, spinner2, spinner3;
    private ArrayAdapter<String> arrayAdapter1;
    private ArrayAdapter<String> arrayAdapter2;
    private ArrayAdapter<String> arrayAdapter3;
    private android.widget.Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruby);

        toolbar = (Toolbar)findViewById(R.id.ruby_toolbar);
        setActionBar(toolbar);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        banner = (ImageView)findViewById(R.id.ruby_banner);
        try{
            InputStream is = getAssets().open("images/ruby/about-ruby-gemstone.jpg");
            banner.setImageBitmap(BitmapFactory.decodeStream(is));
        }catch (IOException e){
            e.printStackTrace();
        }
        playerView = (YouTubePlayerView)findViewById(R.id.ruby_video);
        playerView.initialize(DEVELOPER_KEY, this);

        spinner1 = (Spinner)findViewById(R.id.ruby_spinner);
        arrayAdapter1 = new ArrayAdapter<String>(RubyActivity.this ,android.R.layout.simple_spinner_dropdown_item, _options);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(arrayAdapter1);
        spinner1.setOnItemSelectedListener(this);

        spinner2 = (Spinner)findViewById(R.id.ruby_spinner2);
        arrayAdapter2 = new ArrayAdapter<String>(RubyActivity.this ,android.R.layout.simple_spinner_dropdown_item, _quality1);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(arrayAdapter2);
        spinner2.setOnItemSelectedListener(this);

        spinner3 = (Spinner)findViewById(R.id.ruby_spinner3);
        arrayAdapter3 = new ArrayAdapter<String>(RubyActivity.this ,android.R.layout.simple_spinner_dropdown_item, _quality2);
        arrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(arrayAdapter3);
        spinner3.setOnItemSelectedListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.ac_buy_ruby:
                startActivity(
                        new Intent(RubyActivity.this, WebViewActivity.class)
                                .putExtra("URL","http://khannagems.com/index.php/gem-stones/ruby.html")
                                .putExtra("parent", "RubyActivity")
                );
                return true;

            case R.id.ac_ruby_certi:
                display_image("images/Govt-lab-certified-ruby.jpg", "Govt. Certified");
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void show_dialog(String title, int content) {
        Dialog dialog = new Dialog(RubyActivity.this);
        dialog.setTitle(title);
        dialog.setContentView(R.layout.dialog_bracelet);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());

        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        dialog.show();
        dialog.getWindow().setAttributes(layoutParams);

        TextView textView  = (TextView)dialog.findViewById(R.id.content);
        textView.setText(getString(content));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ruby_menu, menu);
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent == spinner1){
            Fragment fragment = null;
            switch (position){

                case 0:
                        fragment = new RubyInfo();
                    first = true;
                    break;

                case 1:
                        fragment = new IndianRuby();
                    break;

                case 2:
                        fragment = new AfricanRuby();
                    break;

                case 3:
                        fragment = new NewBurmaRuby();
                    break;

                case 4:
                        fragment = new OldBurmaRuby();
                    break;

                case 5:
                    startActivity(
                            new Intent(RubyActivity.this, WebViewActivity.class)
                                    .putExtra("URL","http://gemselections.in/ruby-identification.htm")
                                    .putExtra("parent","RubyActivity")
                    );
                    break;
            }
            if(fragment!=null){
                getFragmentManager().beginTransaction()
                        .replace(R.id.ruby_container, fragment)
                        .commit();
                if(first){
                    first = false;
                }else {
                    View targetView = findViewById(R.id.ruby_container);
                    targetView.getParent().requestChildFocus(targetView, targetView);
                }
            }
        }
        if(parent == spinner2){
            switch (position){
                case 0:
                    break;
                case 1:
                    show_dialog("A Grade", R.string.nonheated_manik1);
                    break;
                case 2:
                    show_dialog("B Grade", R.string.nonheated_manik2);
                    break;
                case 3:
                    show_dialog("C Grade", R.string.nonheated_manik3);
                    break;
                case 4:
                    show_dialog("D Grade", R.string.nonheated_manik4);
                    break;
                case 5:
                    show_dialog("E Grade", R.string.nonheated_manik5);
                    break;
                case 6:
                    show_dialog("F Grade", R.string.nonheated_manik6);
                    break;
            }
        }
        if(parent == spinner3){
            switch (position){
                case 0:
                    break;
                case 1:
                    show_dialog("A Grade", R.string.heated_manik1);
                    break;
                case 2:
                    show_dialog("B Grade", R.string.heated_manik2);
                    break;
                case 3:
                    show_dialog("C Grade", R.string.heated_manik3);
                    break;
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        parent.setSelection(0);
    }

    private void display_image(String url, String title) {

        Dialog dialog = new Dialog(RubyActivity.this);
        dialog.setContentView(R.layout.dialog_display_images);
        dialog.setTitle(title);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());

        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        dialog.show();
        dialog.getWindow().setAttributes(layoutParams);

        ImageView iv = (ImageView)dialog.findViewById(R.id.image_here);
        final InputStream in;
        img = null;
        try {
            in = getAssets().open(url);
            img = BitmapFactory.decodeStream(in);
            iv.setImageBitmap(img);

        } catch (IOException e) {
            e.printStackTrace();
        }

        final FloatingActionButton save = (FloatingActionButton)dialog.findViewById(R.id.fab_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(getApplicationContext(),
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(RubyActivity.this,
                            new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                }else{
                    save_image();
                }
            }
        });
    }

    private void save_image(){
        final String filePath = Environment.getExternalStorageDirectory().toString();

        File dir = new File(filePath + "/gemselections_images");
        dir.mkdirs();

        Random generate = new Random();
        int n = 10000;
        n = generate.nextInt(n);

        String fName = "Image-" + n + ".jpg";

        File file = new File(dir, fName);
        if (file.exists()) {
            n++;
            fName = "Image-" + n*10 + ".jpg";
            file = new File(dir, fName);
        }

        try {
            FileOutputStream out = new FileOutputStream(file);
            img.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            final File finalFile = file;
            new AlertDialog.Builder(RubyActivity.this)
                    .setTitle("Image Saved Successfully")
                    .setIcon(R.drawable.ic_save)
                    .setMessage("Image saved at: " + file.getAbsolutePath())
                    .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setPositiveButton("Open", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(), "Opening...", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setDataAndType(Uri.parse("file://" + finalFile.getAbsolutePath()), "image/*");
                            startActivity(intent);
                        }
                    })
                    .create().show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(RubyActivity.this, "Could not save image", Toast.LENGTH_SHORT).show();
        } catch (NullPointerException e) {
            Toast.makeText(RubyActivity.this, "Could not save image", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_REQUEST) {

            getYouTubePlayerProvider().initialize(DEVELOPER_KEY, this);
        }

        if(requestCode == 1){
            save_image();
        }
    }

    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return playerView;
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if(!b){
            youTubePlayer.cueVideo("9y4KM5FflE8");
            youTubePlayer.setOnFullscreenListener(new YouTubePlayer.OnFullscreenListener() {
                @Override
                public void onFullscreen(boolean b) {
                    if(!b){
                        //youTubePlayer.setFullscreen(false);
                        if(getResources().getConfiguration().orientation != Configuration.ORIENTATION_PORTRAIT)
                        {
                            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                        }
                    }
                }
            });
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(RubyActivity.this, RECOVERY_REQUEST).show();
        } else {
            String error = String.format(getString(R.string.player_error), youTubeInitializationResult.toString());
            Toast.makeText(RubyActivity.this, error, Toast.LENGTH_LONG).show();
        }
    }
}
