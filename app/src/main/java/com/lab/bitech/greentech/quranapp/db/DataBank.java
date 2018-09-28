package com.lab.bitech.greentech.quranapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.lab.bitech.greentech.quranapp.models.SurahDetailModel;
import com.lab.bitech.greentech.quranapp.models.SurahNameModel;

import java.util.ArrayList;
import java.util.List;

public class DataBank {

    // list of constants for referencing the db's internal values
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "al_quran_db";

    /*table sura name*/
    private static final String TABLE_SURA_LIST = "surah_list";
    /*columns*/
    private static final String SURA_LIST_COLUMN_ID = "sura_id";
    private static final String SURAH_NAME = "sura_name";

    /*table*/
    private static final String TABLE_SURA_RECORDS = "surah_details";
    /*columns*/
    private static final String SURA_COLUMN_ID = "_id";
    private static final String SURA_NAME = "suara_name";
    private static final String SURA_ID = "suara_id";
    private static final String SURA_ARABIC_AYAAT = "arabic_ayat";
    private static final String SURA_ARABIC_AYAAT_ID = "arabic_ayaat_id";
    private static final String SURA_TRANSLATION_EN = "translation_en";
    private static final String SURA_TRANSLATION_BN = "translation_bn";

    // stored local variables for the OpenHelper and the database it opens
    private DatabaseOpenHelper openHelper;

    public DataBank(Context context) {
        openHelper = new DatabaseOpenHelper(context);
    }

    public class DatabaseOpenHelper extends SQLiteOpenHelper {

        private SQLiteDatabase database;

        DatabaseOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        // when the db is created. this method is called by onUpgrade when the
        // version number is changed
        @Override
        public void onCreate(SQLiteDatabase database) {
            this.database = database;

            // execSQL actually creates the schema of the db we defined in the
            // method above

            database.execSQL("CREATE TABLE " + TABLE_SURA_LIST + "( "
                    + SURA_LIST_COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL, "
                    + SURAH_NAME + " TEXT )");

            database.execSQL("CREATE TABLE " + TABLE_SURA_RECORDS + "( "
                    + SURA_COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL, "
                    + SURA_NAME + " TEXT, "
                    + SURA_ID + " INTEGER, "
                    + SURA_ARABIC_AYAAT + " TEXT, "
                    + SURA_ARABIC_AYAAT_ID + " INTEGER, "
                    + SURA_TRANSLATION_EN + " TEXT, "
                    + SURA_TRANSLATION_BN + " TEXT )");



            dataForSuraList();
            dataForSuraDetailRecords();
            Log.d("mahmud", "onCreate");
        }

        // when the version number is changed, this method is called
        @Override
        public void onUpgrade(SQLiteDatabase database, int oldVersion,
                              int newVersion) {
            // drops table if exists, and then calls onCreate which implements
            // our new schema
            database.execSQL("DROP TABLE IF EXISTS " + TABLE_SURA_LIST);
            database.execSQL("DROP TABLE IF EXISTS " + TABLE_SURA_RECORDS);

            onCreate(database);
        }

        private void dataForSuraList() {
            entryDatatoTableSuraList(1, "AL FATIHA");
            entryDatatoTableSuraList(2, "AL IKHLAS");
            entryDatatoTableSuraList(3, "AN NAS");
            entryDatatoTableSuraList(4, "FALAK");
            entryDatatoTableSuraList(5, "LAHAB");
            entryDatatoTableSuraList(6, "QARIAH");
            entryDatatoTableSuraList(7, "KAUSAR");
            entryDatatoTableSuraList(8, "AL ASR");
            entryDatatoTableSuraList(9, "MAUN");
            entryDatatoTableSuraList(10, "QURAISH");

        }

        private void entryDatatoTableSuraList(int _id, String suraName) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(SURA_LIST_COLUMN_ID, _id);
            contentValues.put(SURAH_NAME, suraName);

            Log.d("mahmud", "entryToTableTimeRecords");
            database.insert(TABLE_SURA_LIST, null, contentValues);
            //Log.d("mahmud", "entryToTableTimeRecords");
        }

        private void dataForSuraDetailRecords() {

            //entryToTableTimeRecords(1, "رَبَّنَا تَقَبَّلْ مِنَّا إِنَّكَ أَنْتَ السَّمِيعُ العَلِيمُ", "Our Lord! Accept (this service) from us: For Thou art the All-Hearing, the All-knowing.", "Rabbana taqabbal minna innaka antas Sameeaul Aleem.", "[البقرة :127]", "");
            entryDatatoTableSura(1, "Al FATIHA", 1, "بِسْمِ اللَّهِ", 1, "In the Name of Allah.", "Bismillaahi");
            entryDatatoTableSura(2, "Al FATIHA", 1, "الْحَمْدُ لِلَّهِ رَبِّ الْعَالَمِينَِ", 2, "All praise is due to Allah, the Lord of the Worlds.", "Bismillaahi");
            entryDatatoTableSura(3, "Al FATIHA", 1, "الرَّحْمَٰنِ الرَّحِيمَِِ", 3, "The Beneficent, the Merciful.", "Bismillaahi");
            entryDatatoTableSura(4, "Al FATIHA", 1, "مَالِكِ يَوْمِ الدِّينِِ", 4, "Master of the Day of Judgment.", "Bismillaahi");
            entryDatatoTableSura(5, "Al FATIHA", 1, "إِيَّاكَ نَعْبُدُ وَإِيَّاكَ نَسْتَعِينُِ", 5, "Thee do we serve and Thee do we beseech for help.", "Bismillaahi");
            entryDatatoTableSura(6, "Al FATIHA", 1, "اهْدِنَا الصِّرَاطَ الْمُسْتَقِيمَِ", 6, "Keep us on the right path.", "Bismillaahi");
            entryDatatoTableSura(7, "Al FATIHA", 1, "صِرَاطَ الَّذِينَ أَنْعَمْتَ عَلَيْهِمْ غَيْرِ الْمَغْضُوبِ عَلَيْهِمْ وَلَا الضَّالِّينَ", 7, "The path of those upon whom Thou hast bestowed favors. Not (the path) of those upon whom Thy wrath is brought down, nor of those who go astray.", "Bismillaahi");

            entryDatatoTableSura(8, "Al IKHLAS", 2, "بِسْمِ اللَّهِ", 2, "In the Name of Allah.", "Bismillaahi");
            entryDatatoTableSura(9, "Al IKHLAS", 2, "بِسْمِ اللَّهِ", 2, "In the Name of Allah.", "Bismillaahi");
        }

        private void entryDatatoTableSura(int _id, String suraName, int suraId, String arabicAyaat, int arabicAyaatId, String translationEn, String translationBn) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(SURA_COLUMN_ID, _id);
            contentValues.put(SURA_NAME, suraName);
            contentValues.put(SURA_ID, suraId);
            contentValues.put(SURA_ARABIC_AYAAT, arabicAyaat);
            contentValues.put(SURA_ARABIC_AYAAT_ID, arabicAyaatId);
            contentValues.put(SURA_TRANSLATION_EN, translationEn);
            contentValues.put(SURA_TRANSLATION_BN, translationBn);

            Log.d("mahmud", "entryToTableTimeRecords");
            database.insert(TABLE_SURA_RECORDS, null, contentValues);
            //Log.d("mahmud", "entryToTableTimeRecords");
        }

    }

    public SurahNameModel getSurah(int surahId) {
        SQLiteDatabase db = openHelper.getWritableDatabase();
        Cursor cursor = db.query(TABLE_SURA_LIST, null, SURA_LIST_COLUMN_ID + "=?", new String[]{String.valueOf(surahId)}, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        if (cursor != null) {
            // prepare time object
            SurahNameModel surah = new SurahNameModel(
                    cursor.getString(cursor.getColumnIndex(SURAH_NAME)));

            // close the db connection
            cursor.close();
            db.close();

            return surah;
        }

        return null;

    }


    /*public SurahDetailModel getSurah(int surahId) {
        SQLiteDatabase db = openHelper.getWritableDatabase();
        Cursor cursor = db.query(TABLE_DUA_RECORDS, null, DUA_RECORDS_COLUMN_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        if (cursor != null) {
            // prepare time object
            SurahDetailModel duaDetail = new SurahDetailModel(
                    cursor.getString(cursor.getColumnIndexOrThrow(DUA_RECORDS_DUA_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DUA_RECORDS_ARABIC)),
                    cursor.getString(cursor.getColumnIndex(DUA_RECORDS_TRANSLATION)));

            // close the db connection
            cursor.close();
            db.close();

            return duaDetail;
        }

        return null;

    }*/

    public List<SurahDetailModel> getSurahWithEnglishTranslation(int surahId) {
        List<SurahDetailModel> surahAyaatList = new ArrayList<>();
        SQLiteDatabase db = openHelper.getWritableDatabase();
        Cursor cursor = db.query(TABLE_SURA_RECORDS, null, SURA_ID + "=?", new String[]{String.valueOf(surahId)}, null, null, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                surahAyaatList.add( new SurahDetailModel(cursor.getString(cursor.getColumnIndexOrThrow(SURA_ARABIC_AYAAT)),
                        cursor.getString(cursor.getColumnIndexOrThrow(SURA_TRANSLATION_EN))));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return surahAyaatList;
    }

    public List<SurahNameModel> getSurahList() {
        List<SurahNameModel> surahList = new ArrayList<>();

        SQLiteDatabase db = openHelper.getWritableDatabase();
        Cursor cursor = db.query(TABLE_SURA_LIST, new String[] {SURAH_NAME}, null, null, null, null, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                surahList.add( new SurahNameModel(cursor.getString(cursor.getColumnIndexOrThrow(SURAH_NAME))));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return surahList;
    }

}
