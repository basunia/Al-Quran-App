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
            entryDatatoTableSura(1, 1, "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ", 1, "In the name of Allah, the Beneficent, the Merciful.", "শুরু করছি আল্লাহর নামে যিনি পরম করুণাময়, অতি দয়ালু।");
            entryDatatoTableSura(2, 1, "الْحَمْدُ لِلَّهِ رَبِّ الْعَالَمِينَِ", 2, "All praise is due to Allah, the Lord of the Worlds.", "যাবতীয় প্রশংসা আল্লাহ তা’আলার যিনি সকল সৃষ্টি জগতের পালনকর্তা।");
            entryDatatoTableSura(3, 1, "الرَّحْمَٰنِ الرَّحِيمَِِ", 3, "The Beneficent, the Merciful.", "যিনি নিতান্ত মেহেরবান ও দয়ালু।");
            entryDatatoTableSura(4, 1, "مَالِكِ يَوْمِ الدِّينِِ", 4, "Master of the Day of Judgment.", "যিনি বিচার দিনের মালিক।");
            entryDatatoTableSura(5, 1, "إِيَّاكَ نَعْبُدُ وَإِيَّاكَ نَسْتَعِينُِ", 5, "Thee do we serve and Thee do we beseech for help.", "আমরা একমাত্র তোমারই ইবাদত করি এবং শুধুমাত্র তোমারই সাহায্য প্রার্থনা করি।");
            entryDatatoTableSura(6, 1, "اهْدِنَا الصِّرَاطَ الْمُسْتَقِيمَِ", 6, "Keep us on the right path.", "আমাদেরকে সরল পথ দেখাও,");
            entryDatatoTableSura(7, 1, "صِرَاطَ الَّذِينَ أَنْعَمْتَ عَلَيْهِمْ غَيْرِ الْمَغْضُوبِ عَلَيْهِمْ وَلَا الضَّالِّينَ", 7, "The path of those upon whom Thou hast bestowed favors. Not (the path) of those upon whom Thy wrath is brought down, nor of those who go astray.", "সে সমস্ত লোকের পথ, যাদেরকে তুমি নেয়ামত দান করেছ। তাদের পথ নয়, যাদের প্রতি তোমার গজব নাযিল হয়েছে এবং যারা পথভ্রষ্ট হয়েছে।");

            entryDatatoTableSura(8, 2, "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيم", 1, "In the name of Allah, the Beneficent, the Merciful.", "শুরু করছি আল্লাহর নামে যিনি পরম করুণাময়, অতি দয়ালু।");
            entryDatatoTableSura(9, 2, "قُلْ هُوَ اللَّهُ أَحَدٌ", 2, "Say: He, Allah, is One.", "বলুন, তিনি আল্লাহ, এক,");
            entryDatatoTableSura(10, 2, "اللَّهُ الصَّمَدُ", 3, "Allah is He on Whom all depend.", "আল্লাহ অমুখাপেক্ষী,");
            entryDatatoTableSura(11, 2, "لَمْ يَلِدْ وَلَمْ يُولَدْ", 4, "He begets not, nor is He begotten.", "তিনি কাউকে জন্ম দেননি এবং কেউ তাকে জন্ম দেয়নি");
            entryDatatoTableSura(12, 2, "وَلَمْ يَكُن لَّهُ كُفُوًا أَحَدٌ", 5, "And none is like Him.", "এবং তার সমতুল্য কেউ নেই।");

            entryDatatoTableSura(13, 3, "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ", 1, "In the name of Allah, the Beneficent, the Merciful.", "শুরু করছি আল্লাহর নামে যিনি পরম করুণাময়, অতি দয়ালু।");
            entryDatatoTableSura(14, 3, "قُلْ أَعُوذُ بِرَبِّ النَّاسِ", 2, "Say: I seek refuge in the Lord of men,", "বলুন, আমি আশ্রয় গ্রহণ করিতেছি মানুষের পালনকর্তার,");
            entryDatatoTableSura(15, 3, "مَلِكِ النَّاسِ", 3, "The King of men,", "মানুষের অধিপতির,");
            entryDatatoTableSura(16, 3, "إِلَٰهِ النَّاسِ", 4, "The god of men,", "মানুষের মা’বুদের");
            entryDatatoTableSura(17, 3, "مِن شَرِّ الْوَسْوَاسِ الْخَنَّاسِ", 5, "From the evil of the whisperings of the slinking (Shaitan),", "তার অনিষ্ট থেকে, যে কুমন্ত্রণা দেয় ও আত্নগোপন করে,");
            entryDatatoTableSura(18, 3, "الَّذِي يُوَسْوِسُ فِي صُدُورِ النَّاسِ", 6, "Who whispers into the hearts of men,", "যে কুমন্ত্রণা দেয় মানুষের অন্তরে");
            entryDatatoTableSura(19, 3, "مِنَ الْجِنَّةِ وَالنَّاسِ", 7, "From among the jinn and the men.", "জ্বিনের মধ্য থেকে অথবা মানুষের মধ্য থেকে।");

            entryDatatoTableSura(20, 4, "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيم", 1, "In the name of Allah, the Beneficent, the Merciful.", "শুরু করছি আল্লাহর নামে যিনি পরম করুণাময়, অতি দয়ালু।");
            entryDatatoTableSura(21, 4, "قُلْ أَعُوذُ بِرَبِّ الْفَلَقِ", 2, "Say: I seek refuge in the Lord of the dawn,", "বলুন, আমি আশ্রয় গ্রহণ করছি প্রভাতের পালনকর্তার,");
            entryDatatoTableSura(22, 4, "مِن شَرِّ مَا خَلَقَ", 3, "From the evil of what He has created,", "তিনি যা সৃষ্টি করেছেন, তার অনিষ্ট থেকে,");
            entryDatatoTableSura(23, 4, "وَمِن شَرِّ غَاسِقٍ إِذَا وَقَبَ", 4, "And from the evil of the utterly dark night when it comes,", "অন্ধকার রাত্রির অনিষ্ট থেকে, যখন তা সমাগত হয়,");
            entryDatatoTableSura(24, 4, "وَمِن شَرِّ النَّفَّاثَاتِ فِي الْعُقَدِ", 5, "And from the evil of those who blow on knots,", "গ্রন্থিতে ফুঁৎকার দিয়ে জাদুকারিনীদের অনিষ্ট থেকে");
            entryDatatoTableSura(25, 4, "وَمِن شَرِّ حَاسِدٍ إِذَا حَسَدَ", 6, "And from the evil of the envious when he envies", "এবং হিংসুকের অনিষ্ট থেকে যখন সে হিংসা করে।");

            entryDatatoTableSura(26, 5, "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيم", 1, "In the name of Allah, the Beneficent, the Merciful.", "শুরু করছি আল্লাহর নামে যিনি পরম করুণাময়, অতি দয়ালু।");
            entryDatatoTableSura(27, 5, "تَبَّتْ يَدَا أَبِي لَهَبٍ وَتَبَّ", 2, "Perish the hands of the Father of Flame! Perish he!", "আবু লাহাবের হস্তদ্বয় ধ্বংস হোক এবং ধ্বংস হোক সে নিজে,");
            entryDatatoTableSura(28, 5, "مَا أَغْنَى عَنْهُ مَالُهُ وَمَا كَسَبَ", 3, "No profit to him from all his wealth, and all his gains!", "কোন কাজে আসেনি তার ধন-সম্পদ ও যা সে উপার্জন করেছে।");
            entryDatatoTableSura(29, 5, "سَيَصْلَى نَارًا ذَاتَ لَهَبٍ", 4, "Burnt soon will he be in a Fire of Blazing Flame!", "সত্বরই সে প্রবেশ করবে লেলিহান অগ্নিতে");
            entryDatatoTableSura(30, 5, "وَامْرَأَتُهُ حَمَّالَةَ الْحَطَبِ", 5, "His wife shall carry the (crackling) wood - As fuel!-", "এবং তার স্ত্রীও-যে ইন্ধন বহন করে,");
            entryDatatoTableSura(31, 5, "فِي جِيدِهَا حَبْلٌ مِّن مَّسَدٍ", 6, "A twisted rope of palm-leaf fibre round her (own) neck!", "তার গলদেশে খর্জুরের রশি নিয়ে।");

            entryDatatoTableSura(32, 6, "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيم", 1, "In the name of Allah, the Beneficent, the Merciful.", "শুরু করছি আল্লাহর নামে যিনি পরম করুণাময়, অতি দয়ালু।");
            entryDatatoTableSura(33, 6, "الْقَارِعَةُ", 2, "The (Day) of Noise and Clamour:", "করাঘাতকারী,");
            entryDatatoTableSura(34, 6, "مَا الْقَارِعَةُ", 3, "What is the (Day) of Noise and Clamour?", "করাঘাতকারী কি?");
            entryDatatoTableSura(35, 6, "وَمَا أَدْرَاكَ مَا الْقَارِعَةُ", 4, "And what will explain to thee what the (Day) of Noise and Clamour is?", "করাঘাতকারী সম্পর্কে আপনি কি জানেন ?");
            entryDatatoTableSura(36, 6, "يَوْمَ يَكُونُ النَّاسُ كَالْفَرَاشِ الْمَبْثُوثِ", 5, "(It is) a Day whereon men will be like moths scattered about,", "যেদিন মানুষ হবে বিক্ষিপ্ত পতংগের মত");
            entryDatatoTableSura(37, 6, "وَتَكُونُ الْجِبَالُ كَالْعِهْنِ الْمَنفُوشِ", 6, "And the mountains will be like carded wool.", "এবং পর্বতমালা হবে ধুনিত রঙ্গীন পশমের মত।");
            entryDatatoTableSura(38, 6, "فَأَمَّا مَن ثَقُلَتْ مَوَازِينُهُ", 7, "Then, he whose balance (of good deeds) will be (found) heavy,", "অতএব যার পাল্লা ভারী হবে");
            entryDatatoTableSura(39, 6, "فَهُوَ فِي عِيشَةٍ رَّاضِيَةٍ", 8, "Will be in a life of good pleasure and satisfaction.", "সে সুখীজীবন যাপন করবে।");
            entryDatatoTableSura(40, 6, "وَأَمَّا مَنْ خَفَّتْ مَوَازِينُهُ", 9, "But he whose balance (of good deeds) will be (found) light,-", "আর যার পাল্লা হালকা হবে,");
            entryDatatoTableSura(41, 6, "فَأُمُّهُ هَاوِيَةٌ", 10, "Will have his home in a (bottomless) Pit.", "তার ঠিকানা হবে হাবিয়া।");
            entryDatatoTableSura(42, 6, "وَمَا أَدْرَاكَ مَا هِيَهْ", 11, "And what will explain to thee what this is?", "আপনি জানেন তা কি?");
            entryDatatoTableSura(43, 6, "نَارٌ حَامِيَةٌ", 12, "(It is) a Fire Blazing fiercely!", "প্রজ্জ্বলিত অগ্নি!");

        }

        private void entryDatatoTableSura(int _id, int suraId, String arabicAyaat, int arabicAyaatId, String translationEn, String translationBn) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(SURA_COLUMN_ID, _id);
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

    public List<SurahDetailModel> getSurahWithBengaliTranslation(int surahId) {
        List<SurahDetailModel> surahAyaatList = new ArrayList<>();
        SQLiteDatabase db = openHelper.getWritableDatabase();
        Cursor cursor = db.query(TABLE_SURA_RECORDS, null, SURA_ID + "=?", new String[]{String.valueOf(surahId)}, null, null, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                surahAyaatList.add(new SurahDetailModel(cursor.getString(cursor.getColumnIndexOrThrow(SURA_ARABIC_AYAAT)),
                        cursor.getString(cursor.getColumnIndexOrThrow(SURA_TRANSLATION_BN))));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return surahAyaatList;
    }

    public List<SurahDetailModel> getSurahWithEnglishTranslation(int surahId) {
        List<SurahDetailModel> surahAyaatList = new ArrayList<>();
        SQLiteDatabase db = openHelper.getWritableDatabase();
        Cursor cursor = db.query(TABLE_SURA_RECORDS, null, SURA_ID + "=?", new String[]{String.valueOf(surahId)}, null, null, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                surahAyaatList.add(new SurahDetailModel(cursor.getString(cursor.getColumnIndexOrThrow(SURA_ARABIC_AYAAT)),
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
        Cursor cursor = db.query(TABLE_SURA_LIST, new String[]{SURAH_NAME}, null, null, null, null, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                surahList.add(new SurahNameModel(cursor.getString(cursor.getColumnIndexOrThrow(SURAH_NAME))));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return surahList;
    }

}
