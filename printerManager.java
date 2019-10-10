
public class printerManager {
	  boolean printerIsFree[];
      printerManager(int numberOfPrinters)
      {
              printerIsFree = new boolean[numberOfPrinters];
              for (int i=0; i<printerIsFree.length; ++i)
                      printerIsFree[i] = true;
      }
      synchronized boolean all_work() {
    	 for (int i = 0; i < printerIsFree.length; ++i) {
             if ( printerIsFree[i] )
             {
                   return false;
             }
    	 }
    	 return true;
     }
      synchronized int request(int user_num) throws InterruptedException
      {
          while (true)
          {
        	  for (int i = 0; i < printerIsFree.length; ++i) {
        		  if ( printerIsFree[i] )
                  {
        			  printerIsFree[i] = false;
        			  return i;
                  }
              }
        	if(user_num == 1) {
  				Main.jt1.setText("USER1: "+"Wating for a free printer");
  			}
  			else if(user_num == 2) {
  				Main.jt2.setText("USER2: "+"Wating for a free printer");
  			}
  			else if(user_num == 3) {
  				Main.jt3.setText("USER3: "+"Wating for a free printer");
  			}
  			else if(user_num == 4) {
  				Main.jt4.setText("USER4: "+"Wating for a free printer");
  			}
              this.wait(); // block until someone releases a Resource
          }
      }
      synchronized void release( int index )
      {
              printerIsFree[index] = true;
              this.notify(); // let a waiting thread run
      }

}
