package project.student.system.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;

import project.student.system.controller.Controller;
import project.student.system.bean.Student;
import project.student.system.myexception.OverflowArrayCapacityException;

/**
 * 工具类，提供了一些静态方法
 */
public class Tools {

	/**
	 * 保存学生信息为字符串
	 * 
	 * @param stu 传入一个学生对象
	 * @return 返回一个字符串
	 */
	public static String getStudent(Student stu) {
		return stu.getId() + "," + stu.getName() + "," + stu.getAge() + "," + stu.getGender() + "," + stu.getScore();
	}

	/**
	 * 将字符串解析为学生对象
	 * 
	 * @param str 传入一个字符串
	 * @return 返回一个解析好的学生对象
	 */
	public static Student parseStudent(String str) {
		String[] split = str.split(",");

		int id = Integer.parseInt(split[0]);
		String name = split[1];
		int age = Integer.parseInt(split[2]);
		char sex = split[3].charAt(0);
		float score = Float.parseFloat(split[4]);

		Student student = new Student(name, age, sex, score);

		student.setId(id);

		return student;
	}

	/**
	 * 输入流操作学生信息
	 * 
	 * @param c 传入一个学生管理类
	 * @throws IOException IO异常
	 */
	public static void WriteToFile(Controller c) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File("./data/StudentData.txt")));

		for (int i = 0; i < c.size(); i++) {
			bw.write(getStudent(c.get(i)));
			bw.newLine();
		}

		bw.close();
	}

	/**
	 * 输出流将文件内容写入学生集合
	 * 
	 * @param c 传入一个学生管理类
	 * @throws IOException IO异常
	 * @throws OverflowArrayCapacityException 超出数组最大容量异常
	 */
	public static void ReadFromFile(Controller c) throws IOException, OverflowArrayCapacityException, OverflowArrayCapacityException {
		BufferedReader br = new BufferedReader(new FileReader("./data/StudentData.txt"));

		String data = null;

		while (null != (data = br.readLine())) {
			c.add(parseStudent(data));
		}

		br.close();
	}

	/**
	 * 排序
	 * 
	 * @param <T> 自定义泛型
	 * @param arr 用户指定类型的数组
	 * @param c   Comparator接口的实现类
	 */
	public static <T> void selectSort(T[] arr, Comparator<T> c) {
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (c.compare(arr[i], arr[j]) > 0) {
					T temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
	}
}
