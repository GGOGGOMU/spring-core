package cns.annotation_user;

import cns.annotations.CNSBean;

@CNSBean
public class CnsConfig {
    private String host;
    private int port;

    public CnsConfig() {
        this.host = "127.0.0.1";
        this.port = 80;
    }
}
