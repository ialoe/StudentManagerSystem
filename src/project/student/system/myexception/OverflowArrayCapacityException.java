package project.student.system.myexception;

/**
 * 自定义异常，用于扩容方法中的数组容量超过最大值
 */
public class OverflowArrayCapacityException extends Exception {
    public OverflowArrayCapacityException() {
        super();
    }

    public OverflowArrayCapacityException(String message) {
        super(message);
    }
}