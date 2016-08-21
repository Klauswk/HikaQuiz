package com.hika.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.hika.R;

/**
 * @author <a href="https://github.com/Klauswk">Klaus Klein</a>
 * @version 1.0
 * @since 1.0
 */

public class KanjiChooserDialog extends Dialog {

    private TextView[] grades;
    private QuestionnarieCall callQuestionnaire;

    public KanjiChooserDialog(Context context , QuestionnarieCall questionnarieCall) {
        super(context);
        this.callQuestionnaire = questionnarieCall;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_kanjischose);

        grades = new TextView[7];
        grades[0] = (TextView) findViewById(R.id.tvGrade1);
        grades[1] = (TextView) findViewById(R.id.tvGrade2);
        grades[2] = (TextView) findViewById(R.id.tvGrade3);
        grades[3] = (TextView) findViewById(R.id.tvGrade4);
        grades[4] = (TextView) findViewById(R.id.tvGrade5);
        grades[5] = (TextView) findViewById(R.id.tvGrade6);
        grades[6] = (TextView) findViewById(R.id.tvGrade7);

        grades[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callQuestionnaire.callQuestionnaire(1);
            }
        });

        grades[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callQuestionnaire.callQuestionnaire(2);
            }
        });

        grades[2].setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                callQuestionnaire.callQuestionnaire(3);
            }
        });

        grades[3].setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                callQuestionnaire. callQuestionnaire(4);
            }
        });

        grades[4].setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                callQuestionnaire.callQuestionnaire(5);
            }
        });

        grades[5].setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                callQuestionnaire.callQuestionnaire(6);
            }
        });

        grades[6].setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                callQuestionnaire.callQuestionnaire(7);
            }
        });
    }
}
