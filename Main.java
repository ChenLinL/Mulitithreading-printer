import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Main {
	static int NUMBER_OF_USER = 4;
	static int NUMBER_OF_DISK = 2;
	static int NUMBER_OF_PRINTERS = 3;
	public static DiskManager diskManager = new DiskManager(NUMBER_OF_DISK);
	public static Disk disk_1 = new Disk();
	public static Disk disk_2 = new Disk();
	public static DirectoryManager DM = new DirectoryManager();
	public static printerManager PM = new printerManager(NUMBER_OF_PRINTERS);
	public static long disk_sleep_time = 200;
	public static long printer_sleep_time = 2750;
	public static boolean start = false;
//	public static ArrayList<String> User_status = new ArrayList<String>();
	public static JTextArea jt1 = new JTextArea("USER1: "+"free");//User 1
	public static JTextArea jt2 = new JTextArea("USER2: "+"free");//User 2
	public static JTextArea jt3 = new JTextArea("USER3: "+"free");//User 3
	public static JTextArea jt4 = new JTextArea("USER4: "+"free");//User 4
	public static JTextArea jt5 = new JTextArea("Printer1: "+"free");//printer1
	public static JTextArea jt6 = new JTextArea("Printer1: "+"free");//printer2
	public static JTextArea jt7 = new JTextArea("Printer1: "+"free");//printer3
	public static JTextArea jt8 = new JTextArea("Disk1: "+"free");//disk1
	public static JTextArea jt9 = new JTextArea("Disk1: "+"free");//disk2
	public static void main(String args[]) {
		JFrame jf = new JFrame("Printer_os");
		jf.setLayout(new GridLayout(4,3,10,10));
		JButton normal_start = new JButton("Normal Speed Start");
		normal_start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UserThread user1 = new UserThread(1);
					user1.start();
					UserThread user2 = new UserThread(2);
					user2.start();
					UserThread user3 = new UserThread(3);
					user3.start();
					UserThread user4= new UserThread(4);
					user4.start();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		JButton high_start = new JButton("High Speed Start");
		high_start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start = true;
				disk_sleep_time = 100;
				printer_sleep_time = 500;
				try {
					UserThread user1 = new UserThread(1);
					user1.start();
					UserThread user2 = new UserThread(2);
					user2.start();
					UserThread user3 = new UserThread(3);
					user3.start();
					UserThread user4= new UserThread(4);
					user4.start();
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		JButton extrem_start = new JButton("extrem high Speed Start");
		extrem_start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start = true;
				disk_sleep_time = 50;
				printer_sleep_time = 150;
				try {
					UserThread user1 = new UserThread(1);
					user1.start();
					UserThread user2 = new UserThread(2);
					user2.start();
					UserThread user3 = new UserThread(3);
					user3.start();
					UserThread user4= new UserThread(4);
					user4.start();
				} catch (FileNotFoundException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
			}
		});
		jf.add(normal_start);
		jf.add(high_start);
		jf.add(extrem_start);
		jf.add(jt1);
		jf.add(jt2);
		jf.add(jt3);
		jf.add(jt4);
		jf.add(jt5);
		jf.add(jt6);
		jf.add(jt7);
		jf.add(jt8);
		jf.add(jt9);
		jf.setSize(800,1000);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
//		if(start) {
//			try {
//				UserThread user1 = new UserThread(1);
//				user1.start();
//				UserThread user2 = new UserThread(2);
//				user2.start();
//				UserThread user3 = new UserThread(3);
//				user3.start();
//				UserThread user4= new UserThread(4);
//				user4.start();
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}

}
