import javax.swing.*;

public class TemperatureConverterUI extends JFrame {
    
    private JTextField temperatureField;
    private JComboBox<String> unitComboBox;
    private JTextArea resultArea;
    
    public TemperatureConverterUI() {
        setTitle("Temperature Converter");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel();
        JLabel temperatureLabel = new JLabel("Enter temperature:");
        temperatureField = new JTextField(10);
        unitComboBox = new JComboBox<>(new String[]{"Celsius", "Fahrenheit", "Kelvin"});
        JButton convertButton = new JButton("Convert");
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        
        convertButton.addActionListener(e -> convertTemperature());
        
        panel.add(temperatureLabel);
        panel.add(temperatureField);
        panel.add(unitComboBox);
        panel.add(convertButton);
        panel.add(new JScrollPane(resultArea));
        
        add(panel);
        setVisible(true);
    }
    
    private void convertTemperature() {
        try {
            double temperature = Double.parseDouble(temperatureField.getText());
            String originalUnit = (String) unitComboBox.getSelectedItem();
            double convertedCelsius = 0;
            double convertedFahrenheit = 0;
            double convertedKelvin = 0;
            
            switch (originalUnit) {
                case "Celsius":
                    convertedCelsius = temperature;
                    convertedFahrenheit = celsiusToFahrenheit(temperature);
                    convertedKelvin = celsiusToKelvin(temperature);
                    break;
                case "Fahrenheit":
                    convertedCelsius = fahrenheitToCelsius(temperature);
                    convertedFahrenheit = temperature;
                    convertedKelvin = fahrenheitToKelvin(temperature);
                    break;
                case "Kelvin":
                    convertedCelsius = kelvinToCelsius(temperature);
                    convertedFahrenheit = kelvinToFahrenheit(temperature);
                    convertedKelvin = temperature;
                    break;
            }
            
            resultArea.setText(String.format("%.2f degrees Celsius\n%.2f degrees Fahrenheit\n%.2f Kelvin", 
                    convertedCelsius, convertedFahrenheit, convertedKelvin));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid temperature.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private double celsiusToFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }
    
    private double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }
    
    private double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }
    
    private double fahrenheitToKelvin(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9 + 273.15;
    }
    
    private double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }
    
    private double kelvinToFahrenheit(double kelvin) {
        return (kelvin - 273.15) * 9 / 5 + 32;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TemperatureConverterUI::new);
    }
}
