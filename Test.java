class Factorial{
    public static void main(String[] a){
	System.out.println((new Fac2().ComputeFac(10))+(new Fac().ComputeFac(10)));
    }
}

class Fac {

    public int ComputeFac(int num){
		int i;
		int total;
		int[] array;
		array = new int[10];
		total=0;
		for(i=0;i<10;i++){
			array[i]=i*10;
			total = total+array[i];
		}
		return total;
    }

}


class Fac2 {

	public int ComputeFac(int num){
		int num_aux ;
		if (num < 1)
			num_aux = 1 ;
		else
			num_aux = num * (this.ComputeFac(num-1)) ;
		return num_aux ;
	}

}