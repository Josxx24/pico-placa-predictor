package com.pico.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import com.pico.domain.models.Plate;
import com.pico.domain.predictor.PicoPlacaPredictor;
import com.pico.domain.rules.PicoPlacaSchedule;
import com.pico.domain.validation.InputValidator;

public class PicoPlacaGUI extends JFrame {

    private final JTextField plateField;
    private final JTextField dateField;
    private final JTextField timeField;
    private final JLabel resultLabel;
    private final JButton btnPredict;

    private final PicoPlacaPredictor predictor;

    public PicoPlacaGUI() {
        predictor = new PicoPlacaPredictor(new PicoPlacaSchedule());

        setTitle("Pico y Placa Predictor");
        setSize(new Dimension(480, 280));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        plateField = new JTextField();
        dateField = new JTextField();
        timeField = new JTextField();
        resultLabel = new JLabel("", SwingConstants.CENTER);

        btnPredict = new JButton("Predict");
        JButton btnClear = new JButton("Clear");
        JButton btnExit = new JButton("Exit");

        btnPredict.addActionListener(e -> predictAsync());
        btnClear.addActionListener(e -> clearFields());
        btnExit.addActionListener(e -> dispose());

        JPanel center = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.0;
        center.add(new JLabel("Plate (ABC-1234):"), gbc);

        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1.0;
        center.add(plateField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.0;
        center.add(new JLabel("Date (dd-MM-yyyy):"), gbc);

        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 1.0;
        center.add(dateField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.0;
        center.add(new JLabel("Time (HH:mm):"), gbc);

        gbc.gridx = 1; gbc.gridy = 2; gbc.weightx = 1.0;
        center.add(timeField, gbc);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 8));
        bottom.add(btnPredict);
        bottom.add(btnClear);
        bottom.add(btnExit);

        JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.add(resultLabel, BorderLayout.CENTER);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(center, BorderLayout.CENTER);
        getContentPane().add(bottom, BorderLayout.SOUTH);
        getContentPane().add(resultPanel, BorderLayout.NORTH);

        // Tooltips to guide the user
        plateField.setToolTipText("Format: ABC-1234");
        dateField.setToolTipText("Format: dd-MM-yyyy");
        timeField.setToolTipText("Format: HH:mm");

        setVisible(true);
    }

    private void predictAsync() {
        // Disable the predict button to prevent re-entry
        btnPredict.setEnabled(false);
        resultLabel.setText("Calculating...");

        SwingWorker<PicoPlacaPredictor.PredictionResult, Void> worker =
            new SwingWorker<>() {
                @Override
                protected PicoPlacaPredictor.PredictionResult doInBackground() throws Exception {
                    String plateStr = plateField.getText();
                    String dateStr = dateField.getText();
                    String timeStr = timeField.getText();

                    // Validation (throws exceptions with clear messages)
                    InputValidator.validatePlate(plateStr);
                    LocalDate date = InputValidator.validateDate(dateStr);
                    LocalTime time = InputValidator.validateTime(timeStr);

                    Plate plate = new Plate(plateStr);
                    return predictor.getPrediction(plate, date, time);
                }

                @Override
                protected void done() {
                    try {
                        PicoPlacaPredictor.PredictionResult result = get();
                        resultLabel.setText(result.message);
                    } catch (Exception ex) {
                        // Show modal dialog and set concise label text
                        String msg = ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage();
                        JOptionPane.showMessageDialog(PicoPlacaGUI.this, msg, "Error", JOptionPane.ERROR_MESSAGE);
                        resultLabel.setText("Error: " + (msg == null ? "Unexpected error" : msg));
                    } finally {
                        btnPredict.setEnabled(true);
                    }
                }
            };

        worker.execute();
    }

    private void clearFields() {
        plateField.setText("");
        dateField.setText("");
        timeField.setText("");
        resultLabel.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PicoPlacaGUI::new);
    }
}
