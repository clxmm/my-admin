package org.clxmm.autocode.learn.demo.list;

import java.util.*;

public abstract class TestArrayList<E> implements List<E> {


    /**
     * 缺省容量
     */
    private static final int DEFAULT_CAPACITY = 10;


    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * 用于默认大小的空实例的共享空数组实例。我们
     * 将其与空元素数据区分开来，以了解何时充气
     * 添加第一个元素。
     */
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    /**
     * 存储ArrayList元素的数组缓冲区。
     * ArrayList的容量是此数组缓冲区的长度。任何
     * elementData==DEFAULTCAPACITY empty elementData的空ArrayList
     * 将在添加第一个元素时扩展为默认容量。
     */
    transient Object[] elementData; // non-private to simplify nested class access,非私有以简化嵌套类访问


    private int size;


    protected transient int modCount = 0;


    /**
     * 构造一个初始容量为10的空列表。
     */
    public TestArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    /**
     * 将指定的元素追加到此列表的末尾。
     *
     * @param e
     * @return
     */
    @Override
    public boolean add(E e) {
        ensureCapacityInternal(size + 1);  // Increments modCount!!,增加modCount！！
        elementData[size++] = e;
        return true;
    }


    /**
     * 在特定位置添加元素，也就是插入元素
     *
     * @param index
     * @param element
     */
    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);//检查index也就是插入的位置是否合理。

        //跟上面的分析一样，具体看上面
        ensureCapacityInternal(size + 1);  // Increments modCount!!

        //这个方法就是用来在插入元素之后，要将index之后的元素都往后移一位，
        System.arraycopy(elementData, index, elementData, index + 1,
                size - index);

        elementData[index] = element;
        size++;
    }

    /**
     * 添加指定集合到队列末尾
     *
     * @param c
     * @return
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        Object[] a = c.toArray();     // 把集合转化为数组
        int numNew = a.length;   // 得到要添数组的长度
        // 是否需要扩容
        ensureCapacityInternal(size + numNew);  // Increments modCount
        System.arraycopy(a, 0, elementData, size, numNew);
        size += numNew;
        return numNew != 0;
    }

    /**
     * 添加集合到队列的指定位置
     *
     * @param index
     * @param c
     * @return
     */
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        rangeCheckForAdd(index);   // 检查插入的位置

        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityInternal(size + numNew);  // Increments modCount

        int numMoved = size - index;

        if (numMoved > 0) {
            System.arraycopy(elementData, index, elementData, index + numNew,
                    numMoved);
        }
        System.arraycopy(a, 0, elementData, index, numNew);

        size += numNew;
        return numNew != 0;
    }


    @Override
    public int size() {
        checkForComodification();
        return this.size;
    }

    E elementData(int index) {
        return (E) elementData[index];
    }


    @Override
    public E get(int index) {
        rangeCheck(index);

        return elementData(index);
    }

    private void rangeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }



    private void checkForComodification() {
        if (TestArrayList.this.modCount != this.modCount) {
            throw new ConcurrentModificationException();
        }
    }







    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) // 插入的位置肯定不能大于size 和小于0
            // 如果是，就报这个越界异常
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }


    private void ensureCapacityInternal(int minCapacity) {
        ensureExplicitCapacity(calculateCapacity(elementData, minCapacity));
    }

    private static int calculateCapacity(Object[] elementData, int minCapacity) {
        // 判断初始化的elementData是不是空的数组，也就是没有长度
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            // 因为如果是空的话，minCapacity=size+1；其实就是等于1，空的数组没有长度就存放不了，所以就将minCapacity变成10，也就是默认大小，
            // 但是在这里，还没有真正的初始化这个elementData的大小。
            return Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        return minCapacity;
    }

    // 确认实际的容量，上面只是将minCapacity=10，这个方法就是真正的判断elementData是否够用
    private void ensureExplicitCapacity(int minCapacity) {
        modCount++;

        // overflow-conscious code
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
    }

    private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = elementData.length; // 将扩充前的elementData大小给oldCapacity
        int newCapacity = oldCapacity + (oldCapacity >> 1);  // newCapacity就是1.5倍的oldCapacity
        if (newCapacity - minCapacity < 0)  // 新增之后还是小于最小容量
            newCapacity = minCapacity;   // 使用
        if (newCapacity - MAX_ARRAY_SIZE > 0)  //如果newCapacity超过了最大的容量限制，就调用hugeCapacity，也就是将能给的最大值给newCapacity
            newCapacity = hugeCapacity(minCapacity);
        // minCapacity is usually close to size, so this is a win:
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    // 用来赋最大值
    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        //如果minCapacity都大于MAX_ARRAY_SIZE，那么就Integer.MAX_VALUE返回，
        // 反之将MAX_ARRAY_SIZE返回。因为maxCapacity是三倍的minCapacity，可能扩充的太大了，就用minCapacity来判断了。
        //Integer.MAX_VALUE:2147483647   MAX_ARRAY_SIZE：2147483639  也就是说最大也就能给到第一个数值。还是超过了这个限制，就要溢出了。
        // 相当于arraylist给了两层防护。
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }


    /**
     * 清空
     */
    @Override
    public void clear() {
        modCount++;
        // clear to let GC do its work
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        size = 0;
    }

    /**
     * 移除指定位置的元素
     * @param index
     * @return
     */
    @Override
    public E remove(int index) {
        rangeCheck(index);

        modCount++;
        E oldValue = elementData(index);

        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                    numMoved);
        elementData[--size] = null; // clear to let GC do its work

        return oldValue;
    }










    public static void main(String[] args) {
        int i = new Random().nextInt(18);
        System.out.println(i);

        ArrayList<String> arrayList = new ArrayList();
        arrayList.add("2");
        arrayList.add(10, "2");
        arrayList.clear();
        arrayList.remove(1);
        arrayList.remove("2");



    }


}
