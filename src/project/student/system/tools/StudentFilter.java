package project.student.system.tools;

import project.student.system.bean.Student;

/*
 * 学生过滤信息接口
 */
public interface StudentFilter {
	// 过滤方法，保留需要的学生对象，返回true，否则返回false
	public abstract boolean accept(Student stu);
}
