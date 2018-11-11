package com.example.boris.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    final String log = "log";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String tmp = "";

        try {
            //Готовим документ
            XmlPullParser xpp = prepareXpp();

            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                switch (xpp.getEventType()) {
                    //Начало документа
                    case XmlPullParser.START_DOCUMENT:
                        Log.d(log, "Начало документа - START_DOCUMENT");
                        break;
                    //Начало тэга
                    case XmlPullParser.START_TAG:
                        Log.d(log, "Начало тэга: " + xpp.getName());
                        tmp = "";
                        for (int i = 0; i < xpp.getAttributeCount(); i++) {
                            tmp = tmp + xpp.getAttributeName(i) + " = "
                                    + xpp.getAttributeValue(i) + ", ";
                            Log.d(log, "TMP = " + tmp);
                        }
                        if (!TextUtils.isEmpty(tmp))
                            Log.d(log, "Attributes: " + tmp);
                        break;
                    //Конец тэга
                    case XmlPullParser.END_TAG:
                        Log.d(log, "Конец тэга: name = "+ xpp.getName());
                        break;
                    //Содержимое тэга
                    case XmlPullParser.TEXT:
                        Log.d(log,"TEXT = " + xpp.getText());
                        break;

                        default:
                            break;
                }
                //Следующий элемент
                xpp.next();
                Log.d(log, "Inside xpp.next()");
            }
            Log.d(log, "END DOCUMENT");
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private XmlPullParser prepareXpp() {
        return getResources().getXml(R.xml.data);
    }
}
