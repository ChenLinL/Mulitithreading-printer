import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class PrintJobThread extends Thread{
	private Thread t;
	int num_printer =0;
	String file = "";
	int version = 0;
	public PrintJobThread(int printer_num, String print_file, int v) {
		num_printer = printer_num+1;
		file = print_file;
		version = v;
	}
	public void run() {
		try {
			if(num_printer == 1) {
				Main.jt5.setText("Printer1: "+"Printing the"+file);
			}
			else if(num_printer == 2) {
				Main.jt6.setText("Printer2: "+"Printing the"+file);
			}
			else if(num_printer == 3) {
				Main.jt7.setText("Printer3: "+"Printing the"+file);
			}
			PrintWriter out = new PrintWriter("Printer"+num_printer+" "+file+"("+version+")"+".txt");
			StringBuffer file_name = new StringBuffer();
			file_name.append(file);
			//System.out.println(file_name);
			FileInfo p_file = Main.DM.lookup(file_name);
			int start_n = p_file.startingSector;
			for(int i = 0; i< p_file.fileLength; ++i) {
				int s = start_n + i;
				StringBuffer print_info = new StringBuffer();
				//System.out.print("S: ");
				//System.out.println(s);
				
				//System.out.println(p_file.diskNumber);
				if(p_file.diskNumber == 0) {
					//System.out.println("1");
					Main.jt8.setText("Disk 1: reading the file From disk 1");
					Main.disk_1.read(s,print_info);
					//System.out.println(print_info);
					Main.jt8.setText("Disk 1: free");
				}
				else {
					Main.jt9.setText("Disk 1 is reading the file From disk 1");
					Main.disk_2.read(s,print_info);	
					Main.jt9.setText("Disk 1: free");
				}
				//System.out.println("printer");
				//System.out.println(print_info);
				out.println(print_info);
				Thread.sleep(Main.printer_sleep_time);
			}
			out.close();
			if(num_printer == 1) {
				Main.jt5.setText("Printer1: "+"free");
			}
			else if(num_printer == 2) {
				Main.jt6.setText("Printer2: "+"free");
			}
			else if(num_printer == 3) {
				Main.jt7.setText("Printer3: "+"free");
			}
		} catch (FileNotFoundException | InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void start() {
		if( t == null) {
			t = new Thread(this);
			t.start();
		}
	}
}
