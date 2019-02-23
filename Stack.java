	public class Stack
	{
		public Node Top;
		public int length;
		
		public void push(byte i)
		{
			Node n  = new Node(i);
			
			if (Top != null)
				n.next = Top;
			Top = n;
			length++;
		}
		
		public byte pop()
		{
			if (Top != null)
			{
				Node p = Top;
				Top = p.next;
				length--;
				return p.n;
				
			}
			return -1;
		}
		
		public byte peek()
		{
			if (Top != null)
				return Top.n;
			return -1;
		}
		
		public void clear()
		{
			Top = null;
			length = 0;
		}
	}