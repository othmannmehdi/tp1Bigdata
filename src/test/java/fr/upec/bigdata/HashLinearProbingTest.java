package fr.upec.bigdata;

import org.junit.Test;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class HashLinearProbingTest {

    @Test
    public void put_and_get_full_table_A() {
        //given
        List<Character> keysA = Stream.of('B',
                'E',
                'K',
                'M',
                'N',
                'U',
                'L',
                'V',
                'X').collect(toList());
        HashLinearProbing hashLinearProbing = HashLinearProbing.createWithSize(keysA.size());
        //when
        keysA.forEach(key -> hashLinearProbing.put(key,keysA.indexOf(key)));
        //then
        assertThat(hashLinearProbing.get('L'),is(6));
        assertThat(hashLinearProbing.get('V'),is(7));
        assertThat(hashLinearProbing.get('X'),is(8));
        assertThat(hashLinearProbing.get('B'),is(0));
        assertThat(hashLinearProbing.get('K'),is(2));
        assertThat(hashLinearProbing.get('M'),is(3));
        assertThat(hashLinearProbing.get('E'),is(1));
        assertThat(hashLinearProbing.get('N'),is(4));
        assertThat(hashLinearProbing.get('U'),is(5));

    }
    @Test
    public void test_get_non_existing_key() {
        //given
        List<Character> keysA = Stream.of('B',
                'E',
                'K',
                'M',
                'N',
                'U',
                'L',
                'V',
                'X').collect(toList());
        HashLinearProbing hashLinearProbing = HashLinearProbing.createWithSize(keysA.size()+1);
        keysA.forEach(key -> hashLinearProbing.put(key,keysA.indexOf(key)));
        //when
        int actual = hashLinearProbing.get('A');
        //then
        assertThat(actual,is(-1));
    }
    @Test
    public void test_delete_non_existing_key() {
        //given
        List<Character> keysA = Stream.of('B',
                'E',
                'K',
                'M',
                'N',
                'U',
                'L',
                'V',
                'X').collect(toList());
        HashLinearProbing hashLinearProbing = HashLinearProbing.createWithSize(keysA.size()+1);
        keysA.forEach(key -> hashLinearProbing.put(key,keysA.indexOf(key)));
        //when
        hashLinearProbing.remove('A');
        //then
        assertThat(hashLinearProbing.get('A'),is(-1));
    }
    @Test
    public void test_delete_existing_key() {
        //given
        List<Character> keysA = Stream.of('B',
                'E',
                'K',
                'M',
                'N',
                'U',
                'L',
                'V',
                'X').collect(toList());
        HashLinearProbing hashLinearProbing = HashLinearProbing.createWithSize(keysA.size()+1);
        keysA.forEach(key -> hashLinearProbing.put(key,keysA.indexOf(key)));
        //when
        hashLinearProbing.remove('B');
        //then
        assertThat(hashLinearProbing.get('L'),is(6));
        assertThat(hashLinearProbing.get('V'),is(7));
        assertThat(hashLinearProbing.get('X'),is(8));
        assertThat(hashLinearProbing.get('B'),is(-1));
        assertThat(hashLinearProbing.get('K'),is(2));
        assertThat(hashLinearProbing.get('M'),is(3));
        assertThat(hashLinearProbing.get('E'),is(1));
        assertThat(hashLinearProbing.get('N'),is(4));
        assertThat(hashLinearProbing.get('U'),is(5));
    }

}
