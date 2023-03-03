package com.elytron.wildfly.hpi;

import com.elytron.wildfly.hpi.exception.ClientConfigException;

import java.net.URI;

public interface ClientConfigProvider {
    String getUsername(URI uri) throws ClientConfigException;
    String getPassword(URI uri) throws ClientConfigException;
}
