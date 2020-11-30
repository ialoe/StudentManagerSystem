package project.student.system.tools.impl;

import java.util.Comparator;

import project.student.system.bean.Student;

/**
 * 通过学生成绩降序排序
 */
public class MyCompareByStudentScore implements Comparator<Student> {

	@Override
	public int compare(Student o1, Student o2) {
		return (int) (o1.getScore() - o2.getScore());
	}
}
