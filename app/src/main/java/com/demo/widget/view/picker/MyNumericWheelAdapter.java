package com.demo.widget.view.picker;

import com.contrarywind.adapter.WheelAdapter;

public class MyNumericWheelAdapter  implements WheelAdapter {

    private int minValue;
    private int maxValue;
    private int space;
    /**
     * Constructor
     *
     * @param minValue the wheel min value
     * @param maxValue the wheel max value
     */
    public MyNumericWheelAdapter(int minValue, int maxValue, int space) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.space = space;
    }

    @Override
    public Object getItem(int index) {
        if (index >= 0 && index < getItemsCount()) {
            int value = minValue + index;
            return value*space;
        }
        return 0;
    }

    @Override
    public int getItemsCount() {
        return maxValue - minValue + 1;
    }

    @Override
    public int indexOf(Object o){
        try {
            return (int)o/space - minValue;
        } catch (Exception e) {
            return -1;
        }

    }
}
