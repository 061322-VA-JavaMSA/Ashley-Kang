public class FirstJavaClass{
public static void main(String args[])
{
	if(args.length == 0){
	System.out.println("No args present");
}
	else if(args.length == 1){
	System.out.println(Math.random());
}
	else if(args.length == 2){ int num = Integer.parseInt(args[0]); 
	int counter = 0; while(counter < num){
	System.out.println(args[1]);
	counter++;
}
}
else {
	System.out.println("Too many args");	
}
}
}
