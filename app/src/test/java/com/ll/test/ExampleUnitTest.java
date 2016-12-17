package com.ll.test;

import com.ll.test.java.AA;
import com.ll.test.java.Java;
import com.ll.test.java.Test_1;
import com.ll.test.java.Test_data;
import com.ll.test.log.Printer;

import org.junit.Test;
import org.junit.rules.TestName;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
//        Java java = new Java();
//        Test_1 test_1 = new Test_1();
//        test_1.test();
//        test_1.test_fan();
//        ArrayList arrayList=new ArrayList();
//        arrayList.add("ad");
//        AA.main();
//        Java.ll();
//        new Test_data();
//        Test_data.Sieve.main();
//        Test_data.Sieve.main1();
        Test_data.thread();
//        assertEquals(4, Java.ll());
    }
}