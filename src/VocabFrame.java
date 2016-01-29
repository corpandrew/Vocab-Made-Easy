import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class VocabFrame extends JFrame {

	Utils utils = new Utils();

	private JButton getVocabButton;
	private JButton resetButton;
	private JLabel wordsListLabel;
	private JLabel exampleLabel;
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
		resetButton = new JButton();
		getVocabButton = new JButton();
		wordsListLabel = new JLabel();
		exampleLabel = new JLabel();
		definitionsListLabel = new JLabel();
		partOfSpeechRadio = new JRadioButton();
		includeMoreDefinitionsRadio = new JRadioButton();
		customizeOutputRadio = new JRadioButton();
		wordsScrollPane = new JScrollPane();
		definitionScrollPane = new JScrollPane();
		wordsTextArea = new JTextArea();
		definitionTextArea = new JTextArea();
		exampleField = new JTextField();
		customizeField = new JTextField();

		Font font = new Font("Arial", Font.PLAIN, 20);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		wordsListLabel.setText("Words List");

		wordsTextArea.setColumns(20);
		wordsTextArea.setRows(5);
		wordsTextArea.setFont(font);

		wordsScrollPane.setViewportView(wordsTextArea);

		partOfSpeechRadio.setText("Include Part of Speech");
		partOfSpeechRadio
				.setToolTipText("Includes the part of speech of the word");

		includeMoreDefinitionsRadio.setText("Include 2-3 definitions");
		includeMoreDefinitionsRadio
				.setToolTipText("Include more 2-3 definitions if possible");

		customizeOutputRadio.setText("Customize output layout");
		customizeOutputRadio
				.setToolTipText("Create your own definition layout");
		exampleField.setEditable(false);
		exampleField.setText("@num.@tab@def");

		exampleLabel.setText("Example:");

		customizeField.setEditable(false);
		customizeField.setText("@num.@tab@def");

		definitionTextArea.setColumns(20);
		definitionTextArea.setRows(5);
		definitionTextArea.setFont(font);

		definitionScrollPane.setViewportView(definitionTextArea);

		definitionsListLabel.setText("Definition List");

		getVocabButton.setText("Get Vocab");
		getVocabButton.setToolTipText("Get the definitions of the words");

		resetButton.setText("Reset Definition List");

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(wordsScrollPane,
										GroupLayout.PREFERRED_SIZE, 203,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.LEADING,
												false)
												.addComponent(partOfSpeechRadio)
												.addComponent(
														includeMoreDefinitionsRadio)
												.addComponent(
														customizeOutputRadio,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addGroup(
														GroupLayout.Alignment.TRAILING,
														layout.createSequentialGroup()
																.addComponent(
																		exampleLabel)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(
																		layout.createParallelGroup(
																				GroupLayout.Alignment.LEADING,
																				false)
																				.addComponent(
																						customizeField)
																				.addComponent(
																						exampleField)))
												.addComponent(
														getVocabButton,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(
														resetButton,
														GroupLayout.Alignment.TRAILING,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE))
								.addGap(18, 18, 18)
								.addComponent(definitionScrollPane,
										GroupLayout.DEFAULT_SIZE, 391,
										Short.MAX_VALUE).addContainerGap())
				.addGroup(
						layout.createSequentialGroup()
								.addGap(80, 80, 80)
								.addComponent(wordsListLabel)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED,
										GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(definitionsListLabel)
								.addGap(159, 159, 159)));
		layout.setVerticalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.BASELINE)
												.addComponent(wordsListLabel)
												.addComponent(
														definitionsListLabel))
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(
																		layout.createParallelGroup(
																				GroupLayout.Alignment.TRAILING)
																				.addComponent(
																						definitionScrollPane)
																				.addComponent(
																						wordsScrollPane)))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(25, 25,
																		25)
																.addComponent(
																		partOfSpeechRadio)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		includeMoreDefinitionsRadio)
																.addGap(18, 18,
																		18)
																.addComponent(
																		customizeOutputRadio)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(
																		layout.createParallelGroup(
																				GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						exampleField,
																						GroupLayout.PREFERRED_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						exampleLabel))
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		customizeField,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED,
																		43,
																		Short.MAX_VALUE)
																.addComponent(
																		getVocabButton)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		resetButton)
																.addGap(9, 9, 9)))
								.addContainerGap()));

		pack();
		utils.customFormatText = customizeField.getText();
		setTitle("Vocab Made Easy");
		setVisible(true);
	}// </editor-fold>

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
	}

}
