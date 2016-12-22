package com.altruismradio.api;

import com.altruismradio.api.methods.structure.ParameterWithValue;

/**
 * Created by yuriy on 22.12.16.
 */
public abstract class ApiRequestListener {
    public ParameterWithValue[] parameters;

    public ApiRequestListener(ParameterWithValue ... parameters) {
        this.parameters = parameters;
    }

    public abstract void onSuccess(Object object);

    public abstract void onFailure(ApiException exception);
}
