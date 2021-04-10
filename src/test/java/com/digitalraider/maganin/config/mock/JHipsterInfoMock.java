package com.digitalraider.maganin.config.mock;

import com.digitalraider.maganin.config.JHipsterInfo;
import io.quarkus.test.Mock;

import javax.enterprise.context.ApplicationScoped;

@Mock
@ApplicationScoped
public class JHipsterInfoMock implements JHipsterInfo {

    public static Boolean enable;

    @Override
    public Boolean isEnable() {
        return enable;
    }
}
