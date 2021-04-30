package cn.alip8.juc;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * @author yaoshuai
 * @date 2021-四月-29
 */
public class Thread1A2BQuestTest {

    private final ByteArrayOutputStream outContent1 = new ByteArrayOutputStream();
    private final ByteArrayOutputStream outContent2 = new ByteArrayOutputStream();
    private final ByteArrayOutputStream outContent3 = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Test
    public void testAltExec() throws Exception {
        Thread1A2B3CQuestion question = new Thread1A2B3CQuestion();

        int printLength = 10000;
        long beginTime = System.currentTimeMillis();
        System.setOut(new PrintStream(outContent1));
        question.singleThreadImpl(printLength);
        System.err.println(String.format("SingleThread is finished user %d wait two thead begin..",
                System.currentTimeMillis() - beginTime));

        beginTime = System.currentTimeMillis();
        System.setOut(new PrintStream(outContent2));
        question.alternateExecute(printLength);
        System.err.println(String.format("Two thread is finished use time mills %d .",
                System.currentTimeMillis() - beginTime));

        beginTime = System.currentTimeMillis();
        System.setOut(new PrintStream(outContent3));
        question.waitNotifyExecute(printLength);
        System.err.println(String.format("Wait notify is finished use time mills %d .",
                System.currentTimeMillis() - beginTime));
        Assert.assertTrue(outContent1.toString() + "\r\n" + outContent2.toString(),
                outContent1.toString().equals(outContent2.toString()) &&
                        outContent1.toString().equals(outContent3.toString())
                );
    }

    @Before
    public void setUpStreams() {

    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}
