package Assets;

import java.io.*;
import java.lang.*;

public class Update {
	public static void UpdateInfo(String version, String update_1, String update_2, String update_3 ){
		if(update_1.equals("") || update_1.equals("None") ){
			System.out.println(version);
			System.out.println("** No Updates **");
		}
		else{
			System.out.println(version);
			if(!update_1.equals("") || !update_1.equals("None")) {
				System.out.println(update_1);
			}
			if(!update_2.equals("") || !update_2.equals("None")) {
				System.out.println(update_2);
			}
			if(!update_3.equals("") || !update_3.equals("None")) {
				System.out.println(update_3);
			}
			if(update_2.equals("") || update_2.equals("None") && update_3.equals("") || update_3.equals("None")){
				System.out.println(version);
				System.out.println(update_1);
				System.out.println("** No More Updates **");
			}
			if(update_3.equals("") || update_3.equals("None")){
				System.out.println(version);
				System.out.println(update_1);
				System.out.println(update_2);
				System.out.println("** No More Updates **");
			}
		}
	}
	public static void AutoStart(){
		String version = "** 3.0 **";
		String update_1 = "** Added New Update Feature **";
		String update_2 = "";
		String update_3 = "";
		UpdateInfo(version,update_1,update_2,update_3);
	}
}