package com.altruismradio.api.methods.structure;

import com.altruismradio.api.ApiException;

/**
 * Created by yuriy on 22.12.16.
 */
public enum Parameter {
    ID("id",Integer.class),FIELDS("fields",String.class),LIST("list",Boolean.class);
    private Class type;
    private String name;

    Parameter(String name,Class type){
        this.name = name;
        this.type = type;
    }

    public String getName(){
        return name;
    }

    public ParameterWithValue value(Object object){
        if(object==null){
            ApiException.throwException(200,"Null parameter value given at "+name);
        }
        if(type.equals(object.getClass()))
            return new ParameterWithValue(this,object);
        else{
            ApiException.throwException(201,"Parameter "+name+" must be "+type.toString());
            return null;
        }
    }
}
