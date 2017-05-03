package com.lsinf1225.groupe_s.ezmeal;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandredewit on 24/04/17.
 */

public class Tab3 extends Fragment {
    Spinner spinnerType;
    Spinner spinnerSubType;
    Spinner spinnerPerson;
    Spinner spinnerDifficulty;
    Spinner spinnerCooking;
    Spinner spinnerPreparation;
    Spinner spinnerChooseUnit;

    View view;
    LinearLayout linearLayoutIngredient;
    LinearLayout linearLayoutStep;

    int counterIngredient;
    int counterStep;

    ArrayAdapter<CharSequence> adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        counterIngredient = 1;
        counterStep = 1;
        view = inflater.inflate(R.layout.tab3, container, false);

        spinnerType = (Spinner) view.findViewById(R.id.spinnerType);
        adapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.choose_Type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapter);

        spinnerSubType = (Spinner) view.findViewById(R.id.spinnerSubType);
        adapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.choose_SubType, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSubType.setAdapter(adapter);

        spinnerPerson = (Spinner) view.findViewById(R.id.spinnerNbPerson);
        adapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.choose_NbrPerson, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPerson.setAdapter(adapter);

        spinnerDifficulty = (Spinner) view.findViewById(R.id.spinnerDifficulty);
        adapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.choose_Difficulty, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDifficulty.setAdapter(adapter);

        spinnerChooseUnit = (Spinner) view.findViewById(R.id.addIngredientUnit1);
        adapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.choose_unit, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerChooseUnit.setAdapter(adapter);

        linearLayoutIngredient = (LinearLayout) view.findViewById(R.id.linearLayoutIngredient);
        linearLayoutStep = (LinearLayout) view.findViewById(R.id.addStepLinearLayout);

        // cooking & preparation
        List<Integer> spinnerArray = new ArrayList<>();
        for (int i = 1; i <= 36; i++) {
            spinnerArray.add(i * 5);
        }

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(getActivity(), android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerCooking = (Spinner) view.findViewById(R.id.cooking);
        spinnerCooking.setAdapter(adapter);

        spinnerPreparation = (Spinner) view.findViewById(R.id.preparation);
        spinnerPreparation.setAdapter(adapter);


        Button button = (Button) view.findViewById(R.id.buttonAddIngredient);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counterIngredient++;
                TextView textView;
                final LinearLayout.LayoutParams textViewLayoutParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
                textViewLayoutParam.setMargins(0, 0, 0, 10);

                //Ingredient number...

                textView = setNewTextView("Ingredient " + counterIngredient, textViewLayoutParam, Typeface.BOLD, 16);
                linearLayoutIngredient.addView(textView);

                //Choose & quantity

                final LinearLayout.LayoutParams linearLayoutMargin = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                linearLayoutMargin.setMargins(0, 20, 0, 50);


                LinearLayout layout = new LinearLayout(getActivity());
                layout.setLayoutParams(linearLayoutMargin);

                textView = setNewTextView("Choose", textViewLayoutParam, Typeface.NORMAL, 20);
                layout.addView(textView);


                textView = setNewTextView("Quantity", textViewLayoutParam, Typeface.NORMAL, 20);
                layout.addView(textView);

                linearLayoutIngredient.addView(layout);

                //spinner choose & quantity

                final LinearLayout.LayoutParams spinnerParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                final LinearLayout.LayoutParams linearSpinnerParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 0.5f);

                LinearLayout layout1 = setNewLinearLayout(textViewLayoutParam, Gravity.NO_GRAVITY);
                LinearLayout layoutSpinner1 = setNewLinearLayout(linearSpinnerParams, Gravity.NO_GRAVITY);

                Spinner spinner = new Spinner(getActivity());
                spinnerParams.gravity = Gravity.CENTER;
                spinner.setLayoutParams(spinnerParams);
                spinner.setTag("chooseIngredient" + counterIngredient);

                layoutSpinner1.addView(spinner);

                android.support.design.widget.TextInputLayout inputLayout = new android.support.design.widget.TextInputLayout(getActivity());
                inputLayout.setLayoutParams(linearSpinnerParams);

                final LinearLayout.LayoutParams test = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                EditText editText = setNewEditText(null, test, Gravity.NO_GRAVITY, "quantity" + counterIngredient);
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);

                inputLayout.addView(editText);


                layout1.addView(layoutSpinner1);
                layout1.addView(inputLayout);

                linearLayoutIngredient.addView(layout1);


                // Text View ingredient title

                final LinearLayout.LayoutParams textViewTitleParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                textViewTitleParams.setMargins(0, 30, 0, 10);
                textView = setNewTextView("Not in the list ? Add Yours", textViewTitleParams, Typeface.ITALIC, 14);

                linearLayoutIngredient.addView(textView);

                //Input text Name

                inputLayout = new android.support.design.widget.TextInputLayout(getActivity());
                textViewLayoutParam.setMargins(0, 0, 0, 0);
                inputLayout.setLayoutParams(textViewLayoutParam);

                textViewLayoutParam.setMargins(0, 30, 0, 0);
                editText = setNewEditText("Name", textViewLayoutParam, Gravity.NO_GRAVITY, "nameIngredient" + counterIngredient);

                inputLayout.addView(editText);

                linearLayoutIngredient.addView(inputLayout);

                //Unit & quantity

                linearLayoutMargin.setMargins(0, 20, 0, 50);

                layout = new LinearLayout(getActivity());
                layout.setLayoutParams(linearLayoutMargin);

                textView = setNewTextView("Unit", textViewLayoutParam, Typeface.NORMAL, 20);
                layout.addView(textView);


                textView = setNewTextView("Quantity", textViewLayoutParam, Typeface.NORMAL, 20);
                layout.addView(textView);

                linearLayoutIngredient.addView(layout);

                //spinner unit & quantity

                layout1 = setNewLinearLayout(textViewLayoutParam, Gravity.CENTER);
                layoutSpinner1 = setNewLinearLayout(linearSpinnerParams, Gravity.NO_GRAVITY);

                spinner = new Spinner(getActivity());
                spinner.setLayoutParams(spinnerParams);
                spinner.setTag("addIngredientUnit" + counterIngredient);

                ArrayAdapter<CharSequence> adapter;

                adapter = ArrayAdapter.createFromResource(getActivity(), R.array.choose_unit, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                layoutSpinner1.addView(spinner);

                inputLayout = new android.support.design.widget.TextInputLayout(getActivity());
                inputLayout.setLayoutParams(linearSpinnerParams);

                editText = setNewEditText(null, test, Gravity.NO_GRAVITY, "addIngredientQuantity" + counterIngredient);
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);

                inputLayout.addView(editText);

                layout1.addView(layoutSpinner1);
                layout1.addView(inputLayout);

                linearLayoutIngredient.addView(layout1);

            }
        });

        Button buttonAddStep = (Button) view.findViewById(R.id.addStep);
        buttonAddStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counterStep++;
                TextView textView;
                EditText editText;

                final LinearLayout.LayoutParams textViewLayoutParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
                textViewLayoutParam.setMargins(0, 0, 0, 10);

                //Step number...

                textView = setNewTextView("Step " + counterStep, textViewLayoutParam, Typeface.BOLD, 16);
                linearLayoutStep.addView(textView);

                //Text Area
                final LinearLayout.LayoutParams textAreaLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                textAreaLayout.setMargins(0, 10, 0, 0);
                android.support.design.widget.TextInputLayout textInputLayout = new android.support.design.widget.TextInputLayout(getActivity());

                textInputLayout.setLayoutParams(textAreaLayout);
                textAreaLayout.setMargins(0, 0, 0, 10);

                editText = setNewEditText("Description", textAreaLayout, Gravity.START, "description" + counterStep);
                editText.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
                editText.setMinLines(5);

                textInputLayout.addView(editText);

                linearLayoutStep.addView(textInputLayout);

            }
        });
        return view;
    }

    private TextView setNewTextView(String text, LinearLayout.LayoutParams params, int typeface, float size) {
        TextView textView = new TextView(this.getActivity());
        textView.setLayoutParams(params);
        textView.setText(text);
        textView.setTextSize(size);
        textView.setTypeface(null, typeface);

        return textView;
    }

    private LinearLayout setNewLinearLayout(LinearLayout.LayoutParams params, int gravity) {
        LinearLayout layout = new LinearLayout(getActivity());
        layout.setLayoutParams(params);
        layout.setGravity(gravity);

        return layout;
    }

    private EditText setNewEditText(String text, LinearLayout.LayoutParams params, int gravity, String id) {
        EditText editText = new EditText(getActivity());
        editText.setLayoutParams(params);
        editText.setGravity(gravity);
        editText.setTag(id);
        editText.setHint(text);

        return editText;

    }

}
