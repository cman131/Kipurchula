import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;


public class Play {

	public CodedTree tree = new CodedTree(null);
	public CodedTree cur = tree;
	public static ArrayList<String> saves = new ArrayList<String>();
	public String curDATA = "DEFAULT~ ~0";
	public static void main(String[] args) {
		Play play = new Play();
		 play.populate();
		 play.InitiateGUI();
		while (play.win.isShowing()) {
			System.out.println("");
		}
		try {
	        ObjectOutput output = new ObjectOutputStream(new BufferedOutputStream(
	            new FileOutputStream("Save_Data.conor")));
	        output.writeObject(saves.toArray());
	        output.close();
	        System.out.println("DONE!");
	      }
	      catch (FileNotFoundException e) {
	        System.out.println("I can't find your file!");
	      }
	      catch (IOException e) {
	        e.printStackTrace();
	      }
	      finally {
	        System.exit(0);
	      }
	}
	/**
	   * GUI Objects
	   */
	public JFrame win = new JFrame("Kipurchula");
	public static DefaultListModel<String> listModel = new DefaultListModel<String>();
	public JList<String> listy = new JList<String>(listModel);
	public JButton newsave = new JButton("New Save");
	public JButton save = new JButton("Overwrite Save");
	public JButton load = new JButton("Load");
	public JButton delete = new JButton("Delete");
	public JPanel pan1 = new JPanel(new GridLayout(4, 0));
	public JLabel title = new JLabel("Name       SceneID");
	class SaveCellRenderer extends JLabel implements ListCellRenderer {
	    private Color HIGHLIGHT_COLOR = new Color(0, 0, 128);

	    public SaveCellRenderer() {
	      setOpaque(true);
	    }

	    public Component getListCellRendererComponent(JList list, Object value,
	        int index, boolean isSelected, boolean cellHasFocus) {
	      String entry = (String) value;
	      String[] li=entry.split("~");
	      setText(li[0]+"             "+li[1]+":"+li[2]);
	      if (isSelected) {
	        setBackground(HIGHLIGHT_COLOR);
	        setForeground(Color.white);
	      }
	      else {
	        setBackground(Color.white);
	        setForeground(Color.black);
	      }
	      return this;
	    }
	  }
	class NSAVE implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
	    	saves.add(curDATA);
	    	update();
	    	listy.setSelectedIndex(0);
	      }
	    }
	class OSAVE implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
		    int i = saves.indexOf(listy.getSelectedValue());
		    saves.remove(i);
		    saves.add(i,curDATA);
		    update();
		    listy.setSelectedIndex(0);
	      }
	    }
	class LOAD implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
		    curDATA=listy.getSelectedValue();
	    	start();
	      }
	    }
	class DELETE implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
		    int i = saves.indexOf(listy.getSelectedValue());
		    saves.remove(i);
		    update();
		    if (saves.size()>0) {
		    	listy.setSelectedIndex(0);
		    }
	      }
	    }
	public void InitiateGUI() {
		update();
		win.setSize(700, 700);
		win.setResizable(false);
	    win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    toMenu();
	}
	public void toMenu() {
		win.getContentPane().removeAll();
		win.getContentPane().revalidate();
		win.getContentPane().repaint();
		listy.setCellRenderer(new SaveCellRenderer());
	    win.setLayout(new BorderLayout());
	    newsave.addActionListener(new NSAVE());
	    save.addActionListener(new OSAVE());
	    load.addActionListener(new LOAD());
	    delete.addActionListener(new DELETE());
	    pan1.add(newsave);
	    pan1.add(save);
	    pan1.add(load);
	    pan1.add(delete);
	    win.add(title, BorderLayout.NORTH);
	    win.add(new JScrollPane(listy), BorderLayout.CENTER);
	    win.add(pan1, BorderLayout.EAST);
	    win.setVisible(true);
	    if (saves.size()>0) {
	    	listy.setSelectedIndex(0);
	    }
	}
	public void update() {
		listModel.removeAllElements();
		for (String save : saves) {
			listModel.addElement(save);
		}
	}
	/**
	 * GUI components for in-game
	 */
	public JLabel bkg = createPictureLabel("start.gif", new Point(0,0), new Point(700,700));
	public JLabel curActor = createPictureLabel("King_Regular.gif", new Point(0,0), new Point(700,700));
	public JTextArea text = new JTextArea();
	public JLayeredPane pane = new JLayeredPane();
	public JPanel panel = new JPanel();
	public JButton butt = new JButton("Menu");
	public JLabel DangerousBox = createPictureLabel("box.png", new Point(30,500), new Point(635,700));
	public boolean started = false;
	class MENU implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
		    toMenu();
	      }
	    }
	public void start() {
		win.getContentPane().removeAll();
		win.getContentPane().revalidate();
		win.getContentPane().repaint();
		butt.setVerticalAlignment(JLabel.TOP);
		butt.setHorizontalAlignment(JLabel.CENTER);
		butt.setBounds(0, 0, 100, 30);
		butt.addActionListener(new MENU());
		text.setOpaque(false);
		text.setFont(new Font("Helvetica", 15, 15));
		text.setForeground(Color.WHITE);
		text.setText("Phillip: \n\n" +
				"As I lay there in the snow on that cold fateful evening.\n" +
				"I wondered to myself: \"Had I dreamt it all?\" or had I only been slightly\n" +
				"reasoning with myself about the orange dragons of uthengard\n" +
				"or the purple minxes of ostenwald, but I digress for I am A very content \n" +
				"gelatine-like hippo and do not wish for Conflict");
		text.setEditable(false);
		text.setBounds(70, 510, 600, 600);
		panel.setLayout(new GridLayout(2,6));
		if (!started) {
			pane.add(bkg, new Integer(0));
        	pane.add(curActor, new Integer(1));
        	pane.add(butt, new Integer(2));
        	pane.add(DangerousBox, new Integer(2));
        	pane.add(text, new Integer(3));
        	started=true;
		}
		win.add(pane);
	}
	private JLabel createPictureLabel(String url,
            Point origin, Point end) {
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(url));
		label.setVerticalAlignment(JLabel.TOP);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBounds(origin.x, origin.y, end.x, end.y);
		return label;
		}
	public void populate() {
		try {
		      ObjectInput bnkFile = new ObjectInputStream(new BufferedInputStream(
		          new FileInputStream("Save_Data.conor")));
		      Object[] comein = (Object[]) bnkFile.readObject();
		      bnkFile.close();
		      for (Object ac : comein) {
		        saves.add((String) ac);
		      }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
