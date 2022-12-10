package usc.teamfive.helpinghand.drivers;

import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import usc.teamfive.helpinghand.Utilities;
import usc.teamfive.helpinghand.datastructures.DatabaseLogin;
import usc.teamfive.helpinghand.datastructures.Job;

public class ThreadPool extends Thread{
	static Vector<DelRequest> dt;
	//static Vector<PostingRequest> pt;
	public ThreadPool() {
		// TODO Auto-generated constructor stub
	}
	public void run() {
		System.out.println("listening");
		ExecutorService executor = Executors.newCachedThreadPool();
		dt = new Vector<DelRequest>();
		//pt = new Vector<PostingRequest>();
		while(true) {
			if(!dt.isEmpty()) {
				executor.execute(dt.get(0));
			}
//			if(!pt.isEmpty()) {
//				executor.execute(pt.remove(0));
//			}
		}
		//System.out.println("stoooop");
	}	
}