package com.altruismradio.api.methods.structure;

/**
 * Created by yuriy on 22.12.16.
 */
public abstract class ApiMethod {
    protected ParameterWithValue[] values;

    public Object execute(ParameterWithValue ... parameterWithValues){
        this.values = parameterWithValues;
        return run();
    }

    protected abstract Object run();

    protected Object get(Parameter p){
        for(int i=0;i<values.length;i++)
            if(values[i].parameter==p)
                return values[i].value;
        return null;
    }
}
