package compilador;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import gals.LexicalError;
import gals.Lexico;
import gals.SemanticError;
import gals.Semantico;
import gals.Sintatico;
import gals.SyntaticError;
import gals.Token;

public class Screen extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jpMenu;
	private JPanel jpStatusBar;
	private JScrollPane scEditor = new JScrollPane();;
	private JScrollPane scMessageArea = new JScrollPane();
	private JButton btnNew = new JButton("novo (ctrl+n)");
	private JButton btnOpen = new JButton("abrir (ctrl+o)");
	private JButton btnSave = new JButton("salvar (ctrl+s)");
	private JButton btnCopy = new JButton("copiar (ctrl+c)");
	private JButton btnPaste = new JButton("colar (ctrl+v)");
	private JButton btnCut = new JButton("recortar (ctrl+x)");
	private JButton btnRun = new JButton("compilar (F7)");
	private JButton btnTeam = new JButton("equipe (F1)");
	private JTextArea jtMessageArea = new JTextArea();
	private JTextArea jtEditor = new JTextArea();
	private JLabel lblPath = new JLabel("");
	private ButtonAction btnAction = new ButtonAction();
	private File file = null;
	private JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scEditor, scMessageArea);

	public Screen() {
		initComponents();
	}

	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(900, 600));
		pack();

		setMenu();
		setStatusBar();
		setEditor();
		setMessageArea();
		setKeyActions();
		setSplitPane();
		setButtonsColor();

		setVisible(true);
	}

	private void setButtonsColor() {
		btnNew.setBackground(new Color(220, 220, 220));
		btnNew.setForeground(Color.BLACK);

		btnOpen.setBackground(new Color(220, 220, 220));
		btnOpen.setForeground(Color.BLACK);

		btnSave.setBackground(new Color(220, 220, 220));
		btnSave.setForeground(Color.BLACK);

		btnCopy.setBackground(new Color(220, 220, 220));
		btnCopy.setForeground(Color.BLACK);

		btnPaste.setBackground(new Color(220, 220, 220));
		btnPaste.setForeground(Color.BLACK);

		btnCut.setBackground(new Color(220, 220, 220));
		btnCut.setForeground(Color.BLACK);

		btnRun.setBackground(new Color(220, 220, 220));
		btnRun.setForeground(Color.BLACK);

		btnTeam.setBackground(new Color(220, 220, 220));
		btnTeam.setForeground(Color.BLACK);
	}

	private void setMenu() {
		jpMenu = new JPanel();
		jpMenu.setLayout(new GridLayout(0, 1, 0, 0));
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewAction();
			}
		});

		jpMenu.add(btnNew);
		try {
			Image icon = ImageIO.read(getClass().getResource("../icons/new.png"));
			btnNew.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnNew.setHorizontalTextPosition(SwingConstants.CENTER);
			btnNew.setIcon(new ImageIcon(icon));
		} catch (Exception ex) {
			System.out.println(ex);
		}

		jpMenu.add(btnOpen);
		try {
			Image icon = ImageIO.read(getClass().getResource("../icons/open.png"));
			btnOpen.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnOpen.setHorizontalTextPosition(SwingConstants.CENTER);
			btnOpen.setIcon(new ImageIcon(icon));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnOpenAction();
			}
		});

		jpMenu.add(btnSave);
		try {
			Image icon = ImageIO.read(getClass().getResource("../icons/save.png"));
			btnSave.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnSave.setHorizontalTextPosition(SwingConstants.CENTER);
			btnSave.setIcon(new ImageIcon(icon));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnSaveAction();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		jpMenu.add(btnCopy);
		try {
			Image icon = ImageIO.read(getClass().getResource("../icons/copy.png"));
			btnCopy.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnCopy.setHorizontalTextPosition(SwingConstants.CENTER);
			btnCopy.setIcon(new ImageIcon(icon));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		btnCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCopyAction();
			}
		});

		jpMenu.add(btnPaste);
		try {
			Image icon = ImageIO.read(getClass().getResource("../icons/paste.png"));
			btnPaste.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnPaste.setHorizontalTextPosition(SwingConstants.CENTER);
			btnPaste.setIcon(new ImageIcon(icon));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		btnPaste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPasteAction();
			}
		});

		jpMenu.add(btnCut);
		try {
			Image icon = ImageIO.read(getClass().getResource("../icons/cut.png"));
			btnCut.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnCut.setHorizontalTextPosition(SwingConstants.CENTER);
			btnCut.setIcon(new ImageIcon(icon));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		btnCut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCutAction();
			}
		});

		jpMenu.add(btnRun);
		try {
			Image icon = ImageIO.read(getClass().getResource("../icons/run.png"));
			btnRun.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnRun.setHorizontalTextPosition(SwingConstants.CENTER);
			btnRun.setIcon(new ImageIcon(icon));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRunAction();
			}
		});

		jpMenu.add(btnTeam);
		try {
			Image icon = ImageIO.read(getClass().getResource("../icons/team.png"));
			btnTeam.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnTeam.setHorizontalTextPosition(SwingConstants.CENTER);
			btnTeam.setIcon(new ImageIcon(icon));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		btnTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnTeamAction();
			}
		});
	}

	private void setStatusBar() {
		jpStatusBar = new JPanel();
		jpStatusBar.setLayout(null);

		lblPath.setHorizontalAlignment(SwingConstants.LEFT);
		lblPath.setBounds(0, 0, 900, 25);
		jpStatusBar.add(lblPath);
	}

	private void setEditor() {
		jtEditor.setBorder(new NumberedBorder());
		splitPane.setLeftComponent(scEditor);
		scEditor.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scEditor.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scEditor.setViewportView(jtEditor);
	}

	private void setMessageArea() {

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(jpMenu, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 929, Short.MAX_VALUE))
				.addComponent(jpStatusBar, GroupLayout.DEFAULT_SIZE, 1085, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(jpMenu, GroupLayout.PREFERRED_SIZE, 500,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18))
								.addGroup(groupLayout.createSequentialGroup().addGap(11)
										.addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)))
						.addComponent(jpStatusBar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)));

		scMessageArea.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scMessageArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		jtMessageArea.setEditable(false);
		scMessageArea.setViewportView(jtMessageArea);
		getContentPane().setLayout(groupLayout);

	}

	private void setSplitPane() {
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(150);
		splitPane.setDividerLocation(400 + splitPane.getInsets().top);
	}

	private void setKeyActions() {
		btnNew.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK), "new");
		btnNew.getActionMap().put("new", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnNewAction();
			}
		});

		btnOpen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK), "open");
		btnOpen.getActionMap().put("open", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnOpenAction();
			}
		});

		btnSave.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK), "save");
		btnSave.getActionMap().put("save", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					btnSaveAction();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		btnRun.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0), "run");
		btnRun.getActionMap().put("run", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnRunAction();
			}
		});

		btnTeam.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "team");
		btnTeam.getActionMap().put("team", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnTeamAction();
			}
		});
	}

	private void btnNewAction() {
		file = null;
		jtMessageArea.setText("");
		jtEditor.setText("");
		lblPath.setText("");
	}

	private void btnSaveAction() throws IOException {
		if (file == null) {
			JFileChooser fc = new JFileChooser(new File("c:\\"));
			fc.setDialogTitle("Salvar arquivo");
			fc.setApproveButtonText("Save");
			fc.setFileFilter(new FileTypeFilter(".txt", "Text File"));
			int result = fc.showOpenDialog(null);

			if (result == JFileChooser.APPROVE_OPTION) {
				String content = jtEditor.getText();
				file = fc.getSelectedFile();
				String path = file.getPath() + ".txt";

				try {
					FileWriter fw = new FileWriter(path);
					fw.write(content);
					fw.flush();
					fw.close();

				} catch (Exception e2) {
					System.out.println(e2);
				}

				jtMessageArea.setText("");
				lblPath.setText(path);
			}

		} else {
			String path = file.getPath();
			file.delete();
			File fnew = new File(path);

			String source = jtEditor.getText();

			try {
				FileWriter f2 = new FileWriter(fnew, false);
				f2.write(source);
				f2.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
			jtMessageArea.setText("");
		}
	}

	private void btnOpenAction() {
		final JFileChooser fc = new JFileChooser();
		fc.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text", "txt");
		fc.setDialogTitle("Abrir arquivo");
		fc.addChoosableFileFilter(filter);
		fc.showOpenDialog(btnOpen);
		file = fc.getSelectedFile();

		if (file == null)
			return;

		try {
			jtEditor.setText("");
			BufferedReader in = new BufferedReader(new FileReader(file));
			String line = in.readLine();
			while (line != null) {
				jtEditor.setText(jtEditor.getText() + line + "\n");
				line = in.readLine();
			}

		} catch (FileNotFoundException ex) {
			System.out.println(ex);
		} catch (IOException ex) {
			System.out.println(ex);
		}

		jtMessageArea.setText("");
		lblPath.setText(file.getPath());
	}

	private void btnCopyAction() {
		btnAction.copy(jtEditor.getSelectedText());
	}

	private void btnPasteAction() {
		jtEditor.replaceSelection("");
		jtEditor.insert(btnAction.paste(), jtEditor.getCaretPosition());
	}

	private void btnCutAction() {
		btnAction.copy(jtEditor.getSelectedText());
		jtEditor.replaceSelection("");
	}

	private void btnRunAction() {

		String texto = jtEditor.getText();
		Lexico lexico = new Lexico();
		Sintatico sintatico = new Sintatico();
		Semantico semantico = new Semantico();

		lexico.setInput(texto);
		String mensagem = "";
		String linhas[] = texto.split("\n");
		try {
			validaPalavrasReservadas(linhas);
			sintatico.parse(lexico, semantico);
			mensagem = "programa compilado com sucesso";
		} catch (SyntaticError e) { // Trata erros sintáticos
			mensagem = "Erro na linha " + getLinha(linhas, e.getErro(), e.getPosition()) + " - encontrado "
					+ e.getErro() + " " + e.getMessage();

		} catch (LexicalError e) { // Trata erros léxicos

			String[] erro = e.getErro().split("\n");
			if (e.getMessage().equalsIgnoreCase("Comentário de bloco inválido ou não finalizado")
					|| e.getMessage().equalsIgnoreCase("Constante string inválida ou não finalizada")) {
				mensagem = "Erro na linha " + getLinha(linhas, erro[0], e.getPosition()) + " - " + e.getMessage();
				System.out.println("Erro na linha " + getLinha(linhas, erro[0], e.getPosition()) + " - " + e.getMessage());
			} else {
				mensagem = "Erro na linha " + getLinha(linhas, e.getErro(), e.getPosition()) + " - " + e.getErro() + " " + e.getMessage();
				System.out.println(
						"Erro na linha " + getLinha(linhas, e.getErro(), e.getPosition()) + " - " + e.getErro() + " " + e.getMessage());
			}

		} catch (SemanticError e) {// Trata erros semânticos
		}

		this.jtMessageArea.setText(mensagem);
	}

	private void validaPalavrasReservadas(String[] linhas) throws LexicalError {
		int positionCount = 0;
		for (int x = 0; x < linhas.length; x++) {

			String palavra = linhas[x];
			for (int y = 0; y < palavra.length(); y++) {
				positionCount++;
			}
			if (palavra.equals("char") || palavra.equals("aNd") || palavra.equals("endwhile") || palavra.equals("bool")
					|| palavra.equals("iffalse")) {
				throw new LexicalError("palavra reservada inválida", palavra, positionCount + linhas.length);
			}
		}
	}

//getLinha() antigo
//	private int getLinha(String[] linhas, String token) {
//		for (int x = 0; x < linhas.length; x++) {
//			if (linhas[x].contains(token)) {
//				return x + 1;
//			}
//		}
//		return 0;
//	}

	private int getLinha(String[] linhas, String token, int position) {
		
// verifica se texto e linhas tem a mesma quantidade de caracteres
//		String texto = jtEditor.getText();
//		String teste = texto.substring(position);
//		int tamanhoTexto = texto.length();
//		int tamanhoLinhas = 0;
//		for (int x = 0; x < linhas.length; x++) {
//			String linhaAtual = linhas[x] + " ";
//			for (int y = 0; y < linhaAtual.length(); y++) {
//				tamanhoLinhas++;
//			}
//		}
		int positionCount = 0;
		for (int x = 0; x < linhas.length; x++) {
			String linhaAtual = linhas[x] + " ";

			for (int y = 0; y < linhaAtual.length(); y++) {
				positionCount++;
				if (positionCount == position) {
					return x + 1;
				}
			}
		}
		return 0;
	}

	private void btnTeamAction() {
		jtMessageArea.setText(btnAction.getTeam());
	}

//	private String getLexemeClass(int id) {
//		String lexemeClass = "";
//
//		if (id == 2 || (id >= Constants.t_and && id <= Constants.t_while)) {
//			lexemeClass = "palavra reservada ";
//		} else if (id == Constants.t_constInt) {
//			lexemeClass = "constante int	";
//		} else if (id == Constants.t_constFloat) {
//			lexemeClass = "constante float	";
//		} else if (id == Constants.t_constString) {
//			lexemeClass = "constante string ";
//		} else if (id >= Constants.t_int && id <= Constants.t_bool) {
//			lexemeClass = "identificador	";
//		} else if (id >= Constants.t_TOKEN_28 && id <= Constants.t_TOKEN_43) {
//			lexemeClass = "símbolo especial ";
//		}
//
//		return lexemeClass;
//	}

	public static void main(String args[]) {
		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Screen().setVisible(true);
			}
		});
	}
}
