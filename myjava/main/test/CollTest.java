import org.junit.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class CollTest {

            public static void main(String[] args) {
                // 获取 Java 线程管理 MXBean
                ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
                // 不需要获取同步的 monitor 和 synchronizer 信息，仅获取线程和线程堆栈信息
                ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
                // 遍历线程信息，仅打印线程 ID 和线程名称信息
                for (ThreadInfo threadInfo : threadInfos) {
                    System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.getThreadName());
                }
            }


    @Test
    public void mapTest() throws Exception {
        ISpeakAble iSpeakAble = new ISpeakAble() {
            @Override
            public void speak() {
                System.out.println("-------------");
            }
        };
        Class<?> clazz = comparableClassFor(iSpeakAble);
        System.out.println(clazz);
    }


    interface ISpeakAble {
        void speak();
    }

    public static Class<?> comparableClassFor(Object x) {
        if (x instanceof Comparable) {
            Class<?> c; Type[] ts, as; Type t; ParameterizedType p;
            if ((c = x.getClass()) == String.class) // bypass checks
                return c;
            if ((ts = c.getGenericInterfaces()) != null) {
                for (int i = 0; i < ts.length; ++i) {
                    if (((t = ts[i]) instanceof ParameterizedType) &&
                            ((p = (ParameterizedType)t).getRawType() ==
                                    Comparable.class) &&
                            (as = p.getActualTypeArguments()) != null &&
                            as.length == 1 && as[0] == c) // type arg is c
                        return c;
                }
            }
        }
        return null;
    }
}
