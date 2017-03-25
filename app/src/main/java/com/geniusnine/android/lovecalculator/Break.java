package com.geniusnine.android.lovecalculator;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class Break extends AppCompatActivity {

    String[] List;

    ViewPager viewPager;
    PagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rose_day_shayari_quotos);


        List = new String[] {"Kadmo Ki Duri Se Dilo Ke Fasle Nahi Badte,\n" +
                "dur Hone Se pyar Ka Ahsas Nahi Marte,\n" +
                "kuch Kadmo Ka Fasla Hi Sahi Hamre Bech,\n" +
                "par Koi Pal Nahi Jab Aapko Hum Yaad Nahi Karte.","Gujre Hue Kal Ki Yaad Aati Hai,\n" +
                "kuch Lamho Se Aankhen Bhar Aati Hai,\n" +
                "woh Subah Rangeen Woh Shaam Nirali Jaati Hai,\n" +
                "jab Aap Jaise pyar Ki Yaad Aati Hai…","Jalte hu Dil ko aoir mat jalana\n" +
                "Roti hui ankho ko aoir mat rulana\n" +
                "Ap ki judai me ham pahale hi Mar chuke h\n" +
                "Mare hue insan ko aoir mat marna","Tujhe Haq Diya Hai Maine Dillagi Ka..\n" +
                "Aye Sanam\n" +
                "Tu Mere Dilse Khel Jab Tak Tera Dil Behal Na Jaye","kash o samjhte es dil ki tadap ko\n" +
                "to u na hamko rusva kiya hot.\n" +
                "Unki ye berukhi manjur ha hame\n" +
                "Bas ek bar hame samjh liya hota","Beeafao ki Mahfil lagegi\n" +
                "Aaj jara wakt par ana\n" +
                "Mehmane khash ho tum","Kuchh Mohadat ka nasha tha hame\n" +
                "par aaj jab dil tuta to\n" +
                "nase se Mohadat ho gai\n" +
                "…","Jindagi Ki Asli Udaan Abhi Baaki Hai\n" +
                "jindagi Ke Kai Imtehan Abhi Baaki Hai\n" +
                "abhi To Naapi Hai Mutthi Bhar Zamin Humne\n" +
                "abhi To Sara Aasman Baaki Hai…","Hai keesmat hamari aashma mein chamkte seetare ki jaisi..\n" +
                "Log apni tammana ke liye hamare tootne ka intezar karte hai..!!!","Safar me koi hamsafar hamko mila hi nahi,\n" +
                "Kisi ne apna hame umr bhar kaha hi nahi,\n" +
                "Tamam umr rahe raat ke kabje mein,\n" +
                "Hamari raat ka suraj kabhi uga hi nahi…..","Wo dard hi kya jo aankhon se beh jaaye,\n" +
                "Wo khushi hi kya jo honthon par reh jaaye,\n" +
                "Kabhi to samjho meri khamoshi ko,\n" +
                "Wo baat hi kya jo lafzo me kiya jaaye….","Kuchh Mohadat ka nasha tha hame\n" +
                "par aaj jab dil tuta to\n" +
                "nase se Mohadat ho gai","Jindagi Ki Asli Udaan Abhi Baaki Hai\n" +
                "jindagi Ke Kai Imtehan Abhi Baaki Hai\n" +
                "abhi To Naapi Hai Mutthi Bhar Zamin Humne\n" +
                "abhi To Sara Aasman Baaki Hai…","Rab mere pyaar tak ye sandesha pahucha de,\n" +
                "Us ke Dil me khayal mera bhi jaga de,\n" +
                "kho gaya hai wo apni hi duniya me,\n" +
                "Usko meri bhi jara si YAAD dila De…","" +
                "Ikatthaa Karke, Woh Mere Katl Ka Samaan,\n" +
                "Bewafa Adaaon Se Teer Chalaate Gaye,\n" +
                "Hamein Aadat Thi Use Muskurata Dekhne Ki,\n" +
                "Isiliye, Hum Uski Har Adaa Par Muskuraate Gaye…","Iss Dard Se Hum Khelna Ab Seekh Gaye,\n" +
                "Bewafaa K Saath Jeena Ab Seekh Gaye,\n" +
                "Kyaa Bataaye Kiss Qadar Dil Toota Hain Humaara,\n" +
                "Maut Sey Pehle Kafan Humm Odhnaa Seekh Gaye!","Safar me koi hamsafar hamko mila hi nahi,\n" +
                "Kisi ne apna hame umr bhar kaha hi nahi,\n" +
                "Tamam umr rahe raat ke kabje mein,\n" +
                "Hamari raat ka suraj kabhi uga hi nahi…..","Tum, Tum Aur Sirf Tum,\n" +
                "Lo Khatam Hui Yeh Dastan Dil Ki..","Tum Rakh Na Sakoge Mera Tohfa\n" +
                "Sambhal Kr…\n" +
                "\n" +
                "Warna Abhi Dil De Deta Apne Sine\n" +
                "Se Nikal Kar..!!!","Tasavur Main Zaroor Aoo,Magar Tairna To Seekho …\n" +
                "Tum Aksar Doob Jatay Ho Mere Ashkoon K Pani Main …","Suno ! Ye Jo Ishq Ha Na…,,\n" +
                "Jaan Le Leta Ha,, Magar Phir Bhi Maut Nahi Ati…!!","Ulfatein, Uljhanein, Ranjishein Aur Thakawat,\n" +
                "Bas Yu’N Hi Ek Din Aur Beet Gaya Zindagi Ka . . .","Wo Log Q Milte Hi Utar Jate Hain Dil Main\n" +
                "Jin Logon Se Qismat K Sitare Nhi Milte…","Tere Ishq Se Mili Hai Mere Wajood Ko Ye Shohrat…!\n" +
                "Mera Jikr Hi Kaha Tha Teri Daastan Se Pehle…","Tum Mohabbat Bhi Maosam Ki Tarha Nibhate Ho,\n" +
                "Kabhi Baraste Ho Kabhi Ek Boond Ko Tarsate Ho."

                ,"Tumhare Sath Dekhi Thi Warna Zindagi Mujhko\n" +
                "Na Tab Mehsoos Hoti Thi Na Ab Mehsoos Hoti Hai…","Ye Tera Khel Na Ban Jaye Haqiqat Ek Din…\n" +
                "Ret Pe Likh K Mera Naam Mitaya Na Karo…","Youn Bhi To Raaz Khul Hi Jaayega Ek Din Humari Mohabbat Ka…!!\n" +
                "Mehfil Mein Jo Hum Ko Chorr Kar Sab Ko Salaam Karte Ho…!!","Breakup are not always\n" +
                "Meant to make up,\n" +
                "Sometimes they happen to\n" +
                "Give you a chance to wake up..!","Gam na kar hum teri rah me nahi ayenge,\n" +
                "Agar aa bhi gye to tujse nazre nhi milayege\n" +
                "Jab hoga tujhe apni galti ka ehsass\n" +
                "Tab tak hum kisi aur ke ho jayenge….","My eyes R hurting coz I can’t C U,\n" +
                "My arms R empty coz I can’t hold U,\n" +
                "My lips R cold coz I can’t kiss U but…\n" +
                "My heart is breaking coz I’m not with U!","Jodkar rishta mohabbat ka kisi se\n" +
                "Use tanha akele choda nahi jata\n" +
                "Kaanch se hote hai yeh dil ke rishte\n" +
                "In dil ke rishto ko yu hi toda nahi jata","Aakhri baar tere pyaar ko sajda kar lu\n" +
                "Lout ke phir teri mehfil me nhi aaunga\n" +
                "Apni barbaad mohabbat ka janaza lekar\n" +
                "Teri duniya se bahut dur chala jaunga.","Mujhe kisinebewafa ka naamdiya,\n" +
                "Sathi nejaisejaharkajaamdiya,\n" +
                "Jo kabhikahakarte the bhula mat dena,\n" +
                "Unhone hi bhul jane ka paigamdiya…","Raaz-e-ulfat chupa ke dekh liya\n" +
                "Dil bahut kuch jalaa ke dekh liya\n" +
                "Woh mere ho ke bhi mere na huye\n" +
                "Un ko apnaa banaa ke dekh liya","Wada hum ne kiya tha nibhane ke liye\n" +
                "Ek dil diya tha ek dil paane ke liye\n" +
                "Unho ne mohabbat sikha di or kaha.\n" +
                "Hmne to mohabbat ki thi tumhe azmane k liye","Halaat keh rahe hai nahi rukega ab woh\n" +
                "Dil keh raha hai thoda intezaar aur kar\n" +
                "Shayad kuch pal ke liye he rutha ho wo\n" +
                "Jab tak na laute tab tak thoda aitbaar kar","Love hurts when u breakup with someone\n" +
                "Hurts even more when some 1 breaks up with u\n" +
                "But love hurts the most when the person,\n" +
                "You love has no idea how you feel.","O my mind,\n" +
                "Time and again\n" +
                "You have broken my heart\n" +
                "With false promises.\n" +
                "You are so cruel!\n" +
                "You are such a liar!\n" +
                "Even though my heart is broken,\n" +
                "It is healing the wounds\n" +
                "You have inflicted on my dedication-life",




                   "Some day you’ll cry for me Like I cried for" +
                        " you Some day you’ll miss me Like I miss " +
                        "you Some day you’ll need me Like I needed" +
                        " you Some day you’ll Love me… " +
                        "But I won’t Love you…\n" +
                        "\n" ,

                "It hurts to say goodbye to someone you love " +
                        "but it’s the best for both of us to move on.\n" +
                        "\n" ,
                "Love only one girl or boy at a time." +
                        " Or break up with the person. DO NOT CHEAT!!!!\n" +
                        "\n",
                "\"Never allow someone to be your priority while " +
                        "allowing yourself to be their option.\"",
                "\"The hottest love has the coldest end.\"",

                "\"Don't cry when the sun is gone because " +
                        "the tears won't let you see the stars.\"",

                "\"No matter how hard your heart is broken," +
                        " the world doesn't stop for your grief.\"",
                "\"Love is unconditional. Relationships are not.\"",
                "\"I never hated a man enough to give his diamonds back.\"",
                "\"Cheating and lying aren't struggles. " +
                        "They're reasons to break up.\""




        };






        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        viewPager = (ViewPager) findViewById(R.id.Pager);
        // Pass results to ViewPagerAdapter Class
        adapter = new ViewPagerAdapter(Break.this,List);
        // Binds the Adapter to the ViewPager
        viewPager.setAdapter(adapter);

        int pos = getIntent().getIntExtra("key", 0);
        viewPager.setCurrentItem(pos);



    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
     /*   getMenuInflater().inflate(R.menu.main, menu);
*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        switch (item.getItemId()) {
            case android.R.id.home:

                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:

                return super.onOptionsItemSelected(item);

        }
    }
}
