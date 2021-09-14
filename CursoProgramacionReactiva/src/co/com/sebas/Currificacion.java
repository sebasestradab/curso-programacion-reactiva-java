package co.com.sebas;

public class Currificacion {
	public static Function1<Integer, Integer, Integer> funcionSimple = new Function1<Integer, Integer, Integer>() {
		
		@Override
		public Integer simple(Integer x, Integer y) {
			// TODO Auto-generated method stub
			return x + y;
		}
	};
	
	public static Function2<Integer, Function2<Integer, Integer>> funcionCurri = new Function2<Integer, Function2<Integer, Integer>>() {
	
		@Override
		public Function2<Integer, Integer> curri(Integer x) {
			// TODO Auto-generated method stub
			return new Function2<Integer, Integer>() {
				@Override
				public Integer curri(Integer y) {
					return x + y;
				}
			};
		}
	};
	
	public static void main (String[] args) {
		
	}
}

interface Function1<A, B, C> {
	public C simple(A a, B b);
}

interface Function2<A, B> {
	public B curri(A a);
}
