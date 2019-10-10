
public class Disk {
	static final int NUM_SECTORS = 1024;
	StringBuffer sectors[] = new StringBuffer[NUM_SECTORS];
	FileInfo file = new FileInfo();
	void write(int sector, StringBuffer data) throws InterruptedException {
		sectors[sector] = data;
		Thread.sleep(Main.disk_sleep_time);
	}
	void read(int sector, StringBuffer data) throws InterruptedException {
		//System.out.print("disk: ");
		//System.out.println(sectors[sector]);
		data.append( sectors[sector]);
		//System.out.println(data);
		Thread.sleep(Main.disk_sleep_time);
	}

		
}
