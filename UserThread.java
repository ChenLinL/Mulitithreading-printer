import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBElement.GlobalScope;
import javax.xml.bind.annotation.XmlElementDecl.GLOBAL;

public class UserThread extends Thread {
	private Thread t;
	FileReader fileInfo = null;
	BufferedReader fileReader = null;
	StringBuffer fileBuffer = null;
	String fileName = "";
	int user_num = 0;
	public UserThread(int userNum) throws FileNotFoundException {
		fileName = "USER"+userNum;
		fileInfo = new FileReader(fileName);
		fileReader = new BufferedReader(fileInfo);
		user_num = userNum;
	}
	
	public String getLine() throws IOException {
		String lineInfo = fileReader.readLine();
		return lineInfo;
	}
	public void run(){
		try {
			String LineInfo = getLine();
			int sector_1 = 0;
			int sector_2 = 0;
			int length = 0;
			int disk_num = 0;
			int v = 0;
			ArrayList<Integer> disks = new ArrayList<Integer>();
			ArrayList<Integer> printers = new ArrayList<Integer>();
			String fileName = "";
			while( LineInfo != null) {
				if(LineInfo.indexOf(".save")!=-1) {
					length = 0;
					disk_num = Main.diskManager.request(user_num);
					fileName = LineInfo.substring(6);
					if(user_num == 1) {
						Main.jt1.setText("USER1: "+"Save the "+fileName+" to disk");
					}
					else if(user_num == 2) {
						Main.jt2.setText("USER2: "+"Save the "+fileName+" to disk");
					}
					else if(user_num == 3) {
						Main.jt3.setText("USER3: "+"Save the "+fileName+" to disk");
					}
					else if(user_num == 4) {
						Main.jt4.setText("USER4: "+"Save the "+fileName+" to disk");
					}
				}
				else if(LineInfo.indexOf(".save")==-1 && LineInfo.indexOf(".print")==-1&&LineInfo.indexOf(".end")==-1) {
					fileBuffer = new StringBuffer();
					fileBuffer.append(LineInfo);
					if(disk_num == 0) {
						Main.disk_1.write(sector_1+length,fileBuffer);
						Main.jt8.setText("Disk 1: saving the file from User"+user_num);
					}
					else {
						Main.disk_2.write(sector_2+length,fileBuffer);
						Main.jt9.setText("Disk 2: saving the file from User"+user_num);
					}
					length++;
				}
				else if(LineInfo.indexOf(".end")!=-1) {
					Main.diskManager.release(disk_num);
					FileInfo file = new FileInfo();
					file.diskNumber = disk_num;
					file.fileLength = length;
					if(disk_num == 0) {
						file.startingSector = sector_1;
						sector_1 += length;
						Main.jt8.setText("Disk1: free");
					}
					else {
						file.startingSector = sector_2;
						sector_2 += length;
						Main.jt9.setText("Disk2: free");
					}
					StringBuffer file_name = new StringBuffer();
					file_name.append(fileName);
					//System.out.println(file_name);
					//System.out.println(file.diskNumber);
					//System.out.println(file.fileLength);
					//System.out.println(file.startingSector);
					Main.DM.enter(file_name, file);	
				}
				else if(LineInfo.indexOf(".print")!=-1) {
//					Main.User_status.add("User"+user_num+":"+LineInfo);
				
					int printer_num = Main.PM.request(user_num);
					int printer_n = printer_num +1;
					if(user_num == 1) {
						Main.jt1.setText("USER1: "+"Use printer"+printer_n+" to print "+fileName);
					}
					else if(user_num == 2) {
						Main.jt2.setText("USER2: "+"Use printer"+printer_n+" to print "+fileName);
					}
					else if(user_num == 3) {
						Main.jt3.setText("USER3: "+"Use printer"+printer_n+" to print "+fileName);
					}
					else if(user_num == 4) {
						Main.jt4.setText("USER4: "+"Use printer"+printer_n+" to print "+fileName);
					}
					printers.add(printer_num);
					String print_file = LineInfo.substring(7);
					++v;
					PrintJobThread printer = new PrintJobThread(printer_num,print_file,v);
					printer.run();
					Main.PM.release(printer_num);
					if(user_num == 1) {
						Main.jt1.setText("USER1: "+"free");
					}
					else if(user_num == 2) {
						Main.jt2.setText("USER2: "+"free");
					}
					else if(user_num == 3) {
						Main.jt3.setText("USER3: "+"free");
					}
					else if(user_num == 4) {
						Main.jt4.setText("USER4: "+"free");
					}
//					if(Main.PM.all_work()) {
//						if(!printers.isEmpty()) {
//							for(int i = 0; i < printers.size();++i) {
//								Main.PM.release(printers.get(i));
//							}
//						}
//						printers.clear();
//					}
//					StringBuffer file_name = new StringBuffer();
//					file_name.append(print_file);
//					//System.out.println(file_name);
//					FileInfo p_file = Main.DM.lookup(file_name);
//					int start_n = p_file.startingSector;
//					for(int i = 0; i< p_file.fileLength; ++i) {
//						int s = start_n + i;
//						StringBuffer print_info = new StringBuffer();
//						//System.out.print("S: ");
//						//System.out.println(s);
//						if(p_file.diskNumber == 0) {
//							Main.disk_1.read(s,print_info);
//						}
//						else {
//							Main.disk_2.read(s,print_info);
//						}
//						//System.out.println(print_info.toString());
//					}
//					//System.out.println();
				}
				LineInfo = getLine();
			}
			//System.out.println("END");
			//System.out.println(Main.User_status);
		}
		catch(IOException e) {e.printStackTrace();} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void start() {
		if(t==null) {
			t = new Thread(this);
			t.start();
		}
	}
}
