package com.example.himanshuanand.musicvideoplayer;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemLongClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import android.util.Log;


public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<Uri> mUriList = new ArrayList<>();
    private ArrayList<Uri> mSongUriList = new ArrayList<>();
    private ArrayList<Uri> mBandUriList = new ArrayList<>();
    private String[] mFavouriteSong;
    private String[] mFavouriteArtist;
    private HashMap<String,String> mMusic;
    private ArrayList<HashMap<String, String>> musicList;
    private SimpleAdapter mMusicAdapter;

    private int[] mThumbId = new int[]{
            R.drawable.pinkfloyd,
            R.drawable.ledzeppelin,
            R.drawable.novemberrain,
            R.drawable.one,
            R.drawable.coldplay,
            R.drawable.nirvana,
            R.drawable.hello,
            R.drawable.highwaytohell,
            R.drawable.bohemianrhapsody,
            R.drawable.hotelcalifornia
    };



    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        mFavouriteSong = getResources().getStringArray(R.array.favouriteSong);
        mFavouriteArtist = getResources().getStringArray(R.array.favouriteArtist);
        mMusic = new HashMap<String, String>();
        musicList = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < mFavouriteSong.length; i++) {
            mMusic = new HashMap<String, String>();
            mMusic.put("song", mFavouriteSong[i]);
            mMusic.put("artist", mFavouriteArtist[i]);
            mMusic.put("thumbId",Integer.toString(mThumbId[i]));
            musicList.add(mMusic);
        }

        mMusicAdapter = new SimpleAdapter(this, musicList, R.layout.list_item,
                new String[]{"song",
                        "artist","thumbId"}, new int[]{R.id.title,
                R.id.artist,R.id.image});

        initializeSongUris();

        initializeSongWikiUris();

        initializeArtistWikiUris();


        listView.setAdapter(mMusicAdapter);

        //Register the ListView for Context Menu
        registerForContextMenu(listView);

        listView.setOnItemLongClickListener(new OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int arg2, long arg3) {
                //Register the ListView for Context Menu
                registerForContextMenu(listView);
                return false;
            }
        });


        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Uri aUri = mUriList.get(position);
                        Intent aIntent = new Intent(Intent.ACTION_VIEW);
                        aIntent.setData(aUri);
                        aIntent.addCategory(Intent.CATEGORY_BROWSABLE);
                        startActivity(aIntent);
                    }
                });
    }

    // Create Context Menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_music, menu);
    }

    // Process clicks on Context Menu Items
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position;
        switch (item.getItemId()) {
            case R.id.play_video:
                Uri aUri = mUriList.get(index);
                Intent aIntent = new Intent(Intent.ACTION_VIEW);
                aIntent.setData(aUri);
                aIntent.addCategory(Intent.CATEGORY_BROWSABLE);
                startActivity(aIntent);
                return true;
            case R.id.song_info:
                Uri sUri = mSongUriList.get(index);
                Intent sIntent = new Intent(Intent.ACTION_VIEW);
                sIntent.setData(sUri);
                sIntent.addCategory(Intent.CATEGORY_BROWSABLE);
                startActivity(sIntent);
                return true;
            case R.id.artist_info:
                Uri bUri = mBandUriList.get(index);
                Intent bIntent = new Intent(Intent.ACTION_VIEW);
                bIntent.setData(bUri);
                bIntent.addCategory(Intent.CATEGORY_BROWSABLE);
                startActivity(bIntent);
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){

            Log.e("On Configuration Change","LANDSCAPE");
        }else{

            Log.e("On Configuration Change","PORTRAIT");
        }
    }

    private void initializeSongUris() {

        mUriList.add(Uri.parse(getString(R.string.PinkFloyd_URI)));
        mUriList.add(Uri.parse(getString(R.string.LedZeppelin_URI)));
        mUriList.add(Uri.parse(getString(R.string.GunsNRoses_URI)));
        mUriList.add(Uri.parse(getString(R.string.Metallica_URI)));
        mUriList.add(Uri.parse(getString(R.string.ColdPlay_URI)));
        mUriList.add(Uri.parse(getString(R.string.Nirvana_URI)));
        mUriList.add(Uri.parse(getString(R.string.Adele_URI)));
        mUriList.add(Uri.parse(getString(R.string.ACDC_URI)));
        mUriList.add(Uri.parse(getString(R.string.Queen_URI)));
        mUriList.add(Uri.parse(getString(R.string.Eagles_URI)));
    }

    private void initializeSongWikiUris() {

        mSongUriList.add(Uri.parse(getString(R.string.PinkFloyd_song_URI)));
        mSongUriList.add(Uri.parse(getString(R.string.LedZeppelin_song_URI)));
        mSongUriList.add(Uri.parse(getString(R.string.GunsNRoses_song_URI)));
        mSongUriList.add(Uri.parse(getString(R.string.Metallica_song_URI)));
        mSongUriList.add(Uri.parse(getString(R.string.ColdPlay_song_URI)));
        mSongUriList.add(Uri.parse(getString(R.string.Nirvana_song_URI)));
        mSongUriList.add(Uri.parse(getString(R.string.Adele_song_URI)));
        mSongUriList.add(Uri.parse(getString(R.string.ACDC_song_URI)));
        mSongUriList.add(Uri.parse(getString(R.string.Queen_song_URI)));
        mSongUriList.add(Uri.parse(getString(R.string.Eagles_song_URI)));
    }

    private void initializeArtistWikiUris() {

        mBandUriList.add(Uri.parse(getString(R.string.PinkFloyd_band_URI)));
        mBandUriList.add(Uri.parse(getString(R.string.LedZeppelin_band_URI)));
        mBandUriList.add(Uri.parse(getString(R.string.GunsNRoses_band_URI)));
        mBandUriList.add(Uri.parse(getString(R.string.Metallica_band_URI)));
        mBandUriList.add(Uri.parse(getString(R.string.ColdPlay_band_URI)));
        mBandUriList.add(Uri.parse(getString(R.string.Nirvana_band_URI)));
        mBandUriList.add(Uri.parse(getString(R.string.Adele_artist_URI)));
        mBandUriList.add(Uri.parse(getString(R.string.ACDC_band_URI)));
        mBandUriList.add(Uri.parse(getString(R.string.Queen_band_URI)));
        mBandUriList.add(Uri.parse(getString(R.string.Eagles_band_URI)));
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
