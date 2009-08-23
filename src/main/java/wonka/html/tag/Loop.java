package wonka.html.tag;

import java.util.*;

public class Loop {
	
	public static LoopContents over(Class clazz) {
		return new LoopContents(clazz);
	}


	public static class LoopContents {
		private Class clazz;
		private List<Object> contents = new LinkedList<Object>();

		public LoopContents(Class clazz) {
			this.clazz = clazz;
		}
		

	}

}