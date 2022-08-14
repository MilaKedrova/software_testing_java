package ru.stqa.pft.sandbox;

public class MyFirstProgram {

	public static void main(String[] args) {
		hello("Peter");

		Square s = new Square(5);
		System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

		Rectangle r = new Rectangle(4, 6);
		System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

		Point P = new Point(1, 1, 5, 5);
		System.out.println("Расстояние между точками =" + distance(P));
	}

	public static void hello(String somebody) {
		System.out.println("Hello, "+ somebody + "!");
	}

	public static double distance (Point P){
		return Math.sqrt((P.x2-P.x1)*(P.x2-P.x1)+(P.y2-P.y1)*(P.y2-P.y1));
	}
}
