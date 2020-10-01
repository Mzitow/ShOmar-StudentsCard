package com.example.shomarappstudents;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainActivity extends AppCompatActivity {

    private  int[] numOfVerseInEverySurah = new int[]{7,286,200,176,120,165,206,75,129,109,123,111,43,52,99,128,111,110,98,135,112,78,118,64,77,227,93,88,69,60,34,30,73,54,45,83,182,88,75,85, 54,53,89,59,37,35,38,29,18,45,60,49,62,55,78,96,29,22,24,13,14,11,11,18,12,12,30,52,52,44,28,28,20,56,40,31,50,40,46,42,29,19,36,25,22,17,19,26,30,20,15,21,11,8,8,19,5,8,8,11,11,8,3,9,5,4,7,3,6,3,5,4,5,6};
    String[] surahs = new String[] { "الفاتحة", "البقرة","ال عمران" , "النساء", "المائد", "الأنعام", "الأعراف", "الأنفال" , "التوبة", "يونس", "هود", "يوسف", "الرعد" , "إبراهيم", "الحجر", "النحل" , "الإسراء", "الكهف", "مريم", "طه", "الأنبياء", "الحج", "المؤمنون", "النور", "الفرقان","الشعراء","النمل","القصص","العنكبوت","الروم" ,"لقمان","السجدة","الأحزاب","سبأ","فاطر","يس","الصافات","ص","الزمر","غافر" ,"فصلت","الشورى","الزخرف","الدخان","الجاثية","الأحقاف","محمد","الفتح","الحجرات","ق","الذاريات","الطور","النجم","القمر","الرحمن","الواقعة","الحديد","المجادلة","الحشر","الممتحنة","الصف","الجمعة","المنافقون","التغابن","الطلاق","التحريم","الملك","القلم","الحاقة","المعارج","نوح" ,"الجن","المزمل","المدثر","القيامة","الإنسان","المرسلات","النبأ","النازعات","عبس","التكوير","الإنفطار","المطففين","الإنشقاق","البروج","الطارق","الأعلى","الغاشية","الفجر","البلد","الشمس","الليل","الضحى","الشرح","التين","العلق","القدر","البينة","الزلزلة","العاديات","القارعة","التكاثر","العصر","الهمزة","الفيل","قريش","الماعون","الكوثر" ,"الكافرون","النصر","المسد","الإخلاص","الفلق","الناس"};

    FrameLayout forgetCard;
    FrameLayout riwaayaCard;
    FrameLayout mistakeCard;
    FrameLayout doubtCard;

     private Handler h;
     private SpinAdapter adapter , verseAdapter;
     private  int noOfVersesInSelectedStartSurah  ,noOfVersesInSelectedEndSurah;
     private ArrayList<SpinAdapter.SpinItems>  spinItems , spinnerVerses;
     private ArrayList<SpinAdapter.SpinItems> spinItemsVerse;

    Spinner endSurahSpinner, startSurahSpinner, endVerseSpinner, startVerseSpinner, levelSpinner, studentNameSpinner;
    ImageView refreshBtn;


   int  currentEndSurah = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.startSurahSpinner= findViewById(R.id.sp_start_surah);
        this.startVerseSpinner = findViewById(R.id.sp_start_verse);

        this.endSurahSpinner = findViewById(R.id.sp_end_surah);
        this.endVerseSpinner = findViewById(R.id.sp_end_verse);

        this.refreshBtn = findViewById(R.id.refresh_btn_icon);

        this.levelSpinner = findViewById(R.id.sp_students_level);
        this.studentNameSpinner = findViewById(R.id.sp_student_name);

        forgetCard = findViewById(R.id.forget);
        riwaayaCard = findViewById(R.id.riwaaya);
        mistakeCard = findViewById(R.id.mistake);
        doubtCard = findViewById(R.id.doubt);
        manageMyMistakeCards();

        spinItems = new ArrayList<SpinAdapter.SpinItems>(114);

        convertArrayToSpinItemArray(surahs,spinItems);

        adapter = new SpinAdapter(MainActivity.this, R.layout.spinner_item,  spinItems);
        endSurahSpinner.setAdapter(adapter);
        startSurahSpinner.setAdapter(adapter);



        endSurahSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {

                {// Here you get the current item (a User object) that is selected by its position
                    SpinAdapter.SpinItems user = adapter.getItem(position);

                    noOfVersesInSelectedEndSurah = numOfVerseInEverySurah[position];

                    processVerses();
                    // Here you can do the action you want to...
                    Toast.makeText(MainActivity.this, "ID: " + user.getId() + "\nName: " + user.getSpinnerItem() + " Verses: " + noOfVersesInSelectedEndSurah,Toast.LENGTH_SHORT).show();

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapter) {  }
        });

        endVerseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                // Here you get the current item (a User object) that is selected by its position
                SpinAdapter.SpinItems user = adapter.getItem(position);

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapter) {  }
        });

        startVerseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                // Here you get the current item (a User object) that is selected by its position
                SpinAdapter.SpinItems user = adapter.getItem(position);

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapter) {  }
        });

        startSurahSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {

                {// Here you get the current item (a User object) that is selected by its position
                    SpinAdapter.SpinItems user = adapter.getItem(position);

                    noOfVersesInSelectedEndSurah = numOfVerseInEverySurah[position];

                    processVersesIn2();
                    // Here you can do the action you want to...

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapter) {  }
        });


        final Animation rotateAlways = AnimationUtils.loadAnimation(this, R.anim.rotate_always);
        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshBtn.startAnimation(rotateAlways);
            }
        });



    }

    @SuppressLint("ClickableViewAccessibility")
    private void manageMyMistakeCards() {

        ((TextView) forgetCard.findViewById(R.id.name)).setText("النسيان");
        ((TextView) mistakeCard.findViewById(R.id.name)).setText("خطأ");
        ((TextView) doubtCard.findViewById(R.id.name)).setText("الشك");
        ((TextView) riwaayaCard.findViewById(R.id.name)).setText("الرواية");

        doubtCard.setOnTouchListener(new View.OnTouchListener() {
            int alert = 0;

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                alert = Integer.parseInt(((TextView) doubtCard.findViewById(R.id.number)).getText().toString().trim());

                Rect outRect = new Rect();
                doubtCard.getGlobalVisibleRect(outRect);

                // calculate new bottom, to be used for upper part.
                int bottom = (int) (outRect.bottom * 0.95);

                Rect upperRec = new Rect(outRect.left, outRect.top, outRect.right, bottom);

                if (upperRec.contains((int) event.getRawX(), (int) event.getRawY())) {
                    alert++;
                    ((TextView) doubtCard.findViewById(R.id.number)).setText(String.format(Locale.ENGLISH, "%02d", alert));
                } else {
                    if (alert > 0) {
                        --alert;
                        ((TextView) doubtCard.findViewById(R.id.number)).setText(String.format(Locale.ENGLISH, "%02d", alert));
                    }

                }


                return false;
            }
        });

        riwaayaCard.setOnTouchListener(new View.OnTouchListener() {
            int alert = 0;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int riwaaya;
              riwaaya  = Integer.parseInt(((TextView) riwaayaCard.findViewById(R.id.number)).getText().toString().trim());

                Rect outRect = new Rect();
                riwaayaCard.getGlobalVisibleRect(outRect);

                // calculate new bottom, to be used for upper part.
                int bottom = (int) (outRect.bottom * 0.95);

                Rect upperRec = new Rect(outRect.left, outRect.top, outRect.right, bottom);

                if (upperRec.contains((int) event.getRawX(), (int) event.getRawY())) {
                    riwaaya++;
                    ((TextView) riwaayaCard.findViewById(R.id.number)).setText(String.format(Locale.ENGLISH, "%02d", riwaaya));
                } else {
                    if (alert > 0) {
                        --riwaaya;
                        ((TextView) riwaayaCard.findViewById(R.id.number)).setText(String.format(Locale.ENGLISH, "%02d", riwaaya));
                    }

                }


                return false;
            }
        });

        mistakeCard.setOnTouchListener(new View.OnTouchListener() {
            int mistake = 0;
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                mistake = Integer.parseInt(((TextView) mistakeCard.findViewById(R.id.number)).getText().toString().trim());

                Rect outRect = new Rect();
                mistakeCard.getGlobalVisibleRect(outRect);

                // calculate new bottom, to be used for upper part.
                int bottom = (int) (outRect.bottom * 0.95);

                Rect upperRec = new Rect(outRect.left, outRect.top, outRect.right, bottom);

                if (upperRec.contains((int) event.getRawX(), (int) event.getRawY())) {
                    ++mistake;
                    ((TextView) mistakeCard.findViewById(R.id.number)).setText(String.format(Locale.ENGLISH, "%02d", mistake));
                } else {
                    if (mistake > 0) {
                        --mistake;
                        ((TextView) mistakeCard.findViewById(R.id.number)).setText(String.format(Locale.ENGLISH, "%02d", mistake));
                    }

                }

                return false;
            }
        });

        forgetCard.setOnTouchListener(new View.OnTouchListener() {

            int tajweed =0;
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                tajweed = Integer.parseInt(((TextView) forgetCard.findViewById(R.id.number)).getText().toString().trim());

                Rect outRect = new Rect();
                forgetCard.getGlobalVisibleRect(outRect);

                // calculate new bottom, to be used for upper part.
                int bottom = (int) (outRect.bottom * 0.95);

                Rect upperRec = new Rect(outRect.left, outRect.top, outRect.right, bottom);

                if (upperRec.contains((int) event.getRawX(), (int) event.getRawY())) {
                    ++tajweed;
                    ((TextView) forgetCard.findViewById(R.id.number)).setText(String.format(Locale.ENGLISH, "%02d", tajweed));
                } else {
                    if (tajweed > 0) {
                        --tajweed;
                        ((TextView) forgetCard.findViewById(R.id.number)).setText(String.format(Locale.ENGLISH, "%02d", tajweed));
                    }

                }

                return false;
            }
        });


    }

    private void processVerses() {
        spinnerVerses = new ArrayList<>(noOfVersesInSelectedEndSurah);

        convertArrayOfVersesToSpinnerItems(noOfVersesInSelectedEndSurah,spinnerVerses);

        verseAdapter = new SpinAdapter(MainActivity.this, R.layout.spinner_item, spinnerVerses);

        endVerseSpinner.setAdapter(verseAdapter);
        h = new Handler();
        // Spawn a thread that triggers the Spinner to open after 5 seconds...
        new Thread(new Runnable() {
            public void run() {
                // DO NOT ATTEMPT TO DIRECTLY UPDATE THE UI HERE, IT WON'T WORK!
                // YOU MUST POST THE WORK TO THE UI THREAD'S HANDLER
                h.postDelayed(new Runnable() {
                    public void run() {
                        // Open the Spinner...
                        endVerseSpinner.performClick();
                    }
                }, 100);
            }
        }).start();

    }

    private void processVersesIn2() {
        spinnerVerses = new ArrayList<>(noOfVersesInSelectedEndSurah);

        convertArrayOfVersesToSpinnerItems(noOfVersesInSelectedEndSurah,spinnerVerses);

        verseAdapter = new SpinAdapter(MainActivity.this, R.layout.spinner_item, spinnerVerses);

        startVerseSpinner.setAdapter(verseAdapter);
        h = new Handler();
        // Spawn a thread that triggers the Spinner to open after 5 seconds...
        new Thread(new Runnable() {
            public void run() {
                // DO NOT ATTEMPT TO DIRECTLY UPDATE THE UI HERE, IT WON'T WORK!
                // YOU MUST POST THE WORK TO THE UI THREAD'S HANDLER
                h.postDelayed(new Runnable() {
                    public void run() {
                        // Open the Spinner...
                        endVerseSpinner.performClick();
                    }
                }, 100);
            }
        }).start();

    }

    private void convertArrayOfVersesToSpinnerItems(int noOfVersesInSelectedEndSurah, ArrayList<SpinAdapter.SpinItems> spinnerVerses) {


        for (int i = 0; i < noOfVersesInSelectedEndSurah; i++)
        {
                spinnerVerses.add(new SpinAdapter.SpinItems(i, Integer.toString((i+1))));
        }
    }

    private void convertArrayToSpinItemArray(String[] surahs, ArrayList<SpinAdapter.SpinItems> spinItems) {

        if (spinItems != null) spinItems.clear();

        for (int i = 0; i < 114; i++)
        {
            spinItems.add(i,new SpinAdapter.SpinItems(i, surahs[i]));
        }
    }
}