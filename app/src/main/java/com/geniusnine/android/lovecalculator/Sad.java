package com.geniusnine.android.lovecalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class Sad extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propose_day_status);

        MobileAds.initialize(Sad.this, getString(R.string.ads_app_id));
        AdView mAdView = (AdView)this.findViewById(R.id.adViewPropose);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        listView=(ListView)findViewById(R.id.listViewRoseDay);

        String[] values = new String[] {"किसी के दिल पे क्या गुजरी हे वो अनजान क्या जाने,\n" +
                "प्यार किसको कहते हे वो नादान क्या जाने,\n" +
                "हवा के साथ उठा ले गया घर का परिंदा,\n" +
                "केसे बना था ये घोसला वो तूफान क्या जाने.",
                "कितनी अजीब बात है ना जब तू मेरे पास थी तो,\n" +
                        "हर दम ये सोचता था की क्या में तेरी कदर नहीं करता\n" +
                        "और आज तू मेरे पास नहीं है तो है तो ये एहसास होता है की,\n" +
                        "कदर तो हमेशा से थी मगर तुजे न खोने के यकीन ने अँधा कर दिया था.",
                "दुश्मन भी मेरे मुरीद हैं शायद\n" +
                        "वक़्त बेवक्त मेरा नाम लिया करते हैं\n" +
                        "मेरी गली से गुज़रते हैं छुपा के खंजर\n" +
                        "रु-ब-रु होने पर सलाम किया करते हैं.","एक पल भी सोती नहीं है आँखे, चले आईये\n" +
                "आँसुओं के संग गुजरती है राते, चले आईये\n" +
                "इन्तजार के मोती रोज लुटाती है आँखे\n" +
                "बड़ा सताती है तुम्हारी बाते, चले आईये.","भीड़ भाड़ को छोड़ आए हैं बस तन्हाई भाई है.\n" +
                "वहां बहुत बेचैनी भोगी यहां खुमारी छाई है.\n" +
                "वो सवाल अब यहां नहीं हैं जिनके उत्तर मुश्किल थे.\n" +
                "जितनी हमने इच्छा की थी उतनी राहत पाई है.","दिल जब टूटता है तो आवाज नहीं आती!\n" +
                "हर किसी को मुहब्बत रास नहीं आती!\n" +
                "ये तो अपने-अपने नसीब की बात है!\n" +
                "कोई भूलता नहीं और किसी को याद भी नहीं आती!","जख्म तुम देते रहे, हम दवा करते रहे\n" +
                "आह हम भरते रहे, तुम सितम करते रहे\n" +
                "मेरा दावा है की, दीवाना रहेगा बन के\n" +
                "उसकी आँखों से अगर आँख मिला दे कोई.","ठोकर ना लगा मुझे पत्थर नही हूँ मैं,\n" +
                "हैरत से ना देख कोई मंज़र नही हूँ मैं,\n" +
                "उनकी नज़र में मेरी कदर कुछ भी नही,\n" +
                "मगर उनसे पूछो जिन्हें हासिल नही हूँ मैं.","मज़बूरी में जब कोई जुदा होता है,\n" +
                "ज़रूरी नहीं कि वो बेवफ़ा होता है,\n" +
                "देकर वो आपकी आँखों में आँसू,\n" +
                "अकेले में वो आपसे ज्यादा रोता है.","एक अजीब सा मंजर नज़र आता है,\n" +
                "हर एक आँसूं समंदर नज़र आता हैं,\n" +
                "कहाँ रखूं मैं शीशे सा दिल अपना,\n" +
                "हर किसी के हाथ मैं पत्थर नज़र आता हैं.","मुझे को अब तुझ से भी मोहब्बत नहीं रही,\n" +
                "आई ज़िंदगी तेरी भी मुझे ज़रूरत नहीं रही,\n" +
                "बुझ गये अब उस के इंतेज़ार के वो जलते दिए,\n" +
                "कहीं भी आस-पास उस की आहट नहीं रही.","तेरी आरज़ू मेरा ख्वाब है,\n" +
                "जिसका रास्ता बहुत खराब है,\n" +
                "मेरे ज़ख्म का अंदाज़ा न लगा,\n" +
                "दिल का हर पन्ना दर्द की किताब है.","छोड़ दिया यारो किस्मत की\n" +
                "लकीरों पर यकीन करना,\n" +
                "जब लोग बदल सकते हैं\n" +
                "तो किस्मत क्या चीज.",
                "\" दिल मे संजो लूँ...या कही यू ही रख छोड़ु..\n" +
                        "\n" +
                        "वो महकता तोहफ़ा तेरी यादों का...? \"","पता नही होश मे हूँ.....\n" +
                "या बेहोश हूँ मैं.....\n" +
                "पर बहूत सोच .......\n" +
                "समझकर खामोश हूँ मैं.\n",


                " \n" +
                        "दोस्तों मत लगाना बोली हमारे अल्फाजो की,\n" +
                        "हमने लिखना शुरू किया तो तुम नीलाम हो जाओगे.\n",
                "जुबां तीखी हो तो खंजर से गहरा जख्म देती है,\n" +
                        "और मीठी हो तो वैसे ही कत्ल कर देती है.\n"
                ,
                "नहीं मिला कोई तुम जैसा आज तक,\n" +
                        "पर ये सितम अलग है की मिले तुम भी नही.\n",
                "ये भी एक तमाशा है, इश्क और मोहब्बत में\n" +
                        "दिल किसी का होता है और बस किसी का चलता है.\n",
                "मैं उस किताब का आख़िरी पन्ना था,\n" +
                        "मैं ना होता तो कहानी ख़त्म न होती.\n",
                "एक हसरत थी की कभी वो भी हमे मनाये,\n" +
                        "पर ये कम्ब्खत दिल कभी उनसे रूठा ही नही.\n",
                "हर रोज़ खा जाते थे वो कसम मेरे नाम की,\n" +
                        "आज पता चला की जिंदगी धीरे धीरे ख़त्म क्यूँ हो रही है.\n",
                "बेवफा कहने से पहले मेरी रग रग का खून निचोड़ लेना,\n" +
                        "कतरे कतरे से वफ़ा ना मिले तो बेशक मुझे छोड़ देना.\n",





                "ख्वाब बोये थे और अकेलापन काटा है ," +
                        " इस मोहब्बत में यारो बहुत घाटा है",
                "बड़ी मुश्किलों से करता हूँ तेरे यादों का कारोबार ," +
                        " मुनाफा कम है पर गुज़ारा हो ही जाता है।",
                "रात की तन्हाई में तो हर कोई याद कर लेता है ," +
                        " सुबह उठते ही जो याद आए मोहब्बत उसे कहते हैं",
                "टूटे तो बहुत चुभते है……. काँच और रिश्ते",

                "एक कोशिश है कि कोई दिल के ज़ख्म ना देख ले ," +
                        " एक ख्वाइश है कि काश ! कोई देखने वाला होता",
                "दिल से ज्यादा महफूज कोई जगह नहीं दुनिया में …." +
                        " मगर सबसे ज्यादा लोग लापता यहीं से होते हैं",
                "मुझे पता है लोग बदल जाते हैं , " +
                        "पर मैंने तुम्हें उन लोगों में गिना ही नहीं था",
                "मैसेज हम ज़्यादा और वो कम करते हैं, फिर भी वो कहते हैं कि प्यार " +
                        " वो ज़्यादा, हम कम करते हैं!!!",
                "नफरत हो जाएगी तुझे तुझसे ही …. अगर मैं तेरे ही अंदाज़ में बात करूँ",

                "बढ़ती गयी उनकी मनमर्जियाँ , फिर इक दिन" +
                        " ये हुआ की हम फिर से अजनबी बन गए",
                "काश आज मेरी साँस रुक जाए , " +
                        "सुना है की साँस रुक जाए तो रूठे हुए भी देखने आते है",

                "मेरी बेपनाह मोहब्बत तुम्हे उस वक़्त याद आएगी जब " +
                        "तुम्हें हँसाने वाले कम और रुलाने वाले ज्यादा होंगे…",
                "तुझसे दूर जाने का कोई इरादा ना था पर" +
                        " रुकते भी कैसे…. जब तुम ही हमारे नहीं थे।",
                "क्यों दुनिया वाले मोहब्बत को खुदा का दर्जा देते हैं ," +
                        " मैंने तो आज तक नहीं सुना खुदा ने बेवफाई की हो","तुम हो तो बसंत है, तुम नहीं तो बस अंत है",
                "तलाश सिर्फ सुकून कि होती हैं नाम रिश्ते का चाहे जो भी हो",
                "कमी तेरे नसीबों में रही होगी, कि तू मेरी ना हुई," +
                        " मैने तो कोशिश बहुत की,तुझे अपना बनाने की…",
                "बेवजह बिछड़ तो गए हो तुम बस इतना" +
                        " बता दो कि सकून मिला या नहीं।",
                "याद उन्ही की आती है, जिनसे दिल के तालुक हो , " +
                        "हर किसी से मोहब्बत हो ऐसा तो मुमकिन नहीं",
                "मुद्दत बाद जब उसने मेरी खामोश आँखें देखी तो " +
                        "ये कहकर फिर रुला गया कि लगता है अब सम्भल गए हो"











        };

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.activity_propose_day_status,R.id.textViewRose, values);




        // Assign adapter to ListView
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(Sad.this,SadShayari.class);
                i.putExtra("key", position);
                startActivity(i);


            }
        });

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);

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