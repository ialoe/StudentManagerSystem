package project.student.system.tools.impl;

import project.student.system.bean.Student;
import project.student.system.tools.StudentFilter;

/*
 * 根据学生的成绩进行过滤
 */
public class AcceptStudentScore implements StudentFilter {

	/*
	 * 过滤掉成绩小于等于50的学生
	 */
	@Override
	public boolean accept(Student stu) {
		return stu.getScore() > 50;
	}
}
