package project.student.system.tools.impl;

import project.student.system.bean.Student;
import project.student.system.tools.StudentFilter;

/*
 * 根据学生的年龄进行过滤
 */
public class AcceptStudentAge implements StudentFilter {

	/*
	 * 过滤掉年龄大于等于20的学生
	 */
	@Override
	public boolean accept(Student stu) {
		return stu.getAge() < 20;
	}
}
