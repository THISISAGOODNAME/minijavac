import frontend.Frontend;
import java.util.*;

public class MiniJavac {
    public static void main(String[] args) throws Exception {

		Frontend frontend = new Frontend(args);

		ArrayList<String> externalList=frontend.getNames();


		if(frontend.getError())
	    	System.exit(1);

		//利用系统功能将Jasmin code转换成java bytecode

		System.out.println("将Jasmin code转换成JVM bytecode....");
		String exec="";
		Process p;// = Runtime.getRuntime().exec(exec);

		//String line;
		System.out.println("Classes: " + externalList);
		for(int i=0; i<externalList.size(); i++){
	    	exec="java -jar jasmin.jar " + externalList.get(i)+".j";
	    	p = Runtime.getRuntime().exec(exec);
		}

	    System.out.println("编译成功结束.");
    }
}