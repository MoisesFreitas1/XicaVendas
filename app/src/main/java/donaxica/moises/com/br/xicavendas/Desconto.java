package donaxica.moises.com.br.xicavendas;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * Created by Moises on 04/09/16.
 */
public class Desconto  extends Fragment {

    private static final NumberFormat numberFormat = NumberFormat.getCurrencyInstance();

    private double pagar = 0.0;
    private double valorRoupa = 0.0;
    private double desconto = 0.0;
    private TextView vroupaTextView;
    private TextView desTextView;
    private TextView vpTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.desconto,container,false);

        vroupaTextView = (TextView) view.findViewById(R.id.vroupaTextView);
        desTextView = (TextView) view.findViewById(R.id.desTextView);
        vpTextView = (TextView) view.findViewById(R.id.vpTextView);

        vroupaTextView.setText(numberFormat.format(valorRoupa));
        desTextView.setText(numberFormat.format(desconto));
        vpTextView.setText(numberFormat.format(pagar));
        update();

        EditText vroupaEditText = (EditText) view.findViewById(R.id.vroupaEditText);
        vroupaEditText.addTextChangedListener(vroupaEditTextWatcher);
        return view;
    }

    private void update(){
        double p;
        double vr = valorRoupa;
        double d;

        if(vr<50){
            d=0;
            p=vr;
            vroupaTextView.setText(numberFormat.format(vr));
            desTextView.setText(numberFormat.format(d));
            vpTextView.setText(numberFormat.format(p));
        } else{
            d=vr*0.1;
            p=vr-d;
            vroupaTextView.setText(numberFormat.format(vr));
            desTextView.setText(numberFormat.format(d));
            vpTextView.setText(numberFormat.format(p));
        }
    }

    private TextWatcher vroupaEditTextWatcher = new TextWatcher()
    {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {
            try
            {
                valorRoupa = Double.parseDouble(s.toString()) / 100.0;
            }
            catch (NumberFormatException e)
            {
                valorRoupa = 0.0;
            }
            vroupaTextView.setText(numberFormat.format(valorRoupa));
            update();
        }
        @Override
        public void afterTextChanged(Editable s)
        {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {
        }
    };
}
