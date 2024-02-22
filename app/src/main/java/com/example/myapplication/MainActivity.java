import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private EditText amountField;
    private Spinner fromCurrencySpinner;
    private Spinner toCurrencySpinner;
    private Button convertButton;
    private TextView resultLabel;

    private HashMap<String, Double> exchangeRates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountField = findViewById(R.id.amount_field);
        fromCurrencySpinner = findViewById(R.id.from_currency_spinner);
        toCurrencySpinner = findViewById(R.id.to_currency_spinner);
        convertButton = findViewById(R.id.convert_button);
        resultLabel = findViewById(R.id.result_label);

        exchangeRates = new HashMap<>();
        exchangeRates.put("EUR", 4.9);
        exchangeRates.put("USD", 4.2);
        exchangeRates.put("GBP", 5.7);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.currencies_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromCurrencySpinner.setAdapter(adapter);
        toCurrencySpinner.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertCurrency();
            }
        });
    }

    private void convertCurrency() {
        String amountText = amountField.getText().toString();
        if (amountText.isEmpty()) {
            Toast.makeText(this, "Introduceți o sumă validă.", Toast.LENGTH_SHORT).show();
            return;
        }

        double amount = Double.parseDouble(amountText);
        String fromCurrency = fromCurrencySpinner.getSelectedItem().toString();
        String toCurrency = toCurrencySpinner.getSelectedItem().toString();

        double exchangeRate = exchangeRates.get(toCurrency) / exchangeRates.get(fromCurrency);
        double result = amount * exchangeRate;

        resultLabel.setText(String.format("%.2f %s", result, toCurrency));
    }
}
