package com.example.nikhil3000.gemselections;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        display_selected_item(R.id.nav_home);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

        getSupportFragmentManager().popBackStack("MainActivity", 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.ac_buy_now:
                startActivity(
                        Intent.createChooser(
                                new Intent(Intent.ACTION_VIEW)
                                        .setData(Uri.parse("http://khannagems.com")), "Open Shopping page via..."
                        )
                );
                break;

            case R.id.ac_payment_methods:
                payment_options();
                break;

            case R.id.ac_natural_sapphire:
                    display_image("images/lab-certificate.jpg", "Natural Sapphire");
                break;
            case R.id.ac_treated_sapphire:
                    display_image("images/Treated-Sapphire.jpg", "Treated Sapphire");
                break;
            case R.id.ac_heated_sapphire:
                display_image("images/Heated-Sapphire.jpg", "Heated Sapphire");
                break;
            case R.id.ac_testimonials:
                display_image("images/testimonials.jpg", "Testimonials");
                break;
            case R.id.ac_shipment:
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Deliver in World")
                        .setIcon(R.drawable.ic_delivery)
                        .setMessage(getString(R.string.delivery_message))
                        .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();
                break;

            case R.id.ac_aima:
                display_image("images/all-india-management.jpg", "ALL INDIA MANAGEMENT ASSOCIATION");
                break;

            case R.id.ac_idi:
                display_image("images/indian-diamond-institute.jpg", "INDIAN DIAMOND INSTITUTE");
                break;

            case R.id.ac_sgi:
                display_image("images/surat-gemology-institute.jpg", "SURAT GEMOLOGY INSTITUTE");
                break;

            case R.id.ac_astro:
                startActivity(
                        Intent.createChooser(
                                new Intent(Intent.ACTION_VIEW)
                                        .setData(Uri.parse("http://www.astropankaj.com")), "Open www.astropankaj.com via..."
                        )
                );
                break;

            case R.id.ac_puja:
                startActivity(
                        Intent.createChooser(
                                new Intent(Intent.ACTION_VIEW)
                                        .setData(Uri.parse("http://www.vedmandirtrust.com")), "Open Ved Mandir Trust via..."
                        )
                );

                break;

            case R.id.ac_faq:
                    startActivity(
                            new Intent(MainActivity.this, FAQs.class)
                    );
                break;

            case R.id.ac_about_us:
                    display_selected_item(R.id.ac_about_us);
                break;

            case R.id.ac_management:
                    display_selected_item(R.id.ac_management);
                break;

            case R.id.ac_pankaj:
                    dialog_pankaj();
                break;

            case R.id.ac_khanna_gems:
                    dialog_khanna_gems();
                break;

            case R.id.ac_sonipat:
                    display_selected_item(R.id.ac_sonipat);
                break;

            case R.id.ac_exit:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void dialog_khanna_gems() {
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setTitle("Khanna Gems Pvt. Ltd.");
        dialog.setContentView(R.layout.dialog_khanna_gems);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());

        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        dialog.show();
        dialog.getWindow().setAttributes(layoutParams);
    }

    private void dialog_pankaj() {
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setTitle("Sh. Pankaj Khanna");
        dialog.setContentView(R.layout.dialog_pankaj_khanna);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());

        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        dialog.show();
        dialog.getWindow().setAttributes(layoutParams);

        ImageView img = (ImageView)dialog.findViewById(R.id.dialog_pankaj_image);
        InputStream is;
        try{
            is = getAssets().open("images/pankaj-khanna.jpg");
            img.setImageBitmap(BitmapFactory.decodeStream(is));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void payment_options() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setTitle("$ Payment Methods");
        dialog.setContentView(R.layout.dialog_payment_method);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());

        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        dialog.show();
        dialog.getWindow().setAttributes(layoutParams);

        Button OK = (Button)dialog.findViewById(R.id.ok_button);
        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Button Buy = (Button)dialog.findViewById(R.id.buy);
        Buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(
                        new Intent(Intent.ACTION_VIEW)
                                .setData(Uri.parse("http://www.khannagems.com"))
                );
            }
        });
    }

    private void display_image(String url, String title) {

        Dialog dialog = new Dialog(MainActivity.this);
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
        Bitmap img=null;
        final Bitmap imgcpy;

        try {
            in = getAssets().open(url);
            img = BitmapFactory.decodeStream(in);
            iv.setImageBitmap(img);

        } catch (IOException e) {
            e.printStackTrace();
        }

        imgcpy = img;

        final FloatingActionButton save = (FloatingActionButton)dialog.findViewById(R.id.fab_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File filePath = Environment.getExternalStorageDirectory();

                File dir = new File(filePath.getAbsoluteFile()+"/SavedImage/");
                dir.mkdirs();

                File file = new File(dir, "image.jpg");

                FileOutputStream fos=null;

                try{
                    fos = new FileOutputStream(file);

                    imgcpy.compress(Bitmap.CompressFormat.JPEG, 90, fos);

                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    try{
                        fos.flush();
                        fos.close();
                        final String path = file.getAbsolutePath();
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Image Saved Successfully")
                                .setIcon(R.drawable.ic_save)
                                .setMessage("Image saved at: "+path)
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
                                        intent.setDataAndType(Uri.parse("file://"+path), "image/*");
                                        startActivity(intent);
                                    }
                                })
                                .create().show();
                    }catch (IOException e){
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Could Not Save Image", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        display_selected_item(id);

        return true;
    }

    private void display_selected_item(int id) {

        Fragment fragment = null;

        switch (id){
            case R.id.nav_home:
                    fragment = new HomeFragment();
                break;

            case R.id.nav_call:

                new AlertDialog.Builder(MainActivity.this)
                        .setMessage("Which number to open in dialer?")
                        .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("+919213932017", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Intent.ACTION_DIAL);
                                intent.setData(Uri.parse("tel:" + Uri.encode("+919213932017")));
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("+919999136878", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Intent.ACTION_DIAL);
                                intent.setData(Uri.parse("tel:" + Uri.encode("+919999136878")));
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        })
                        .create().show();

                break;

            case R.id.nav_mail:
                String uritext = "care@khannagems.com";
                Uri uri = Uri.parse(uritext);
                Intent intent1 = new Intent(Intent.ACTION_SENDTO);
                intent1.setData(uri);
                startActivity(Intent.createChooser(intent1, "Mail Via..."));
                break;

            case R.id.nav_visit:
                    fragment = new VisitUsFragment();
                break;

            case R.id.nav_fb_page:
                    startActivity(
                            new Intent(Intent.ACTION_VIEW)
                            .setData(Uri.parse("http://www.facebook.com/GemSelections.in/"))
                    );
                break;

            case R.id.ac_about_us:
                    fragment = new AboutUsFragment();
                break;

            case R.id.ac_management:
                    fragment = new ManagementFragment();
                break;

            case R.id.ac_sonipat:
                    fragment = new SonipatFragment();
                break;

        }

        if(fragment!=null){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, fragment);
            fragmentTransaction.addToBackStack("MainActivity");
            fragmentTransaction.commit();

            //getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment)
            //        .commit();

            //int homeFragmentIdentifier = fragmentTransaction.commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
}
