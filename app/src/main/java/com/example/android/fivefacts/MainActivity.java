package com.example.android.fivefacts;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends ActionBarActivity {

    int factsCount = 0;
    String[] facts = {"", ""};
    String[] sources = {"", ""};
    String[] backgroundColors = {"", ""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
    chooses the random facts and random backgroun colors,
    removes the 'show me' button
    displays the 'next' button, the fact and its source
     */
    public void showFacts(View view) {

        String[] factList = this.getResources().getStringArray(R.array.factsArray);
        int[] factOrder = chooseRandomFacts(factList.length);
        facts[0] = factList[factOrder[0]];
        facts[1] = factList[factOrder[1]];

        String[] sourceList = this.getResources().getStringArray(R.array.factsSources);
        sources[0] = sourceList[factOrder[0]];
        sources[1] = sourceList[factOrder[1]];

        String[] colorList = this.getResources().getStringArray(R.array.backgroundArray);
        int[] colorOrder = chooseRandomColors(colorList.length);
        backgroundColors[0] = colorList[colorOrder[0]];
        backgroundColors[1] = colorList[colorOrder[1]];

        Button showme_button = (Button) findViewById(R.id.showme_button);
        showme_button.setVisibility(View.GONE);

        RelativeLayout fivefacts_layout = (RelativeLayout) findViewById(R.id.fivefacts_layout);
        fivefacts_layout.setVisibility(View.VISIBLE);

        // showFacts method is also used after displaying five facts if the user wishes to see more
        // so the view with Thank you and Show more buttons must disappear
        RelativeLayout finished_facts = (RelativeLayout) findViewById(R.id.finished_facts);
        finished_facts.setVisibility(View.GONE);

        showNext(view);
    }

    /*
    if number of facts already showed is less than 3, shows next fact;
    else makes fivefacts_layout disappear and finished_facts with additional buttons appear
     */
    public void showNext(View view) {

        if (factsCount < 2) {

            // sets the text of the facz
            TextView factTextView = (TextView) findViewById(R.id.fact_textview);
            factTextView.setText(facts[factsCount]);

            // sets the link to the source
            TextView sourceTextView = (TextView) findViewById(R.id.source_textview);
            sourceTextView.setMovementMethod(LinkMovementMethod.getInstance());
            String clickText = "<a href='" + sources[factsCount] + "'> Read more </a>";
            sourceTextView.setText(Html.fromHtml(clickText));

            // sets the background color
            View fivefactsLayout = findViewById(R.id.fivefacts_layout);
            fivefactsLayout.setBackgroundColor(Color.parseColor(backgroundColors[factsCount]));

            factsCount++;

        } else {

            RelativeLayout fivefacts_layout = (RelativeLayout) findViewById(R.id.fivefacts_layout);
            fivefacts_layout.setVisibility(View.GONE);

            RelativeLayout finished_facts = (RelativeLayout) findViewById(R.id.finished_facts);
            finished_facts.setVisibility(View.VISIBLE);

            factsCount = 0;
        }

    }

    /*
    removes the view with the fact, its source and the 'next' button,
    displays the 'thank you' and the 'show more' buttons
     */
    public void closeAll(View view) {

        Button showme_button = (Button) findViewById(R.id.showme_button);
        showme_button.setVisibility(View.VISIBLE);

        RelativeLayout fivefacts_layout = (RelativeLayout) findViewById(R.id.fivefacts_layout);
        fivefacts_layout.setVisibility(View.GONE);

        RelativeLayout finished_facts = (RelativeLayout) findViewById(R.id.finished_facts);
        finished_facts.setVisibility(View.GONE);
    }

    // Creates a set of random numbers to choose random facts
    private int[] chooseRandomFacts(int length) {
        int[] numbers = {-1, -1, -1};
        for(int i=0; i<3; i++){
            int addNum = new Random().nextInt(length);
            numbers[i] = addNum;
        }
        return numbers;
    }

    // Creates a set of numbers to choose random colors
    private int[] chooseRandomColors(int length) {
        int[] numbers = {-1, -1};
        for(int i=0; i<2; i++) {
            int addNum = new Random().nextInt(length);
            numbers[i] = addNum;
        }
        return numbers;
    }
}

















