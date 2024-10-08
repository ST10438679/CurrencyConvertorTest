package currencyconvertortest;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
//new code//
import java.util.ArrayList;
//end of new code//

public class CurrencyConvertorTest implements ActionListener {
    
    ArrayList<UsersClass> Users = new ArrayList<>();


    // Replace with your actual API key
    final String API_ENDPOINT = "https://v6.exchangerate-api.com/v6/9eff8f8a461936a74b762f3a/latest/USD";
    static String[] CURRENCIES = {
            "USD", "AED", "AFN", "ALL", "AMD", "ANG", "AOA", "ARS", "AUD", "AWG",
            "AZN", "BAM", "BBD", "BDT", "BGN", "BHD", "BIF", "BMD", "BND", "BOB",
            "BRL", "BSD", "BTN", "BWP", "BYN", "BZD", "CAD", "CDF", "CHF", "CLP",
            "CNY", "COP", "CRC", "CUP", "CVE", "CZK", "DJF", "DKK", "DOP", "DZD",
            "EGP", "ERN", "ETB", "EUR", "FJD", "FKP", "FOK", "GBP", "GEL", "GGP",
            "GHS", "GIP", "GMD", "GNF", "GTQ", "GYD", "HKD", "HNL", "HRK", "HTG",
            "HUF", "IDR", "ILS", "IMP", "INR", "IQD", "IRR", "ISK", "JEP", "JMD",
            "JOD", "JPY", "KES", "KGS", "KHR", "KID", "KMF", "KRW", "KWD", "KYD",
            "KZT", "LAK", "LBP", "LKR", "LRD", "LSL", "LYD", "MAD", "MDL", "MGA",
            "MKD", "MMK", "MNT", "MOP", "MRU", "MUR", "MVR", "MWK", "MXN", "MYR",
            "MZN", "NAD", "NGN", "NIO", "NOK", "NPR", "NZD", "OMR", "PAB", "PEN",
            "PGK", "PHP", "PKR", "PLN", "PYG", "QAR", "RON", "RSD", "RUB", "RWF",
            "SAR", "SBD", "SCR", "SDG", "SEK", "SGD", "SHP", "SLL", "SOS", "SRD",
            "SSP", "STN", "SYP", "SZL", "THB", "TJS", "TMT", "TND", "TOP", "TRY",
            "TTD", "TVD", "TWD", "TZS", "UAH", "UGX", "UYU", "UZS", "VEF", "VND",
            "VUV", "WST", "XAF", "XCD", "XDR", "XOF", "XPF", "YER", "ZAR", "ZMW",
            "ZWL"
    };
    

    static JFrame f;
    static JLabel amountLabel;
    static JLabel fromLabel;
    static JLabel toLabel;
    static JLabel resultLabel;
    static JComboBox<String> fromCurrencyBox;
    static JComboBox<String> toCurrencyBox;
    static JTextField AmountField;
    static JPanel p;

    public static void main(String[] args) {
        f = new JFrame("CURENCIO");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(300, 350);
        f.setLayout(null);
        f.setLocationRelativeTo(null);

        amountLabel = new JLabel("Amount");
        amountLabel.setFont(new Font("Arial", Font.BOLD, 16));
        amountLabel.setBounds(15, 10, 100, 34);

        AmountField = new JTextField("1");
        AmountField.setBounds(10, 40, 180, 34);

        fromLabel = new JLabel("From");
        fromLabel.setFont(new Font("Arial", Font.BOLD, 16));
        fromLabel.setBounds(15, 70, 80, 34);

        toLabel = new JLabel("To");
        toLabel.setFont(new Font("Arial", Font.BOLD, 16));
        toLabel.setBounds(15, 130, 80, 34);

        fromCurrencyBox = new JComboBox<>(CURRENCIES);
        fromCurrencyBox.setBounds(5, 105, 200, 25);

        toCurrencyBox = new JComboBox<>(CURRENCIES);
        toCurrencyBox.setBounds(5, 165, 200, 25);

        resultLabel = new JLabel();

        p = new JPanel();
        p.setLocation(300, 150);

        JButton converterButton = new JButton("Convert");
        converterButton.setFont(new Font("Arial", Font.BOLD, 16));
        converterButton.setBackground(Color.RED);
        converterButton.setBounds(10, 220, 265, 40);
        
        //new code
        JButton swapButton = new JButton("Swap");
        swapButton.setFont(new Font("Arial", Font.BOLD, 16));
        swapButton.setBackground(Color.BLUE);
        swapButton.setBounds(115, 134, 90, 25);
        
        JButton LoginButton = new JButton("Login");
        LoginButton.setFont(new Font("Arial", Font.BOLD, 16));
        LoginButton.setBackground(Color.YELLOW);
        LoginButton.setBounds(190, 15, 90, 25);
        
        JButton RegisterButton = new JButton("Register");
        RegisterButton.setFont(new Font("Arial", Font.BOLD, 13));
        RegisterButton.setBackground(Color.GREEN);
        RegisterButton.setBounds(190, 45, 90, 25);
        //end of new code
        
        f.add(amountLabel);
        f.add(AmountField);
        f.add(fromLabel);
        f.add(toLabel);
        f.add(fromCurrencyBox);
        f.add(toCurrencyBox);
        f.add(converterButton);
        
        //new code
        f.add(swapButton);
        f.add(LoginButton);
        f.add(RegisterButton);
        swapButton.addActionListener(new CurrencyConvertorTest());
        LoginButton.addActionListener(new CurrencyConvertorTest());
        RegisterButton.addActionListener(new CurrencyConvertorTest());
        //end of new code
        
        converterButton.addActionListener(new CurrencyConvertorTest());
        f.add(p);
        f.setVisible(true);
    }

    @SuppressWarnings("deprecation")
    public double convert(double amount, String fromCurrency, String toCurrency) throws IOException {
        // Make Request
        URL url = new URL(API_ENDPOINT);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        // Convert to JSON
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader(request.getInputStream()));
        JsonObject jsonobj = root.getAsJsonObject();

        // Accessing object
        JsonObject conversionRates = jsonobj.getAsJsonObject("conversion_rates");

        // Ensure that the currencies exist in the conversion_rates object
        if (!conversionRates.has(fromCurrency) || !conversionRates.has(toCurrency)) {
            throw new IllegalArgumentException("Invalid currency codes");
        }

        double rateFrom = conversionRates.get(fromCurrency).getAsDouble();
        double rateTo = conversionRates.get(toCurrency).getAsDouble();

        return amount * rateTo / rateFrom;
    }

    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("Convert")) {
            double amount = Double.parseDouble(AmountField.getText());
            String fromCurrency = (String) fromCurrencyBox.getSelectedItem();
            String toCurrency = (String) toCurrencyBox.getSelectedItem();
            double result;
            try {
                result = convert(amount, fromCurrency, toCurrency);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            resultLabel.setText(String.format("%.2f %s = %.2f %s", amount, fromCurrency, result, toCurrency));
            resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
            resultLabel.setForeground(Color.BLACK);
            resultLabel.setBounds(45, 270, 280, 20);
            f.add(resultLabel);
            f.revalidate();
            f.repaint();
        }
        //new code//
        if (s.equals("Swap"))
        {
            String fromCurrency = (String) fromCurrencyBox.getSelectedItem();
            String toCurrency = (String) toCurrencyBox.getSelectedItem();
            
            fromCurrencyBox.setSelectedItem(toCurrency);
            toCurrencyBox.setSelectedItem(fromCurrency);
        }
        if (s.equals("Register"))
        {
            
            int iOption;
            
            String[] sOptions = {"Register", "Quit"};
            iOption = JOptionPane.showOptionDialog(null, "Option 1) Register " + "\nOption 2) Quit ",
                "Login Menu", 0, 1, null, sOptions, sOptions[0]);
            
            switch (iOption) 
            {
            case 0:
                String FullName = JOptionPane.showInputDialog("Please enter your full Name");
                String UserName = JOptionPane.showInputDialog("Please enter a Username");
                String Password = JOptionPane.showInputDialog("Please enter a password");
                JOptionPane.showMessageDialog(null, "Registeration Successful" + "\nFull Name: " + FullName + "\n"
                        + "Username: " + UserName + "\nPassword: " + Password);
                Users.add(new UsersClass(FullName, UserName, Password));
                break;
            }
            
        }
        if (s.equals("Login"))
        {
            int iOption;
            String[] sOptions = {"Login", "Quit"};
            iOption = JOptionPane.showOptionDialog(null, "Option 1) Login " + "\nOption 2) Quit ",
                "Login Menu", 0, 1, null, sOptions, sOptions[0]);
            
            switch (iOption) 
            {
            case 0:
                String FullName = JOptionPane.showInputDialog("Please enter your full Name");
                String UserName = JOptionPane.showInputDialog("Please enter your Username");
                String Password = JOptionPane.showInputDialog("Please enter your Password");
                
                boolean hasUser = Users.contains(new UsersClass(FullName, UserName, Password));
                JOptionPane.showMessageDialog(fromLabel, "Login is Successful: " + hasUser);
                break;
            }
        }
        //end of new code//
    }
}