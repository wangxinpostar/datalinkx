package com.datalinkx.dataserver.config;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class CustomResponseWrapper extends HttpServletResponseWrapper {
    private int status;
    public CustomResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public void setStatus(int sc) {
        super.setStatus(sc);
        this.status = sc;
    }

    @Override
    public void setStatus(int sc, String sm) {
        super.setStatus(sc, sm);
        this.status = sc;
    }

    public int getStatus() {
        return status;
    }
}
