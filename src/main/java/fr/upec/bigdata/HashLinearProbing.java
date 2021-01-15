package fr.upec.bigdata;

/**
 * !!!!! IMPORTANT : tout les exemples sont dans les tests !!!!!
 */
public class HashLinearProbing {
    /**
     * nombre de bucket
     */
    private int M;

    private char[] keys;

    private int[] values;

    private HashLinearProbing(int size) {
        M = size;
        keys = new char[size];
        values = new int[size];
    }

    public static HashLinearProbing createWithSize(int size) {
        if (size < 1) {
            throw new ArrayIndexOutOfBoundsException("Array size cant be lower than 1");
        }
        return new HashLinearProbing(size);
    }

    public void put(char key, int value) {
        if (M <= value) {
            throw new ArrayIndexOutOfBoundsException("Hash is full");
        }
        int index = hash(key);
        while (keys[index] != 0) {
            index = (index + 1) % M;
        }
        keys[index] = key;
        values[index] = value;
    }

    public int get(char key) {
        int index = hash(key);
        int valueToReturn = -1;
        int i = 0;
        while (keys[index] != key && keys[index] != 0) {
            index = (index + 1) % M;
            if (i == M) {
                return valueToReturn;
            }
            i++;
        }
        if (keys[index] == key) {
            valueToReturn = values[index];
        }
        return valueToReturn;
    }

    public void remove(char key) {
        int index = hash(key);
        int i = 0;
        while (keys[index] != key && keys[index] != 0) {
            index = (index + 1) % M;
            if (i == M) {
                break;
            }
            i++;
        }
        keys[index] = 0;
        values[index] = 0;
        index = (index + 1) % M;
        while (keys[index] != 0) {
            char savedKey = keys[index];
            int savedValue = values[index];
            keys[index] = 0;
            values[index] = 0;
            put(savedKey, savedValue);
            index = (index + 1) % M;
        }
    }

    private int hash(char key) {
        return key % M;
    }
}
