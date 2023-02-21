package queue_singlelinkedlist;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class FifoQueueTest {
    private FifoQueue<Integer> myIntQueue1;
    private FifoQueue<Integer> myIntQueue2;

    @BeforeEach
    void setUp() {
        myIntQueue1 = new FifoQueue<Integer>();
        myIntQueue2 = new FifoQueue<Integer>();
    }

    @AfterEach
    void tearDown() {
        myIntQueue1 = null;
        myIntQueue2 = null;
    }

    @Test
    void testAppendTwoNull() {
        assertThrows(NullPointerException.class, () -> myIntQueue1.append(myIntQueue2));
    }
    @Test
    void test1AppendOneNull() {
        myIntQueue2.offer(1);
        myIntQueue2.offer(2);
        myIntQueue2.append(myIntQueue1);
        for (int i = 1; i <= 2; i++) {
            int k = myIntQueue2.poll();
            assertEquals(i, k);
        }
    }
    @Test
    void test2AppendOneNull() {
        myIntQueue2.offer(1);
        myIntQueue2.offer(2);
        myIntQueue2.offer(3);
        myIntQueue1.append(myIntQueue2);
        for (int i = 1; i <= 3; i++) {
            int k = myIntQueue1.poll();
            assertEquals(i,k);
        }
    }
    @Test
    void testAppend() {
        myIntQueue1.offer(1);
        myIntQueue2.offer(2);

        myIntQueue2.offer(3);
        myIntQueue2.offer(4);
        myIntQueue2.offer(5);
        myIntQueue1.append(myIntQueue2);
        for (int i = 1; i <= 5; i++) {
            int k = myIntQueue1.poll();
            assertEquals(i,k);

        }
    }
    @Test
    void testAppendSameQueue() {
        int nbr = 5;
        for (int i = 1; i <= nbr; i++) {
            myIntQueue1.offer(i);
        }
        assertThrows(IllegalArgumentException.class, () -> myIntQueue1.append(myIntQueue1));
    }
}