package com.example.finalproject.ui.Financials;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.finalproject.R;
import com.example.finalproject.databinding.FragmentFinancialsBinding;

import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;

public class FinancialsFragment extends Fragment {

    private FinancialsViewModel financialsViewModel;
    private FragmentFinancialsBinding binding;

    private final ArrayList<String> FINANCIALS_SELECTORS = new ArrayList<String>(){
        {
            add("Income");
            add("Expense");
        }
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        financialsViewModel =
                new ViewModelProvider(this).get(FinancialsViewModel.class);

        binding = FragmentFinancialsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView financeHeader            = binding.financeHeader;
        final Spinner financeSelectionSpinner   = binding.financeSelectionSpinner;
        final EditText financeAmountText        = binding.financeAmountText;
        final EditText financeDateText          = binding.financeDateText;
        final Button financeSubmitButton        = binding.financeSubmitButton;

        // Setup Spinner to contain the different Time Frames for the finances over time graph
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this.getContext(), R.layout.support_simple_spinner_dropdown_item, FINANCIALS_SELECTORS);
        spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        financeSelectionSpinner.setAdapter(spinnerAdapter);

        financeSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(financeSelectionSpinner.getSelectedItem() == null) && !financeAmountText.getText().toString().isEmpty() && !financeDateText.getText().toString().isEmpty()){

                    //PUT THE DATABASE CODE HERE AND USE
                    String finance_selector = financeSelectionSpinner.getSelectedItem().toString();
                    Double finance_amount = Double.parseDouble(financeAmountText.getText().toString());
                    Date finance_date = Date.valueOf(financeDateText.getText().toString());


                }
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}