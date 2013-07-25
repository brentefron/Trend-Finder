
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TrendApplication extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel label1;
	private JLabel label2;
	private JButton button;
	private JComboBox combobox1;
	private JComboBox combobox2;
	private ArrayList<String> Makes = new ArrayList<String>();
	Scanner s;
	{
		try {
			s = new Scanner(new File("/Users/Brent/Desktop/workspace/Trend Finder/ImportantDocs/Makes.txt"));
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		while (s.hasNext()){
			{	
				Makes.add(s.nextLine());
			}
		}
		s.close();
	}
	String[] MakeArray = Makes.toArray(new String[Makes.size()]);
	private	ArrayList<String[]> Models = new ArrayList<String[]>();
	Scanner scan;
	{
		try {
			scan = new Scanner(new File("/Users/Brent/Desktop/workspace/Trend Finder/ImportantDocs/Models.txt"));
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		while (scan.hasNext()){
			{	
				Models.add(scan.nextLine().split(","));
			}
		}
		scan.close();
	}
	String makeInput;
	String modelInput;


	public TrendApplication() throws FileNotFoundException
	{

		setLayout(new FlowLayout());

		label1 = new JLabel("Enter a Make");
		add (label1);

		combobox1 = new JComboBox();
		for(int i = 0; i < Makes.size(); i++){
			combobox1.addItem(Makes.get(i));
		}
		add (combobox1);
		event2 Make = new event2();
		combobox1.addActionListener(Make);

		label2 = new JLabel("Enter a Model");


		combobox2 = new JComboBox();


		button = new JButton("Enter");
		add(button);

		event trends = new event();
		button.addActionListener(trends);

	}
	public class event2 implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent Makes)
		{
			try {
				combobox2.removeAllItems();
				for (int k = 0; k<Models.get(combobox1.getSelectedIndex()).length; k++)
				{

					{
						combobox2.addItem(Models.get(combobox1.getSelectedIndex())[k]);
					}
					add (combobox2);
					add (label2);
				}



			}
			catch (Exception ex)
			{
				label1.setText("Uhhhh.......?");
			}
		}
	}
	public class event implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent trends)
		{
			label1.setText("loading");
			try {
				makeInput = (String) (combobox1.getSelectedItem());
				modelInput = (String) (combobox2.getSelectedItem());
				Htmlpagecreator.htmlPage(TwitterCar.twitter(makeInput, modelInput), makeInput, modelInput);
				label1.setText("Again?");
				Desktop.getDesktop().open(new File("/Users/Brent/Desktop/workspace/Trend Finder/trends.html"));
			}
			catch (Exception ex)
			{
				label1.setText("Please select a model");
			}
		}
	}



	public static void main(String[] args) throws FileNotFoundException {
		TrendApplication gui = new TrendApplication();
		gui.setSize(400, 300);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setVisible(true);
	}
} ///:~

