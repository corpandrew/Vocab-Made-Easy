import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.plaf.FileChooserUI;

public class VocabFrame extends JFrame {

    Utils utils = new Utils();

    private JButton getVocabButton;
    private JButton resetButton;
    private JLabel wordsListLabel;
    private JLabel exampleLabel;
    private JButton filepathButton;
    private JTextField filepathField;
    private JLabel definitionsListLabel;
    private JRadioButton partOfSpeechRadio;
    private JRadioButton includeMoreDefinitionsRadio;
    private JRadioButton customizeOutputRadio;
    private JScrollPane wordsScrollPane;
    private JScrollPane definitionScrollPane;
    private JTextArea wordsTextArea;
    private JTextArea definitionTextArea;
    private JTextField exampleField;
    private JTextField customizeField;

    @SuppressWarnings("unchecked")
    private void initComponents() {

        wordsListLabel = new javax.swing.JLabel();
        wordsScrollPane = new javax.swing.JScrollPane();
        wordsTextArea = new javax.swing.JTextArea();
        partOfSpeechRadio = new javax.swing.JRadioButton();
        includeMoreDefinitionsRadio = new javax.swing.JRadioButton();
        customizeOutputRadio = new javax.swing.JRadioButton();
        exampleField = new javax.swing.JTextField();
        exampleLabel = new javax.swing.JLabel();
        customizeField = new javax.swing.JTextField();
        definitionScrollPane = new javax.swing.JScrollPane();
        definitionTextArea = new javax.swing.JTextArea();
        definitionsListLabel = new javax.swing.JLabel();
        getVocabButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        filepathButton = new javax.swing.JButton();
        filepathField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Vocab Made Easy");

        wordsListLabel.setLabelFor(wordsListLabel);
        wordsListLabel.setText("Words List");

        wordsTextArea.setColumns(20);
        wordsTextArea.setRows(5);
        wordsScrollPane.setViewportView(wordsTextArea);

        partOfSpeechRadio.setText("Include Part of Speech");
        partOfSpeechRadio.setToolTipText("Includes the part of speech of the word");

        includeMoreDefinitionsRadio.setText("Include 2-3 definitions");
        includeMoreDefinitionsRadio.setToolTipText("Inlcude more 2-3 definitions if possible");

        customizeOutputRadio.setText("Customize output layout");

        exampleField.setEditable(false);
        exampleField.setText("@num.@tab@def");

        exampleLabel.setText("Example:");

        customizeField.setEditable(false);
        customizeField.setText("@num.@tab@def");

        definitionTextArea.setColumns(20);
        definitionTextArea.setRows(5);
        definitionScrollPane.setViewportView(definitionTextArea);

        definitionsListLabel.setText("Definition List");

        getVocabButton.setText("Get Vocab");

        resetButton.setText("Reset Definition List");

        filepathButton.setText("Import Words from File");

        filepathField.setText("File Path");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(wordsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(customizeOutputRadio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(exampleLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(customizeField)
                                                        .addComponent(exampleField)))
                                        .addComponent(getVocabButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(resetButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(filepathButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(partOfSpeechRadio)
                                                        .addComponent(includeMoreDefinitionsRadio))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(filepathField))
                                .addGap(18, 18, 18)
                                .addComponent(definitionScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                                .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(wordsListLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(definitionsListLabel)
                                .addGap(194, 194, 194))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(wordsListLabel)
                                        .addComponent(definitionsListLabel))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(25, 25, 25)
                                                .addComponent(partOfSpeechRadio)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(includeMoreDefinitionsRadio)
                                                .addGap(18, 18, 18)
                                                .addComponent(customizeOutputRadio)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(exampleField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(exampleLabel))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(customizeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(filepathButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(filepathField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(getVocabButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(resetButton)
                                                .addContainerGap(23, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(wordsScrollPane)
                                                        .addComponent(definitionScrollPane, javax.swing.GroupLayout.Alignment.TRAILING))
                                                .addContainerGap())))
        );

        pack();
        setVisible(true);
    }

    public VocabFrame() {

        initComponents();

        resetButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                definitionTextArea.setText("");
                utils.i = 1;
            }
        });

        getVocabButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!wordsTextArea.getText().equals("")) {
                    ArrayList<String> words = new ArrayList<>();
                    for (String word : wordsTextArea.getText().split("\\n")) {
                        words.add(word);
                    }
                    try {
                        String definitions = utils.getDefinitions(words,
                                partOfSpeechRadio.isSelected(),
                                includeMoreDefinitionsRadio.isSelected(),
                                customizeOutputRadio.isSelected());
                        definitionTextArea.append(definitions);
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }
            }
        });

        customizeOutputRadio.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (customizeOutputRadio.isSelected()) {
                    customizeField.setEditable(true);
                } else {
                    customizeField.setEditable(false);
                }
            }
        });

        customizeField.addCaretListener(new CaretListener() {

            @Override
            public void caretUpdate(CaretEvent e) {
                utils.customFormatText = customizeField.getText();

            }
        });

        filepathButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser fileChooser = new JFileChooser();

                int returnVal = fileChooser.showOpenDialog(VocabFrame.this);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {

                        FileInputStream file = new FileInputStream(fileChooser.getSelectedFile());

                        filepathField.setText(fileChooser.getSelectedFile().getAbsolutePath());

                        InputStreamReader reader = new InputStreamReader(file, Charset.defaultCharset().forName("UTF-8"));
                        BufferedReader bufferReader = new BufferedReader(reader);

                        String word;

                        while ((word = bufferReader.readLine()) != null) {
                            wordsTextArea.append(word + "\n");
                        }

                    } catch (FileNotFoundException exception) {
                        exception.printStackTrace();
                    } catch (IOException exception2) {
                        exception2.printStackTrace();
                    }

                }
            }
        });

        filepathField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    File file = new File(filepathField.getText());

                    FileInputStream fileStream = new FileInputStream(file);

                    filepathField.setText(file.getAbsolutePath());

                    InputStreamReader reader = new InputStreamReader(fileStream, Charset.defaultCharset().forName("UTF-8"));
                    BufferedReader bufferReader = new BufferedReader(reader);

                    String word;

                    while ((word = bufferReader.readLine()) != null) {
                        wordsTextArea.append(word + "\n");
                    }
                } catch (FileNotFoundException exception) {
                    exception.printStackTrace();
                } catch (IOException exception2) {
                    exception2.printStackTrace();
                }
            }
        });
    }

}
