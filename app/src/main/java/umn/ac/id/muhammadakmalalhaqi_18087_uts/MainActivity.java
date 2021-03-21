package umn.ac.id.muhammadakmalalhaqi_18087_uts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<String> lagus;
    EditText search;
    List<Song> songList;
    ListView songView;
    final Context context = this;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.activity_alert);
        dialog.show();
        button = (Button) dialog.findViewById(R.id.buttonShowCustomDialog);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            dialog.dismiss();
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);


        songList= new ArrayList<Song>();

        songList.add(new Song(R.raw.itzy_wannabe, "itzy_wannabe.mp3"));

        songList.add(new Song(R.raw.iu_dearname, "iu_dearname.mp3"));

        songList.add(new Song(R.raw.taeyeon_i, "taeyeon_i.mp3"));

        songList.add(new Song(R.raw.oh_my_girl_dolphin,"oh_my_girl_dolphin.mp3"));
        songList.add(new Song(R.raw.bts_dope, "bts_dope.mp3"));
        songList.add(new Song(R.raw.snsd_lionheart, "snsd_lionheart.mp3"));
        songList.add(new Song(R.raw.mamamoo_hip, "mamamoo_hip.mp3"));
        

        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new MyAdapter(songList,MainActivity.this);
        recyclerView.setAdapter(mAdapter);


        this.initializeViews();
    }
    private void initializeViews(){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    public static int stringCompare(String str1, String str2)
    {
        int l1 = str1.length();
        int l2 = str2.length();
        int lmin = Math.min(l1, l2);

        for (int i = 0; i < lmin; i++) {
            int str1_ch = (int)str1.charAt(i);
            int str2_ch = (int)str2.charAt(i);

            if (str1_ch != str2_ch) {
                return str1_ch - str2_ch;
            }
        }


        if (l1 != l2) {
            return l1 - l2;
        }


        else {
            return 0;
        }
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.itMP){
            startActivity(new Intent(this, MyProfile.class));
        }
        else
        if (item.getItemId() == R.id.itLogout) {
            startActivity(new Intent(this,Login.class));
            finish();
            Toast.makeText(this, "LOGOUT", Toast.LENGTH_SHORT).show();
        }



        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionmenu, menu);
        return true;

    }
}
