import java.util.EmptyStackException;

public class ListStack implements DStack {
	public class ListNode {
		double data;
		ListNode next;
	}
	ListNode sound;
	@Override
	public boolean isEmpty() {
		if (sound==null) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void push(double d) { //must code for empty and non-empty list separately
		if (sound==null) {
			sound=new ListNode();
			sound.data=d;
		}
		else {
			ListNode current=sound;
			while (current.next!=null) {
				current=current.next;
			}
			current.next=new ListNode();
			current.next.data=d;
		}

	}

	@Override
	public double pop() throws EmptyStackException{
		if (sound.next==null) {
			double i=sound.data;
			sound=null;
			return i;
		}
		else {
		ListNode current=sound;
		while (current.next.next!=null) {
			current=current.next;	
		}
		double i=current.next.data;
		current.next=null;
		return i;	
		}
	}

	@Override
	public double peek() throws EmptyStackException{
		ListNode current=sound;
		while (current.next!=null) {
			current=current.next;
		}
		return current.data;
	}

}
