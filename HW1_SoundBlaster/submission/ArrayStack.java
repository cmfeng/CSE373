
import java.util.EmptyStackException;

public class ArrayStack implements DStack {
	int maxNumber=10;//the distributed initial length of array
	int numberOfArray=0;//the actual length of array,i.e. the nunber of data in it
	double[] sound = new double[maxNumber];
	@Override
	
	public boolean isEmpty() { //to see if the array given is empty
		if (numberOfArray==0) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void push(double d) {
		if (numberOfArray==maxNumber) {//resize to use an array twice as large whenever the array becomes full, copying over the elements in the smaller array.
			maxNumber=2*maxNumber;
			double[] sounda= new double[sound.length*2];
			for(int i=0; i<numberOfArray; i++) {
				sounda[i]=sound[i];
			}
			sound=sounda;
		}
		sound[numberOfArray]=d;
		numberOfArray++;
		}

	@Override
	public double pop() throws EmptyStackException{
		if (numberOfArray<=maxNumber/4) {//when the array is 3/4 empty, the stack resizes to use an array of half the size.
			maxNumber=maxNumber/2;
			double[] sounds=new double[maxNumber];
			for (int i=0; i<numberOfArray; i++) {
				sounds[i]=sound[i];
			}
			sound=sounds;
		} 
		numberOfArray=numberOfArray-1;
		return sound[numberOfArray];
	}

	@Override
	public double peek() throws EmptyStackException{
		return sound[numberOfArray-1];
	}

}
