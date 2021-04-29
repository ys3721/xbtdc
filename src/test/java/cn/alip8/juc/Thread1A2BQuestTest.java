package cn.alip8.juc;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

/**
 * @author yaoshuai
 * @date 2021-四月-29
 */
public class Thread1A2BQuestTest {

    private final ByteArrayOutputStream outContent1 = new ByteArrayOutputStream();
    private final ByteArrayOutputStream outContent2 = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Test
    public void testAltExec() throws Exception {
        System.setOut(new PrintStream(outContent1));
        Thread1A2B3CQuestion question = new Thread1A2B3CQuestion();
        int testCount = 100;
        int printLength = 100;
        long beginTime = System.currentTimeMillis();
        for (int i = 0; i < testCount; i++) {
            question.singleThreadImpl(printLength);
        }
        System.err.print(String.format("SingleThread is finished user %d wait two thead begin..", System.currentTimeMillis() - beginTime));
        beginTime = System.currentTimeMillis();
        System.setOut(new PrintStream(outContent2));
        for (int i = 0; i < testCount ; i++) {
            question.alternateExecute(printLength);
        }
        System.err.print(String.format("Two thread is finished use time mills %d .", System.currentTimeMillis() - beginTime));
        Assert.assertTrue(outContent1.toString()+"--"+outContent2.toString(), outContent1.toString().equals(outContent2.toString()));
    }

    @Before
    public void setUpStreams() {

    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}
