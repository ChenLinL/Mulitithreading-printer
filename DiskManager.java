
public class DiskManager {
	boolean diskIsFree[];
	DiskManager(int numberOfDisks)
	{
		diskIsFree = new boolean[numberOfDisks];
		for(int i = 0; i < diskIsFree.length; ++i) {
			diskIsFree[i] = true;
		}
	}
	synchronized int request(int user_num) throws InterruptedException {
		while(true) {
			for(int i = 0; i < diskIsFree.length; ++i) {
				if(diskIsFree[i]) {
					diskIsFree[i] = false;
					//System.out.print("disk_num:");
					//System.out.println(i);
					return i;
				}
			}
			if(user_num == 1) {
				Main.jt1.setText("USER1: "+"Wating for a free disk");
			}
			else if(user_num == 2) {
				Main.jt2.setText("USER2: "+"Wating for a free disk");
			}
			else if(user_num == 3) {
				Main.jt3.setText("USER3: "+"Wating for a free disk");
			}
			else if(user_num == 4) {
				Main.jt4.setText("USER4: "+"Wating for a free disk");
			}
			this.wait(); //block until someone release a Resource
		}
	}
	synchronized void release(int index) {
		diskIsFree[index] = true;
		this.notify();//let a waiting thread run
	}

}

